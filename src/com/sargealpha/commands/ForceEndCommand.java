package com.sargealpha.commands;

import com.sargealpha.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class ForceEndCommand implements CommandExecutor {

    private Main plugin;

    public ForceEndCommand(Main plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("dsend");
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
        if (!(p.hasPermission("deathswap.end"))) {
            return false;
        }

        // Forces end.
        this.plugin.swapper.forceEnd();
        return true;
    }

}
