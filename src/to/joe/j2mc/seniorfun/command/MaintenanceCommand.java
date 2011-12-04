package to.joe.j2mc.seniorfun.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class MaintenanceCommand extends MasterCommand {

	public MaintenanceCommand(J2MC_SeniorFun seniorfun) {
		super(seniorfun);
	}

	J2MC_SeniorFun seniorfun = new J2MC_SeniorFun();
	boolean maintenance_enable = seniorfun.maintenance_enable;

	public void exec(CommandSender sender, String commandName, String[] args,
			Player player, String playerName, boolean isPlayer) {
		if (!isPlayer || player.hasPermission("j2mc.senior")) {
			if (maintenance_enable == false) {
				J2MC_Manager.getCore().adminAndLog(ChatColor.AQUA + playerName + " has turned on maintenance mode");
				seniorfun.getConfig().set("Access.Maintenance.enable", true);
				seniorfun.saveConfig();
				for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
					if ((p != null) && !p.hasPermission("j2mc.admin")) {
						p.sendMessage(ChatColor.AQUA + "Server entering maintenance mode");
						p.kickPlayer("Server entering maintenance mode");
					}
				}
			}
			else{
				J2MC_Manager.getCore().adminAndLog(ChatColor.AQUA + playerName + " has turned off maintenance mode");
				seniorfun.getConfig().set("Access.Maintenance.enable", false);
				seniorfun.saveConfig();
			}

		}
	}

}
