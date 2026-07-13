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

            spawnX1 = builder.comment("第一個生成點 X 座標").define("spawn1_x", 100);
            spawnY1 = builder.comment("第一個生成點 Y 座標").define("spawn1_y", 65);
            spawnZ1 = builder.comment("第一個生成點 Z 座標").define("spawn1_z", 100);

            spawnX2 = builder.comment("第二個生成點 X 座標").define("spawn2_x", -100);
            spawnY2 = builder.comment("第二個生成點 Y 座標").define("spawn2_y", 65);
            spawnZ2 = builder.comment("第二個生成點 Z 座標").define("spawn2_z", 100);

            spawnX3 = builder.comment("第三個生成點 X 座標").define("spawn3_x", 100);
            spawnY3 = builder.comment("第三個生成點 Y 座標").define("spawn3_y", 65);
            spawnZ3 = builder.comment("第三個生成點 Z 座標").define("spawn3_z", -100);

            spawnX4 = builder.comment("第四個生成點 X 座標").define("spawn4_x", -100);
            spawnY4 = builder.comment("第四個生成點 Y 座標").define("spawn4_y", 65);
            spawnZ4 = builder.comment("第四個生成點 Z 座標").define("spawn4_z", -100);

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
