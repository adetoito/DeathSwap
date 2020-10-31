package com.sargealpha;

import com.sargealpha.commands.*;
import org.bukkit.*;
import org.bukkit.plugin.java.*;

public class Main extends JavaPlugin {

    public Swapper swapper;

    @Override
    public void onEnable() {
        new StartCommand(this);
        new ForceEndCommand(this);
        new DebugCommand(this);
    }
}
