package dev.juici.jooz.factor;

import dev.juici.jooz.JoozLib;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class FactorRegistry {
    private static final Map<Identifier, Factor> FACTORS = new HashMap<>();
    private static final Map<Identifier, FactorGroup> GROUPS = new HashMap<>();

    public static void register(Factor factor) {
        FACTORS.put(factor.getId(), factor);
        JoozLib.LOGGER.info("[JoozLib] Registered Factor: {}", factor.getId());
    }

    public static Factor get(Identifier id) {
        return FACTORS.get(id);
    }

    public static Map<Identifier, Factor> all() {
        return FACTORS;
    }

    public static void registerGroup(FactorGroup group) {
        GROUPS.put(group.getId(), group);
        JoozLib.LOGGER.info("[JoozLib] Registered Factor Group: {}", group.getId());
    }

    public static FactorGroup getGroup(Identifier id) {
        return GROUPS.get(id);
    }

    public static Map<Identifier, FactorGroup> allGroups() {
        return GROUPS;
    }
}