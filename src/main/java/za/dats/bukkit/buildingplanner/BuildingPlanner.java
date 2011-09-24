package za.dats.bukkit.buildingplanner;

import java.util.regex.Pattern;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import za.dats.bukkit.buildingplanner.commands.GivePluginCommand;

public class BuildingPlanner extends JavaPlugin {
    public static BuildingPlanner plugin;
    public static PluginDescriptionFile pdf;
    public static PluginManager pm;
    private final static Pattern COLOR = Pattern.compile("&([a-fA-F0-9])");
    public PlanAreaManager areaManager;
    
    public static void info(String log) {
	plugin.getServer().getLogger().info("[BuildingPlanner] " + log);
    }

    public void warn(String log) {
	plugin.getServer().getLogger().warning("[BuildingPlanner] " + log);
    }
    
    public static void debug(String log) {
	plugin.getServer().getLogger().fine("[BuildingPlanner] " + log);
    }

    public void onDisable() {
	// TODO: Place any custom disable code here.
	info(pdf.getName() + " version " + pdf.getVersion() + " is disabled!");
    }

    public void onEnable() {
	// TODO: Place any custom enable code here, such as registering events
	plugin = this;
	Config.init(this);
        getCommand("plan").setExecutor(new GivePluginCommand());

	pm = getServer().getPluginManager();
	pdf = getDescription();

	areaManager = new PlanAreaManager();
	areaManager.init();
	
	info(pdf.getName() + " version " + pdf.getVersion() + " is enabled!");
    }

    public boolean isSpoutEnabled() {
	if (pm.isPluginEnabled("Spout")) {
	    return true;
	}
	return false;
    }

    public static String color(String msg) {
	return COLOR.matcher(msg).replaceAll("\u00A7$1");
    }

}
