package com.sargealpha;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class DeathListener implements Listener {

    private Swapper swapper;

    public DeathListener(Swapper swapper) {
        this.swapper = swapper;
    }

    public boolean changeSwapper(Swapper swapper) {
        if (swapper == null) {
            return false;
        }
        this.swapper = swapper;
        return true;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        p.sendMessage(ChatColor.GRAY + "You died XDDDD"); // DEBUG
        if (swapper.isGameRunning() && swapper.isPlayerParticipating(p)) {
            swapper.updateStatus(p, true);
        }
    }

}
