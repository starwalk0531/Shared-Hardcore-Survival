package com.starwalk.sharedhardcoresurvival.game;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * 定義四個遊戲開始點
 */
public class SpawnPoint {
    private final BlockPos position;
    private final String name;

    public SpawnPoint(BlockPos position, String name) {
        this.position = position;
        this.name = name;
    }

    public BlockPos getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    /**
     * 創建默認的四個角落生成點
     * 假設地圖中心在 0,0
     */
    public static SpawnPoint[] getDefaultSpawnPoints() {
        return new SpawnPoint[]{
            new SpawnPoint(new BlockPos(100, 65, 100), "東北角"),
            new SpawnPoint(new BlockPos(-100, 65, 100), "西北角"),
            new SpawnPoint(new BlockPos(100, 65, -100), "東南角"),
            new SpawnPoint(new BlockPos(-100, 65, -100), "西南角")
        };
    }
}
