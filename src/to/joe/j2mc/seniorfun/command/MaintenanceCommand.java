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

	public void exec(CommandSender sender, String commandName, String[] args,
			Player player, boolean isPlayer) {
		J2MC_SeniorFun seniorfun = new J2MC_SeniorFun();
		boolean maintenance_enable = seniorfun.maintenance_enable;
		if (!isPlayer || player.hasPermission("j2mc.senior")) {
			if(seniorfun.getConfig().get("Maintenance.enable") != null){
				J2MC_Manager.getLog().info("Maintenance is " + seniorfun.getConfig().getBoolean("Maintenance.enable"));
			}
			if (maintenance_enable == false) {
				J2MC_Manager.getCore().adminAndLog(ChatColor.AQUA + player.getName() + " has turned on maintenance mode");
				//seniorfun.getConfig().set("Access.Maintenance.enable", true);
				seniorfun.saveConfig();
				for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
					if ((p != null) && !p.hasPermission("j2mc.admin")) {
						p.sendMessage(ChatColor.AQUA + "Server entering maintenance mode");
						p.kickPlayer("Server entering maintenance mode");
					}
				}
			}
			else{
				J2MC_Manager.getCore().adminAndLog(ChatColor.AQUA + player.getName() + " has turned off maintenance mode");
				seniorfun.getConfig().set("Access.Maintenance.enable", false);
				seniorfun.saveConfig();
			}

		}
	}

}
