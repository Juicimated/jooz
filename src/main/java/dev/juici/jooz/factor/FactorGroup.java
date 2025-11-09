package dev.juici.jooz.factor;

import net.minecraft.util.Identifier;
import java.util.HashSet;
import java.util.Set;

public class FactorGroup {
    private final Identifier id;
    private final Set<Factor> factors = new HashSet<>();

    public FactorGroup(Identifier id) {
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    public Set<Factor> getFactors() {
        return factors;
    }

    public void addFactor(Factor factor) {
        factors.add(factor);
    }

    public void removeFactor(Factor factor) {
        factors.remove(factor);
    }

    public boolean hasFactor(Factor factor) {
        return factors.contains(factor);
    }
}