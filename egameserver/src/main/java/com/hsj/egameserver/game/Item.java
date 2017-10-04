package com.hsj.egameserver.game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class Item<T extends ItemType> implements Entity {

    private T type;
    private int entityId = -1;
    private int itemId = -1; //for database;
    private long gemNumber;
    private long extraStats;
    private int durability;
    private int unknown1;
    private int unknown2;
    private int unknown3;

    private ItemPosition position;

    static private ScheduledExecutorService jobService = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> job;

    public ItemPosition getPosition() {
        return position;
    }

    public void setPosition(ItemPosition position) {
        this.position = position;
    }

    public Item(T itemType) {
        setType(itemType);
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public long getGemNumber() {
        return gemNumber;
    }

    public void setGemNumber(long gemNumber) {
        this.gemNumber = gemNumber;
    }

    public long getExtraStats() {
        return extraStats;
    }

    public void setExtraStats(long extraStats) {
        this.extraStats = extraStats;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getUnknown1() {
        return unknown1;
    }

    public void setUnknown1(int unknown1) {
        this.unknown1 = unknown1;
    }

    public int getUnknown2() {
        return unknown2;
    }

    public void setUnknown2(int unknown2) {
        this.unknown2 = unknown2;
    }

    public int getUnknown3() {
        return unknown3;
    }

    public void setUnknown3(int unknown3) {
        this.unknown3 = unknown3;
    }

    @Override
    public int getEntityId() {
        return 0;
    }

    @Override
    public void setEntityId(int id) {

    }
}
