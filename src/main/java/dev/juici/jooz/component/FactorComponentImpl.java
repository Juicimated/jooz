package dev.juici.jooz.component;

import dev.juici.jooz.JoozLib;
import dev.juici.jooz.factor.FactorGroup;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class FactorComponentImpl implements FactorComponent {
    private final Set<FactorGroup> groups = new HashSet<>();
    private FactorGroup activeGroup;

    public FactorComponentImpl() {
        FactorGroup defaultGroup = new FactorGroup(Identifier.of(JoozLib.MOD_ID, "default"),
                Text.translatable("factorgroup.jooz.default"));
        groups.add(defaultGroup);
        activeGroup = defaultGroup;
    }

    @Override
    public Set<FactorGroup> getFactorGroups() {
        return groups;
    }

    @Override
    public void addGroup(FactorGroup group) {
        groups.add(group);
        if (activeGroup == null) activeGroup = group;
    }

    @Override
    public void removeGroup(FactorGroup group) {
        groups.remove(group);
        if (activeGroup == group) activeGroup = groups.stream().findFirst().orElse(null);
    }

    @Override
    public FactorGroup getActiveGroup() {
        return activeGroup;
    }

    @Override
    public void setActiveGroup(FactorGroup group) {
        this.activeGroup = group;
    }

    @Override
    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        groups.clear();

        NbtList groupList = nbt.getList("groups", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < groupList.size(); i++) {
            NbtCompound groupTag = groupList.getCompound(i);
            Identifier gId = Identifier.of(groupTag.getString("group_id"));
            Text gKey = Text.of(groupTag.getString("group_key"));

            FactorGroup group = new FactorGroup(gId, gKey);

            NbtList factors = groupTag.getList("factors", NbtElement.STRING_TYPE);
            for (int j = 0; j < factors.size(); j++) {
                Identifier fId = Identifier.of(factors.getString(j));
                group.getFactorIds().add(fId);
            }

            groups.add(group);
        }

        if (nbt.contains("active_group")) {
            Identifier activeId = Identifier.of(nbt.getString("active_group"));
            activeGroup = groups.stream()
                    .filter(g -> g.getId().equals(activeId))
                    .findFirst()
                    .orElse(null);
        }
    }

    @Override
    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (activeGroup != null) {
            nbt.putString("active_group", activeGroup.getId().toString());
        }

        NbtList groupList = new NbtList();
        for (FactorGroup group : groups) {
            NbtCompound groupTag = new NbtCompound();
            groupTag.putString("group_id", group.getId().toString());
            groupTag.putString("group_key", group.getKey().toString());

            NbtList factorList = new NbtList();
            for (Identifier fId : group.getFactorIds()) {
                factorList.add(NbtString.of(fId.toString()));
            }

            groupTag.put("factors", factorList);
            groupList.add(groupTag);
        }

        nbt.put("groups", groupList);
    }
}