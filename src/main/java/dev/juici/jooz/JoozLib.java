package dev.juici.jooz;

import dev.juici.jooz.factor.FactorRegistry;
import dev.juici.jooz.factor.PassiveFactor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoozLib implements ModInitializer {
	public static final String MOD_ID = "jooz";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[JoozLib] Initializing...");

        FactorRegistry.register(new PassiveFactor(Identifier.of(MOD_ID, "test"),
                "factor.jooz.test", Identifier.of(MOD_ID, "textures/factor/test.png")));
	}
}