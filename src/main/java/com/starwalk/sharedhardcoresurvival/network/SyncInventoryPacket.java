package com.starwalk.sharedhardcoresurvival.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class SyncInventoryPacket {
    private final ItemStack[] items;

    public SyncInventoryPacket(ItemStack[] items) {
        this.items = items;
    }

    public SyncInventoryPacket(FriendlyByteBuf buf) {
        int size = buf.readInt();
        this.items = new ItemStack[size];
        for (int i = 0; i < size; i++) {
            this.items[i] = buf.readItem();
        }
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.items.length);
        for (ItemStack item : this.items) {
            buf.writeItem(item);
        }
    }

    public static SyncInventoryPacket decode(FriendlyByteBuf buf) {
        return new SyncInventoryPacket(buf);
    }

    public static void handle(SyncInventoryPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // 客戶端處理背包同步
            // TODO: 更新共享背包顯示
        });
        ctx.get().setPacketHandled(true);
    }

    public ItemStack[] getItems() { return items; }
}
