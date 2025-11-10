package dev.juici.jooz;

import dev.juici.jooz.command.FactorCommand;
import dev.juici.jooz.command.FactorGroupCommand;
import dev.juici.jooz.factor.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
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
                Text.translatable("factor.jooz.test"), Identifier.of(MOD_ID, "textures/factor/test.png")));
        FactorRegistry.register(new ActiveFactor(Identifier.of(MOD_ID, "test2"),
                Text.translatable("factor.jooz.test2"), Identifier.of(MOD_ID, "textures/factor/test2.png")));
        FactorRegistry.register(new CosmeticFactor(Identifier.of(MOD_ID, "test3"),
                Text.translatable("factor.jooz.test3"), Identifier.of(MOD_ID, "textures/factor/test3.png")));

        FactorRegistry.registerGroup(new FactorGroup(Identifier.of(MOD_ID, "test_group"),
                Text.translatable("factorgroup.jooz.test_group")));
        FactorRegistry.registerGroup(new FactorGroup(Identifier.of(MOD_ID, "test_group2"),
                Text.translatable("factorgroup.jooz.test_group2")));

        CommandRegistrationCallback.EVENT.register(FactorCommand::register);
        CommandRegistrationCallback.EVENT.register(FactorGroupCommand::register);
	}
}