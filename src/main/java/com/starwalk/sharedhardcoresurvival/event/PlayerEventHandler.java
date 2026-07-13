package com.starwalk.sharedhardcoresurvival.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.nbt.CompoundTag;
import com.starwalk.sharedhardcoresurvival.SharedHardcoreSurvival;
import com.starwalk.sharedhardcoresurvival.game.GameState;
import com.starwalk.sharedhardcoresurvival.game.SharedStats;

@Mod.EventBusSubscriber(modid = SharedHardcoreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    /**
     * 玩家受傷事件 - 共享傷害給全隊
     */
    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            GameState gameState = GameState.getInstance();
            SharedStats stats = SharedStats.getInstance();

            if (gameState.getCurrentState() == GameState.State.STARTED) {
                float damage = event.getAmount();
                stats.damageHealth(damage);

                // 如果共享血量 <= 0，全隊死亡
                if (stats.getHealth() <= 0) {
                    gameState.endGame("玩家 " + player.getName().getString() + " 死亡");
                    // TODO: 發送死亡通知給所有玩家
                }
            }
        }
    }

    /**
     * 玩家回血事件 - 共享回血給全隊
     */
    @SubscribeEvent
    public static void onPlayerHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            GameState gameState = GameState.getInstance();
            SharedStats stats = SharedStats.getInstance();

            if (gameState.getCurrentState() == GameState.State.STARTED) {
                float heal = event.getAmount();
                stats.healHealth(heal);
            }
        }
    }

    /**
     * 玩家吃食物事件 - 共享飢餓恢復給全隊
     */
    @SubscribeEvent
    public static void onPlayerFoodEaten(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        GameState gameState = GameState.getInstance();

        if (gameState.getCurrentState() == GameState.State.STARTED && player != null) {
            // TODO: 檢查物品是否為食物，如果是則同步飢餓值
        }
    }

    /**
     * 玩家登出事件 - 移除玩家註冊
     */
    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        GameState gameState = GameState.getInstance();
        gameState.unregisterPlayer(event.getEntity().getUUID());
    }

    /**
     * 玩家登入事件 - 註冊玩家
     */
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        GameState gameState = GameState.getInstance();
        if (gameState.getCurrentState() == GameState.State.WAITING) {
            gameState.registerPlayer(event.getEntity());
        }
    }
}
