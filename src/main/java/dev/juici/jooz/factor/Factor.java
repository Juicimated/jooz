package dev.juici.jooz.factor;

import net.minecraft.util.Identifier;

public abstract class Factor {
    private final Identifier id;
    private final String key;
    private final Identifier icon;
    private final FactorType type;

    public Factor(Identifier id, String key, Identifier icon, FactorType type) {
        this.id = id;
        this.key = key;
        this.icon = icon;
        this.type = type;
    }

    public Identifier getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public Identifier getIcon() {
        return icon;
    }

    public FactorType getType() {
        return type;
    }
}