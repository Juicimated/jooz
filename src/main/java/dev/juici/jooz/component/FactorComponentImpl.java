package dev.juici.jooz.component;

import dev.juici.jooz.JoozLib;
import dev.juici.jooz.factor.FactorGroup;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class FactorComponentImpl implements FactorComponent {
    private final Set<FactorGroup> groups = new HashSet<>();
    private FactorGroup activeGroup;

    public FactorComponentImpl() {
        FactorGroup defaultGroup = new FactorGroup(Identifier.of(JoozLib.MOD_ID, "default"));
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
    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {

    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {

    }
}