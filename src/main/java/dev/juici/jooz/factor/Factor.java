package dev.juici.jooz.factor;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class Factor {
    private final Identifier id;
    private final Text key;
    private final Identifier icon;
    private final FactorType type;

    public Factor(Identifier id, Text key, Identifier icon, FactorType type) {
        this.id = id;
        this.key = key;
        this.icon = icon;
        this.type = type;
    }

    public Identifier getId() {
        return id;
    }

    public Text getKey() {
        return key;
    }

    public Identifier getIcon() {
        return icon;
    }

    public FactorType getType() {
        return type;
    }
}