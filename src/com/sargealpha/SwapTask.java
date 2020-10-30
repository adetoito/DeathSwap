package com.sargealpha;

import org.bukkit.scheduler.*;

public class SwapTask extends BukkitRunnable {

    private Main plugin;
    private Swapper swapper;

    public SwapTask(Main plugin, Swapper swapper) {
        this.plugin = plugin;
        this.swapper = swapper;
    }

    @Override
    public void run() {
        swapper.decrementTime();
        GameCase gameCase = swapper.getCurrentCase();

        switch(gameCase) {
            case P1_DEAD:
            case P2_DEAD:
            case BOTH_DEAD:
                swapper.processCase(gameCase);
                swapper.end();
                this.cancel();
                break;
            case BOTH_ALIVE:
                break;
        }

        if (swapper.getTime() <= 0) {
            swapper.swap();
            //SwapTask task = new SwapTask(plugin, swapper);
            //task.runTaskTimer(plugin, 20, 20);
            //this.cancel();
        }
    }
}
