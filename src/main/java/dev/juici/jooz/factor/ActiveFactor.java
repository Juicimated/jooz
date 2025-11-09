package dev.juici.jooz.factor;

import net.minecraft.util.Identifier;

public class ActiveFactor extends Factor {
    public ActiveFactor(Identifier id, String key, Identifier icon) {
        super(id, key, icon, FactorType.ACTIVE);
    }
}