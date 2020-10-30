package com.sargealpha;

import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class DeathListener implements Listener {

    private Swapper swapper;

    public DeathListener(Swapper swapper) {
        this.swapper = swapper;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        if (swapper.isGameRunning() && swapper.isPlayerParticipating(p)) {
            swapper.updateStatus(p, true);
        }
    }

}
