package com.starwalk.sharedhardcoresurvival.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import com.starwalk.sharedhardcoresurvival.game.GameState;
import com.starwalk.sharedhardcoresurvival.game.SharedStats;

public class SharedCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("shared")
            .then(Commands.literal("start")
                .executes(SharedCommand::startGame))
            .then(Commands.literal("reset")
                .executes(SharedCommand::resetGame))
            .then(Commands.literal("end")
                .executes(SharedCommand::endGame))
            .then(Commands.literal("info")
                .executes(SharedCommand::showInfo))
            .then(Commands.literal("bag")
                .executes(SharedCommand::openBag)));
    }

    private static int startGame(CommandContext<CommandSourceStack> context) {
        GameState gameState = GameState.getInstance();
        CommandSourceStack source = context.getSource();

        if (!gameState.isGameReady()) {
            source.sendFailure(Component.literal("玩家人數不足！目前: " + gameState.getPlayerCount() + "/4"));
            return 0;
        }

        gameState.startGame();
        source.sendSuccess(() -> Component.literal("挑戰已開始！"), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int resetGame(CommandContext<CommandSourceStack> context) {
        GameState gameState = GameState.getInstance();
        SharedStats stats = SharedStats.getInstance();
        CommandSourceStack source = context.getSource();

        gameState.reset();
        stats.reset();
        source.sendSuccess(() -> Component.literal("遊戲已重置！"), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int endGame(CommandContext<CommandSourceStack> context) {
        GameState gameState = GameState.getInstance();
        CommandSourceStack source = context.getSource();

        gameState.endGame("管理員結束遊戲");
        source.sendSuccess(() -> Component.literal("遊戲已結束！"), true);
        return Command.SINGLE_SUCCESS;
    }

    private static int showInfo(CommandContext<CommandSourceStack> context) {
        GameState gameState = GameState.getInstance();
        CommandSourceStack source = context.getSource();

        String info = String.format(
            "遊戲狀態: %s\n玩家數: %d/4\n已註冊玩家: %s",
            gameState.getCurrentState(),
            gameState.getPlayerCount(),
            gameState.getPlayerNames().values()
        );
        source.sendSuccess(() -> Component.literal(info), false);
        return Command.SINGLE_SUCCESS;
    }

    private static int openBag(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> Component.literal("打開共享背包"), true);
        // TODO: 實現背包 UI
        return Command.SINGLE_SUCCESS;
    }
}
