package com.starwalk.sharedhardcoresurvival.game;

import net.minecraft.world.entity.player.Player;
import java.util.*;

/**
 * 管理遊戲狀態：等待、進行、結束
 */
public class GameState {
    public enum State {
        WAITING,      // 等待玩家加入
        STARTED,      // 挑戰已開始
        ENDED         // 挑戰已結束
    }

    private static GameState instance = null;
    
    private State currentState = State.WAITING;
    private Set<UUID> registeredPlayers = new LinkedHashSet<>();
    private Map<UUID, String> playerNames = new HashMap<>();
    private long startTime = 0;
    private boolean teamDefeated = false;
    private String defeatReason = "";
    
    private static final int MAX_PLAYERS = 4;

    private GameState() {}

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void registerPlayer(Player player) {
        if (registeredPlayers.size() < MAX_PLAYERS) {
            registeredPlayers.add(player.getUUID());
            playerNames.put(player.getUUID(), player.getName().getString());
        }
    }

    public void unregisterPlayer(UUID playerUUID) {
        registeredPlayers.remove(playerUUID);
        playerNames.remove(playerUUID);
    }

    public boolean isGameReady() {
        return registeredPlayers.size() == MAX_PLAYERS;
    }

    public void startGame() {
        if (isGameReady()) {
            currentState = State.STARTED;
            startTime = System.currentTimeMillis();
            teamDefeated = false;
        }
    }

    public void endGame(String reason) {
        currentState = State.ENDED;
        teamDefeated = true;
        defeatReason = reason;
    }

    public void reset() {
        currentState = State.WAITING;
        registeredPlayers.clear();
        playerNames.clear();
        startTime = 0;
        teamDefeated = false;
        defeatReason = "";
    }

    public State getCurrentState() {
        return currentState;
    }

    public Set<UUID> getRegisteredPlayers() {
        return new HashSet<>(registeredPlayers);
    }

    public Map<UUID, String> getPlayerNames() {
        return new HashMap<>(playerNames);
    }

    public int getPlayerCount() {
        return registeredPlayers.size();
    }

    public long getGameDuration() {
        if (startTime == 0) return 0;
        return System.currentTimeMillis() - startTime;
    }

    public String getGameDurationFormatted() {
        long duration = getGameDuration();
        long seconds = duration / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        
        seconds = seconds % 60;
        minutes = minutes % 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public boolean isTeamDefeated() {
        return teamDefeated;
    }

    public String getDefeatReason() {
        return defeatReason;
    }
}
