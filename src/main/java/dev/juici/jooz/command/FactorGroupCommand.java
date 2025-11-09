package dev.juici.jooz.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.juici.jooz.component.FactorComponents;
import dev.juici.jooz.factor.FactorGroup;
import dev.juici.jooz.factor.FactorRegistry;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FactorGroupCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("factorgroup")
                .requires(src -> src.hasPermissionLevel(2))
                .then(CommandManager.literal("add")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .then(CommandManager.argument("factorGroup", IdentifierArgumentType.identifier())
                                        .suggests(((ctx, builder) -> {
                                            FactorRegistry.allGroups().keySet().forEach(id -> builder.suggest(id.toString()));
                                            return builder.buildFuture();
                                        }))
                                        .executes(ctx -> {
                                            ServerPlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");

                                            Identifier groupId = IdentifierArgumentType.getIdentifier(ctx, "factorGroup");
                                            FactorGroup group = FactorRegistry.getGroup(groupId);

                                            if (group == null) {
                                                ctx.getSource().sendError(Text.literal("Unknown Factor Group: " + groupId));
                                                return 0;
                                            }

                                            var comp = FactorComponents.get(player);
                                            comp.addGroup(group);

                                            ctx.getSource().sendFeedback(() ->
                                                    Text.literal("Factor Group " + groupId + " added to " + player.getNameForScoreboard()), false);
                                            return 1;
                                        })
                                ))
                )
                .then(CommandManager.literal("remove")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .then(CommandManager.argument("factorGroup", IdentifierArgumentType.identifier())
                                        .suggests(((ctx, builder) -> {
                                            FactorRegistry.allGroups().keySet().forEach(id -> builder.suggest(id.toString()));
                                            return builder.buildFuture();
                                        }))
                                        .executes(ctx -> {
                                            ServerPlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");

                                            Identifier groupId = IdentifierArgumentType.getIdentifier(ctx, "factorGroup");
                                            FactorGroup group = FactorRegistry.getGroup(groupId);

                                            if (group == null) {
                                                ctx.getSource().sendError(Text.literal("Unknown Factor Group: " + groupId));
                                                return 0;
                                            }

                                            var comp = FactorComponents.get(player);
                                            comp.removeGroup(group);

                                            ctx.getSource().sendFeedback(() ->
                                                    Text.literal("Factor Group " + groupId + " removed from " + player.getNameForScoreboard()), false);
                                            return 1;
                                        })
                                ))
                )
                .then(CommandManager.literal("switch")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .then(CommandManager.argument("factorGroup", IdentifierArgumentType.identifier())
                                        .suggests((ctx, builder) -> {
                                            FactorRegistry.allGroups().keySet().forEach(id -> builder.suggest(id.toString()));
                                            return builder.buildFuture();
                                        })
                                        .executes(ctx -> {
                                            ServerPlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");

                                            Identifier groupId = IdentifierArgumentType.getIdentifier(ctx, "factorGroup");
                                            FactorGroup group = FactorRegistry.getGroup(groupId);

                                            if (group == null) {
                                                ctx.getSource().sendError(Text.literal("Unknown Factor Group: " + groupId));
                                                return 0;
                                            }

                                            var comp = FactorComponents.get(player);
                                            if (!comp.getFactorGroups().contains(group)) {
                                                ctx.getSource().sendError(Text.literal("Player does not have Factor Group: " + groupId));
                                                return 0;
                                            }

                                            comp.setActiveGroup(group);

                                            ctx.getSource().sendFeedback(() ->
                                                    Text.literal("Switched Active Group to: " + groupId), false);
                                            return 1;
                                        })
                                ))
                )
                .then(CommandManager.literal("list")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(ctx -> {
                                    ServerPlayerEntity player = EntityArgumentType.getPlayer(ctx, "player");

                                    var comp = FactorComponents.get(player);

                                    ctx.getSource().sendFeedback(() ->
                                                    Text.literal("Factor Groups:\n" +
                                                            String.join("\n", comp.getFactorGroups().stream().map(group -> group.getId().toString()).toList())),
                                            false);
                                    return 1;
                                }))
                )
                .then(CommandManager.literal("all")
                        .executes(ctx -> {
                            StringBuilder sb = new StringBuilder("Registered Factor Groups:\n");
                            FactorRegistry.allGroups().keySet().forEach(id -> sb.append(id.toString()).append("\n"));

                            ctx.getSource().sendFeedback(() -> Text.literal(sb.toString()), false);
                            return 1;
                        })
                ));
    }
}