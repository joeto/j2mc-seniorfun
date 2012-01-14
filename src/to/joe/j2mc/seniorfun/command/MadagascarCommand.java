package to.joe.j2mc.seniorfun.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class MadagascarCommand extends MasterCommand {

	public MadagascarCommand(J2MC_SeniorFun seniorfun) {
		super(seniorfun);
	}

	public void exec(CommandSender sender, String commandName, String[] args,
			Player player, boolean isPlayer) {
		if (!isPlayer || player.hasPermission("j2mc.senior")) {
			J2MC_Manager.getCore().adminAndLog(
					player.getName() + " wants to SHUT. DOWN. EVERYTHING.");
			/*
			 * if (this.config.irc_enable) { 
			 * 		if(name.equalsIgnoreCase("console")) {
			 * 			this.irc.getBot().sendMessage(this.config.irc_admin_channel, "A MAN IN BRAZIL IS COUGHING"); 
			 * 		} 
			 * this.config.irc_enable = false;
			 * this.irc.getBot().quitServer("SHUT. DOWN. EVERYTHING."); 
			 * }
			 */
			for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p != null) {
					p.kickPlayer("We'll be back after these brief messages");
				}
			}
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
		}
	}

}
