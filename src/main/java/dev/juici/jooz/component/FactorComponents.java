package dev.juici.jooz.component;

import dev.juici.jooz.JoozLibComponents;
import net.minecraft.entity.player.PlayerEntity;

public class FactorComponents {
    public static FactorComponent get(PlayerEntity player) {
        return JoozLibComponents.FACTORS.get(player);
    }
}