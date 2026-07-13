package com.starwalk.sharedhardcoresurvival.network;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import com.starwalk.sharedhardcoresurvival.SharedHardcoreSurvival;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        new net.minecraft.resources.ResourceLocation(SharedHardcoreSurvival.MOD_ID, "packets"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    private static int id = 0;

    public static void register() {
        CHANNEL.messageBuilder(SyncGameStatePacket.class, id++, NetworkDirection.PLAY_TO_CLIENT)
            .decoder(SyncGameStatePacket::decode)
            .encoder(SyncGameStatePacket::encode)
            .consumerMainThread(SyncGameStatePacket::handle)
            .add();

        CHANNEL.messageBuilder(SyncSharedStatsPacket.class, id++, NetworkDirection.PLAY_TO_CLIENT)
            .decoder(SyncSharedStatsPacket::decode)
            .encoder(SyncSharedStatsPacket::encode)
            .consumerMainThread(SyncSharedStatsPacket::handle)
            .add();

        CHANNEL.messageBuilder(SyncInventoryPacket.class, id++, NetworkDirection.PLAY_TO_CLIENT)
            .decoder(SyncInventoryPacket::decode)
            .encoder(SyncInventoryPacket::encode)
            .consumerMainThread(SyncInventoryPacket::handle)
            .add();
    }
}
