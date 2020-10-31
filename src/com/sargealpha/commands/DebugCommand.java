package com.sargealpha.commands;

import com.sargealpha.*;
import com.sargealpha.tasks.BarTimerTask;
import org.bukkit.*;
import org.bukkit.boss.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class DebugCommand implements CommandExecutor {

    private Main plugin;
    private BossBar visualTimer;

    // CONSTANTS
    public static final int TIME = Swapper.TIME_PER_ROUND;

    public DebugCommand(Main plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("dsdebug");
        command.setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Player check.
        if (!(sender instanceof Player)) {
            return false;
        }
        // Permission check.
        Player p = (Player)sender;
        if (!(p.hasPermission("deathswap.debug"))) {
            return false;
        }

        visualTimer = Bukkit.getServer().createBossBar("Time Before Swap", BarColor.YELLOW, BarStyle.SEGMENTED_20,
                                                        BarFlag.CREATE_FOG);
        visualTimer.setProgress(1.0);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            visualTimer.addPlayer(onlinePlayer);
        }

        BarTimerTask task = new BarTimerTask(plugin, visualTimer, TIME);
        task.runTaskTimer(plugin, 20, 20);
        return true;
    }

}
