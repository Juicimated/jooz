package dev.juici.jooz.factor;

import net.minecraft.util.Identifier;

public class CosmeticFactor extends Factor {
    public CosmeticFactor(Identifier id, String key, Identifier icon) {
        super(id, key, icon, FactorType.COSMETIC);
    }
}