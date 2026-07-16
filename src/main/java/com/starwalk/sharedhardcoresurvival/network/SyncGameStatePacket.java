package com.starwalk.sharedhardcoresurvival.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import com.starwalk.sharedhardcoresurvival.game.GameState;
import java.util.function.Supplier;

public class SyncGameStatePacket {
    private final String state;
    private final int playerCount;
    private final long duration;

    public SyncGameStatePacket(String state, int playerCount, long duration) {
        this.state = state;
        this.playerCount = playerCount;
        this.duration = duration;
    }

    public SyncGameStatePacket(FriendlyByteBuf buf) {
        this.state = buf.readUtf();
        this.playerCount = buf.readInt();
        this.duration = buf.readLong();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.state);
        buf.writeInt(this.playerCount);
        buf.writeLong(this.duration);
    }

    public static SyncGameStatePacket decode(FriendlyByteBuf buf) {
        return new SyncGameStatePacket(buf);
    }

    public static void handle(SyncGameStatePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // 客戶端處理遊戲狀態同步
            // TODO: 更新客戶端的 HUD 顯示
        });
        ctx.get().setPacketHandled(true);
    }
}
