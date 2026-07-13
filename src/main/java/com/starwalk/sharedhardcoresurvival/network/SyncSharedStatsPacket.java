package com.starwalk.sharedhardcoresurvival.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class SyncSharedStatsPacket {
    private final float health;
    private final int hunger;
    private final float saturation;

    public SyncSharedStatsPacket(float health, int hunger, float saturation) {
        this.health = health;
        this.hunger = hunger;
        this.saturation = saturation;
    }

    public SyncSharedStatsPacket(FriendlyByteBuf buf) {
        this.health = buf.readFloat();
        this.hunger = buf.readInt();
        this.saturation = buf.readFloat();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(this.health);
        buf.writeInt(this.hunger);
        buf.writeFloat(this.saturation);
    }

    public static SyncSharedStatsPacket decode(FriendlyByteBuf buf) {
        return new SyncSharedStatsPacket(buf);
    }

    public static void handle(SyncSharedStatsPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // 客戶端處理共享統計數據同步
            // TODO: 更新所有玩家的血量、飢餓和飽和度
        });
        ctx.get().setPacketHandled(true);
    }

    public float getHealth() { return health; }
    public int getHunger() { return hunger; }
    public float getSaturation() { return saturation; }
}
