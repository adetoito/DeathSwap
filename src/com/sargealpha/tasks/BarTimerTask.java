package com.sargealpha.tasks;

import com.sargealpha.Main;
import com.sargealpha.Swapper;
import org.bukkit.boss.*;
import org.bukkit.scheduler.*;

public class BarTimerTask extends BukkitRunnable {

    private Main plugin;
    private int seconds;
    private BossBar visualTimer;

    public BarTimerTask(Main plugin, BossBar visualTimer, int seconds) {
        this.plugin = plugin;
        this.visualTimer = visualTimer;
        this.seconds = seconds;
    }

    @Override
    public void run() {
        seconds--;
        visualTimer.setProgress((double)seconds / Swapper.TIME_PER_ROUND);
        if (seconds == 10) {
            visualTimer.setColor(BarColor.RED);
        }
        if (seconds <= 0) {
            visualTimer.setProgress(1.0);
            visualTimer.setColor(BarColor.YELLOW);
            BarTimerTask newTask = new BarTimerTask(plugin, visualTimer, Swapper.TIME_PER_ROUND);
            newTask.runTaskTimer(plugin, 20, 20);
            this.cancel();
        }
    }

}
