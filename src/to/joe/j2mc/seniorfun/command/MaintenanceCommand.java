package to.joe.j2mc.seniorfun.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class MaintenanceCommand extends MasterCommand {
	
	public J2MC_SeniorFun plugin;
	Boolean maintenance_enable;
	public FileConfiguration config;

	public MaintenanceCommand(J2MC_SeniorFun seniorfun) {
		super(seniorfun);
		this.plugin = seniorfun;
		this.config = plugin.getConfig();
	}

	public void exec(CommandSender sender, String commandName, String[] args,
			Player player, boolean isPlayer) {
		if (sender.hasPermission("j2mc.senior")) {
			this.maintenance_enable = config.getBoolean("Maintenance.enable");
			if (maintenance_enable == false) {
				J2MC_Manager.getCore().adminAndLog(ChatColor.AQUA + sender.getName() + " has turned on maintenance mode");
				config.set("Maintenance.enable", true);
				plugin.saveConfig();
				for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
					if ((p != null) && !p.hasPermission("j2mc.admin")) {
						p.sendMessage(ChatColor.AQUA + "Server entering maintenance mode");
						p.kickPlayer("Server entering maintenance mode");
					}
				}
			}
			else{
				J2MC_Manager.getCore().adminAndLog(ChatColor.AQUA + sender.getName() + " has turned off maintenance mode");
				config.set("Maintenance.enable", false);
				plugin.saveConfig();
			}
		}
	}

}