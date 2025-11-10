package dev.juici.jooz.factor;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PassiveFactor extends Factor {
    public PassiveFactor(Identifier id, Text key, Identifier icon) {
        super(id, key, icon, FactorType.PASSIVE);
    }
}