package dev.juici.jooz.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.juici.jooz.component.FactorComponents;
import dev.juici.jooz.factor.Factor;
import dev.juici.jooz.factor.FactorRegistry;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FactorCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("factor")
                .requires(src -> src.hasPermissionLevel(2))
                .then(CommandManager.literal("give")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .then(CommandManager.argument("factor", IdentifierArgumentType.identifier())
                                        .suggests(((ctx, builder) -> {
                                            FactorRegistry.all().keySet().forEach(id -> builder.suggest(id.toString()));
                                            return builder.buildFuture();
                                        }))
                                        .executes(ctx -> {
                                            ServerPlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");

                                            Identifier factorId = IdentifierArgumentType.getIdentifier(ctx, "factor");
                                            Factor factor = FactorRegistry.get(factorId);

                                            if (factor == null) {
                                                ctx.getSource().sendError(Text.literal("Unknown Factor: " + factorId));
                                                return 0;
                                            }

                                            var comp = FactorComponents.get(player);
                                            comp.getActiveGroup().addFactor(factor);

                                            ctx.getSource().sendFeedback(() ->
                                                    Text.literal("Factor " + factorId + " given to " + player.getNameForScoreboard()), false);
                                            return 1;
                                        })
                                ))
                )
                .then(CommandManager.literal("remove")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .then(CommandManager.argument("factor", IdentifierArgumentType.identifier())
                                        .suggests(((ctx, builder) -> {
                                            FactorRegistry.all().keySet().forEach(id -> builder.suggest(id.toString()));
                                            return builder.buildFuture();
                                        }))
                                        .executes(ctx -> {
                                            ServerPlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");

                                            Identifier factorId = IdentifierArgumentType.getIdentifier(ctx, "factor");
                                            Factor factor = FactorRegistry.get(factorId);

                                            if (factor == null) {
                                                ctx.getSource().sendError(Text.literal("Unknown Factor: " + factorId));
                                                return 0;
                                            }

                                            var comp = FactorComponents.get(player);
                                            comp.getActiveGroup().removeFactor(factor);

                                            ctx.getSource().sendFeedback(() ->
                                                    Text.literal("Factor " + factorId + " removed from " + player.getNameForScoreboard()), false);
                                            return 1;
                                        })
                                ))
                )
                .then(CommandManager.literal("list")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> {
                                    ServerPlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");

                                    var comp = FactorComponents.get(player);
                                    var active = comp.getActiveGroup();

                                    ctx.getSource().sendFeedback(() ->
                                            Text.literal("Active Group: " + active.getId() + "\n" +
                                                    "Factors:\n" +
                                                    String.join("\n", active.getFactorIds().stream().map(Identifier::toString).toList())),
                                            false);
                                    return 1;
                                }))
                )
                .then(CommandManager.literal("all")
                        .executes(ctx -> {
                            StringBuilder sb = new StringBuilder("Registered Factors:\n");
                            FactorRegistry.all().keySet().forEach(id -> sb.append(id.toString()).append("\n"));

                            ctx.getSource().sendFeedback(() -> Text.literal(sb.toString()), false);
                            return 1;
                        })
                ));
    }
}