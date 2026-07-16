package com.starwalk.sharedhardcoresurvival.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SharedConfig {
    public static final CommonConfig COMMON = new CommonConfig();

    public static class CommonConfig {
        public final ForgeConfigSpec.IntValue playerCount;
        public final ForgeConfigSpec.IntValue spawnX1;
        public final ForgeConfigSpec.IntValue spawnY1;
        public final ForgeConfigSpec.IntValue spawnZ1;
        public final ForgeConfigSpec.IntValue spawnX2;
        public final ForgeConfigSpec.IntValue spawnY2;
        public final ForgeConfigSpec.IntValue spawnZ2;
        public final ForgeConfigSpec.IntValue spawnX3;
        public final ForgeConfigSpec.IntValue spawnY3;
        public final ForgeConfigSpec.IntValue spawnZ3;
        public final ForgeConfigSpec.IntValue spawnX4;
        public final ForgeConfigSpec.IntValue spawnY4;
        public final ForgeConfigSpec.IntValue spawnZ4;

        public final ForgeConfigSpec spec;

        public CommonConfig() {
            ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

            builder.comment("Shared Hardcore Survival Configuration")
                .push("general");

            playerCount = builder
                .comment("需要的玩家數量（2-4）")
                .defineInRange("playerCount", 4, 2, 4);

            builder.pop();
            builder.push("spawn_points");

           spawnX1 = builder.comment("第一隊出生點 X 座標").defineInRange("spawn1_x", 100, -30000000, 30000000);
spawnY1 = builder.comment("第一隊出生點 Y 座標").defineInRange("spawn1_y", 65, -64, 320);
spawnZ1 = builder.comment("第一隊出生點 Z 座標").defineInRange("spawn1_z", 100, -30000000, 30000000);

spawnX2 = builder.comment("第二隊出生點 X 座標").defineInRange("spawn2_x", -100, -30000000, 30000000);
spawnY2 = builder.comment("第二隊出生點 Y 座標").defineInRange("spawn2_y", 65, -64, 320);
spawnZ2 = builder.comment("第二隊出生點 Z 座標").defineInRange("spawn2_z", 100, -30000000, 30000000);

spawnX3 = builder.comment("第三隊出生點 X 座標").defineInRange("spawn3_x", 100, -30000000, 30000000);
spawnY3 = builder.comment("第三隊出生點 Y 座標").defineInRange("spawn3_y", 65, -64, 320);
spawnZ3 = builder.comment("第三隊出生點 Z 座標").defineInRange("spawn3_z", -100, -30000000, 30000000);

spawnX4 = builder.comment("第四隊出生點 X 座標").defineInRange("spawn4_x", -100, -30000000, 30000000);
spawnY4 = builder.comment("第四隊出生點 Y 座標").defineInRange("spawn4_y", 65, -64, 320);
spawnZ4 = builder.comment("第四隊出生點 Z 座標").defineInRange("spawn4_z", -100, -30000000, 30000000);

            builder.pop();

            this.spec = builder.build();
        }

        public int getPlayerCount() {
            return Math.max(2, Math.min(4, playerCount.get()));
        }

        public int[] getSpawnPoint(int index) {
            return switch (index) {
                case 0 -> new int[]{spawnX1.get(), spawnY1.get(), spawnZ1.get()};
                case 1 -> new int[]{spawnX2.get(), spawnY2.get(), spawnZ2.get()};
                case 2 -> new int[]{spawnX3.get(), spawnY3.get(), spawnZ3.get()};
                case 3 -> new int[]{spawnX4.get(), spawnY4.get(), spawnZ4.get()};
                default -> new int[]{0, 65, 0};
            };
        }
    }
}
