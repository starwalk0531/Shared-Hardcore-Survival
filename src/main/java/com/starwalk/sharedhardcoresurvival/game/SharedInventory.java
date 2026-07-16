package com.starwalk.sharedhardcoresurvival.game;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;

/**
 * 管理四人共享背包
 * 排除：Sophisticated Backpacks、護甲欄、副手、Curios 欄位、神秘遺物能力
 */
public class SharedInventory {
    private ItemStack[] items;
    private static final int SHARED_INVENTORY_SIZE = 27; // 3x9 格

    public SharedInventory() {
        this.items = new ItemStack[SHARED_INVENTORY_SIZE];
        for (int i = 0; i < SHARED_INVENTORY_SIZE; i++) {
            this.items[i] = ItemStack.EMPTY;
        }
    }

    public void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < SHARED_INVENTORY_SIZE) {
            this.items[slot] = stack;
        }
    }

    public ItemStack getItem(int slot) {
        if (slot >= 0 && slot < SHARED_INVENTORY_SIZE) {
            return this.items[slot];
        }
        return ItemStack.EMPTY;
    }

    public ItemStack removeItem(int slot, int amount) {
        ItemStack itemStack = this.items[slot];
        if (itemStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        if (itemStack.getCount() <= amount) {
            this.items[slot] = ItemStack.EMPTY;
            return itemStack;
        }
        ItemStack taken = itemStack.split(amount);
        return taken;
    }

    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack itemStack = this.items[slot];
        this.items[slot] = ItemStack.EMPTY;
        return itemStack;
    }

    public void addItem(ItemStack stack) {
        int i = this.getSlotWithRemainingSpace(stack);
        if (i < 0) {
            i = this.getFreeSlot();
        }

        if (i >= 0) {
            this.addToSlot(i, stack);
        }
    }

    private int getSlotWithRemainingSpace(ItemStack stack) {
        for (int i = 0; i < this.items.length; ++i) {
            if (ItemStack.isSameItemSameTags(this.items[i], stack) && this.items[i].getCount() < this.items[i].getMaxStackSize()) {
                return i;
            }
        }
        return -1;
    }

    private int getFreeSlot() {
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i].isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    private void addToSlot(int slot, ItemStack stack) {
        ItemStack itemStack = this.items[slot];
        if (itemStack.isEmpty()) {
            this.items[slot] = stack.copy();
        } else {
            itemStack.grow(stack.getCount());
        }
    }

    public void save(CompoundTag tag) {
        ListTag listTag = new ListTag();
        for (int i = 0; i < this.items.length; ++i) {
            if (!this.items[i].isEmpty()) {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putByte("Slot", (byte) i);
                this.items[i].save(itemTag);
                listTag.add(itemTag);
            }
        }
        tag.put("Items", listTag);
    }

    public void load(CompoundTag tag) {
        this.items = new ItemStack[SHARED_INVENTORY_SIZE];
        for (int i = 0; i < SHARED_INVENTORY_SIZE; i++) {
            this.items[i] = ItemStack.EMPTY;
        }
        ListTag listTag = tag.getList("Items", Tag.TAG_COMPOUND);
        for (int i = 0; i < listTag.size(); ++i) {
            CompoundTag compoundtag = listTag.getCompound(i);
            byte b0 = compoundtag.getByte("Slot");
            if (b0 >= 0 && b0 < this.items.length) {
                this.items[b0] = ItemStack.of(compoundtag);
            }
        }
    }

    public ItemStack[] getItems() {
        return this.items;
    }

    public int getSize() {
        return SHARED_INVENTORY_SIZE;
    }
}
