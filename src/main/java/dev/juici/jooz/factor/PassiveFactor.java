package dev.juici.jooz.factor;

import net.minecraft.util.Identifier;

public class PassiveFactor extends Factor {
    public PassiveFactor(Identifier id, String key, Identifier icon) {
        super(id, key, icon, FactorType.PASSIVE);
    }
}