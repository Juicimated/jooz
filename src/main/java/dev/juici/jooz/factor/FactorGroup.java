package dev.juici.jooz.factor;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class FactorGroup {
    private final Identifier id;
    private final Text key;
    private final Set<Identifier> factorIds = new HashSet<>();

    public FactorGroup(Identifier id, Text key) {
        this.id = id;
        this.key = key;
    }

    public Identifier getId() {
        return id;
    }

    public Text getKey() {
        return key;
    }

    public void addFactor(Factor factor) {
        factorIds.add(factor.getId());
    }

    public void removeFactor(Factor factor) {
        factorIds.remove(factor.getId());
    }

    public boolean hasFactor(Factor factor) {
        return factorIds.contains(factor.getId());
    }

    public Set<Identifier> getFactorIds() {
        return factorIds;
    }

    public Set<Factor> resolveFactors() {
        return factorIds.stream()
                .map(FactorRegistry::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}