package com.sargealpha.commands;

import com.sargealpha.DeathListener;
import com.sargealpha.Main;
import com.sargealpha.Swapper;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class StartCommand implements CommandExecutor {

    private Main plugin;
    private DeathListener currentListener;

    public StartCommand(Main plugin) {
        this.plugin = plugin;
        this.currentListener = null;
        PluginCommand command = plugin.getCommand("dsstart");
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
        if (!(p.hasPermission("deathswap.start"))) {
            return false;
        }
        // Must supply two usernames.
        if (args.length < 2) {
            return false;
        }

        // Gets players
        Player p1 = Bukkit.getPlayerExact(args[0]);
        Player p2 = Bukkit.getPlayerExact(args[1]);
        p1.sendMessage(ChatColor.RED + "Death Swap has begun.");
        p2.sendMessage(ChatColor.RED + "Death Swap has begun.");

        // Creates a swapper for plugin operations.
        Swapper swapper = new Swapper(plugin, p1, p2);
        plugin.swapper = swapper;
        if (currentListener != null) {
            currentListener.changeSwapper(swapper);
        } else {
            currentListener = new DeathListener(swapper);
        }
        Bukkit.getServer().getPluginManager().registerEvents(currentListener, plugin);
        swapper.start();

        return true;
    }

}
