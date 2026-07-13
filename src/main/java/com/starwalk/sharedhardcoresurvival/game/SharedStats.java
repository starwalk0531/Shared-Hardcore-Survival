package com.starwalk.sharedhardcoresurvival.game;

/**
 * 管理共享的血量、飢餓、飽和度
 */
public class SharedStats {
    private static SharedStats instance = null;
    
    private float sharedHealth = 20.0f;
    private int sharedHunger = 20;
    private float sharedSaturation = 5.0f;
    
    private static final float MAX_HEALTH = 20.0f;
    private static final int MAX_HUNGER = 20;
    private static final float MAX_SATURATION = 5.0f;

    private SharedStats() {}

    public static synchronized SharedStats getInstance() {
        if (instance == null) {
            instance = new SharedStats();
        }
        return instance;
    }

    public void setHealth(float health) {
        this.sharedHealth = Math.max(0, Math.min(health, MAX_HEALTH));
    }

    public float getHealth() {
        return this.sharedHealth;
    }

    public void damageHealth(float damage) {
        this.sharedHealth = Math.max(0, this.sharedHealth - damage);
    }

    public void healHealth(float amount) {
        this.sharedHealth = Math.min(MAX_HEALTH, this.sharedHealth + amount);
    }

    public void setHunger(int hunger) {
        this.sharedHunger = Math.max(0, Math.min(hunger, MAX_HUNGER));
    }

    public int getHunger() {
        return this.sharedHunger;
    }

    public void decreaseHunger(int amount) {
        this.sharedHunger = Math.max(0, this.sharedHunger - amount);
    }

    public void restoreHunger(int amount) {
        this.sharedHunger = Math.min(MAX_HUNGER, this.sharedHunger + amount);
    }

    public void setSaturation(float saturation) {
        this.sharedSaturation = Math.max(0, Math.min(saturation, MAX_SATURATION));
    }

    public float getSaturation() {
        return this.sharedSaturation;
    }

    public void addSaturation(float amount) {
        this.sharedSaturation = Math.min(MAX_SATURATION, this.sharedSaturation + amount);
    }

    public boolean isTeamAlive() {
        return this.sharedHealth > 0;
    }

    public void reset() {
        this.sharedHealth = MAX_HEALTH;
        this.sharedHunger = MAX_HUNGER;
        this.sharedSaturation = MAX_SATURATION;
    }
}
