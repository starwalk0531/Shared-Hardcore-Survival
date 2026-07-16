package com.starwalk.sharedhardcoresurvival.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import com.starwalk.sharedhardcoresurvival.SharedHardcoreSurvival;
import com.starwalk.sharedhardcoresurvival.game.GameState;
import com.starwalk.sharedhardcoresurvival.game.SharedStats;

@Mod.EventBusSubscriber(modid = SharedHardcoreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HudRenderer {
    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        if (minecraft.screen != null) {
            return;
        }

        GameState gameState = GameState.getInstance();
        SharedStats stats = SharedStats.getInstance();

        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();

        renderGameInfo(event.getGuiGraphics(), screenWidth, screenHeight, gameState, stats);
    }

    private static void renderGameInfo(GuiGraphics guiGraphics, int screenWidth, int screenHeight, GameState gameState, SharedStats stats) {
        int x = 10;
        int y = 10;

        // 遊戲狀態
        String status = "狀態: " + gameState.getCurrentState();
        guiGraphics.drawString(minecraft.font, status, x, y, 0xFFFFFF, false);

        y += 12;

        // 玩家人數
        String playerCount = "玩家: " + gameState.getPlayerCount() + "/4";
        guiGraphics.drawString(minecraft.font, playerCount, x, y, 0xFFFFFF, false);

        y += 12;

        // 遊戲時間與共享屬性
        if (gameState.getCurrentState() == GameState.State.STARTED) {
            String duration = "時間: " + gameState.getGameDurationFormatted();
            guiGraphics.drawString(minecraft.font, duration, x, y, 0xFFFFFF, false);

            y += 12;

            // 共享血量
            String health = String.format("血量: %.1f/20", stats.getHealth());
            int healthColor = stats.getHealth() <= 5 ? 0xFF0000 : 0x00FF00;
            guiGraphics.drawString(minecraft.font, health, x, y, healthColor, false);

            y += 12;

            // 共享飢餓
            String hunger = String.format("飢餓: %d/20", stats.getHunger());
            guiGraphics.drawString(minecraft.font, hunger, x, y, 0xFFD700, false);

            y += 12;

            // 共享飽和度
            String saturation = String.format("飽和: %.1f/5", stats.getSaturation());
            guiGraphics.drawString(minecraft.font, saturation, x, y, 0xFF69B4, false);
            
            y += 12;
        }

        // 挑戰結束信息
        if (gameState.getCurrentState() == GameState.State.ENDED) {
            String endMessage = "挑戰結束: " + gameState.getDefeatReason();
            int messageColor = 0xFF0000;
            guiGraphics.drawString(minecraft.font, endMessage, x, y, messageColor, false);
        }
    }
}