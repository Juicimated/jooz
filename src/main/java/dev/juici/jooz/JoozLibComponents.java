package dev.juici.jooz;

import dev.juici.jooz.component.FactorComponent;
import dev.juici.jooz.component.FactorComponentImpl;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistryV3;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class JoozLibComponents implements EntityComponentInitializer {
    public static final ComponentKey<FactorComponent> FACTORS =
            ComponentRegistryV3.INSTANCE.getOrCreate(Identifier.of(JoozLib.MOD_ID, "factors"),
                    FactorComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(FACTORS, player -> new FactorComponentImpl(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}