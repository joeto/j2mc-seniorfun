package to.joe.j2mc.seniorfun.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.core.J2MC_Core;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class SayCommand extends MasterCommand{

	public SayCommand(J2MC_SeniorFun seniorfun) {
		super(seniorfun);
	}
	
	@Override
	public void exec(CommandSender sender, String commandName, String[] args,
			Player player, boolean isPlayer) {
		if(!isPlayer || player.hasPermission("j2mc.senior")){
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Dude, you gotta /say SOMETHING");
                return;
            }
            J2MC_Manager.getCore();
			final String message = ChatColor.LIGHT_PURPLE + "[SERVER] " + J2MC_Core.combineSplit(0, args, " ");
			J2MC_Manager.getLog().info(message);
			for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p != null) {
					p.sendMessage(message);
				}
			}
		}
	}
	
}
