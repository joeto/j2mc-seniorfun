package to.joe.j2mc.seniorfun.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.core.J2MC_Core;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class KickAllCommand extends MasterCommand {

	public KickAllCommand(J2MC_SeniorFun seniorfun) {
		super(seniorfun);
	}

	@Override
	public void exec(CommandSender sender, String commandName, String[] args,
			Player player, String playerName, boolean isPlayer) {
		if (!isPlayer || player.hasPermission("j2mc.senior")) {
			String reason;
			if (args.length == 0) {
				reason = "Count to 30 and try again.";
			} else {
				J2MC_Manager.getCore();
				reason = J2MC_Core.combineSplit(0, args, " ");
			}
			J2MC_Manager.getLog().info(
					ChatColor.RED + "Kicking all players: " + reason);
			if (reason.equalsIgnoreCase("")) {
				reason = "Count to 30 and try again.";
			}
			for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p != null) {
					p.kickPlayer(reason);
				}
			}
		}
	}

}
