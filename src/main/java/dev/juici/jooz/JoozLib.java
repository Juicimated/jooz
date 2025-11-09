package dev.juici.jooz;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoozLib implements ModInitializer {
	public static final String MOD_ID = "jooz";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[JoozLib] Hello Fabric world!");
	}
}