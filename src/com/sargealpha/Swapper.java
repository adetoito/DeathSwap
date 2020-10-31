package com.sargealpha;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.*;

import java.util.*;

public class Swapper {

    // Constants
    private static final int TIME_PER_ROUND = 300; // 60 = 1 minute

    // Base Information
    private Main plugin;
    private PlayerPair pair;

    // Game Information
    private boolean isRunning;
    private int timer;
    private BukkitRunnable currentTask;

    public Swapper(Main plugin, Player p1, Player p2) {
        this.plugin = plugin;
        this.timer = TIME_PER_ROUND;
        this.isRunning = false;
        this.pair = new PlayerPair(p1, p2);
    }

    public int getTime() {
        return timer;
    }

    public void start() {
        this.isRunning = true;
        this.currentTask = new SwapTask(plugin, this);
        this.currentTask.runTaskTimer(plugin, 20, 20);
    }

    public void end() {
        this.isRunning = false;
        this.currentTask = null;
    }

    public boolean forceEnd() {
        if (currentTask == null || currentTask.isCancelled()) {
            return false;
        }
        currentTask.cancel();
        this.isRunning = false;
        currentTask = null;
        return true;
    }

    public void swap() {
        Player p1 = pair.getP1(), p2 = pair.getP2();
        Location loc1 = p1.getLocation();
        Location loc2 = p2.getLocation();
        p1.teleport(loc2);
        p1.sendMessage(ChatColor.GOLD + "Teleported.");
        p2.teleport(loc1);
        p2.sendMessage(ChatColor.GOLD + "Teleported.");

        if (!(currentTask.isCancelled())) {
            currentTask.cancel();
        }
        this.timer = TIME_PER_ROUND;
        currentTask = new SwapTask(plugin, this);
        currentTask.runTaskTimer(plugin, 20, 20);
    }

    public void sendTimeRemaining() {
        Set<Player> players = pair.getParticipants();
        String message = ChatColor.YELLOW + "Swapping in " + ChatColor.WHITE + timer;
        for (Player p : players) {
            p.sendMessage(message);
        }
    }

    public void processCase(GameCase gameCase) {
        Set<Player> players = pair.getParticipants();

        String endMessage = "";
        String p1Name = pair.getP1().getDisplayName();
        String p2Name = pair.getP2().getDisplayName();

        switch(gameCase) {
            case P1_DEAD:
                endMessage = ChatColor.AQUA + p2Name + ChatColor.DARK_AQUA + " wins!";
                break;
            case P2_DEAD:
                endMessage = ChatColor.AQUA + p1Name + ChatColor.DARK_AQUA + " wins!";
                break;
            case BOTH_DEAD:
                endMessage = ChatColor.DARK_AQUA + "Both players died. No one wins.";
                break;
            case BOTH_ALIVE:
                break;
        }

        for (Player p : players) {
            p.sendMessage(endMessage);
        }
    }

    public GameCase getCurrentCase() {
        return pair.getCurrentCase();
    }

    public int decrementTime() {
        timer--;
        return timer;
    }

    public boolean updateStatus(Player p, boolean status) {
        return pair.updateStatus(p, status);
    }

    public boolean isGameRunning() {
        return this.isRunning;
    }

    public boolean isPlayerParticipating(Player p) {
        return pair.isPlayerParticipating(p);
    }

}
