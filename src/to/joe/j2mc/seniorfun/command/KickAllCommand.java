package to.joe.j2mc.seniorfun.command;

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
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (sender.hasPermission("j2mc.senior")) {
            String reason;
            if (args.length == 0) {
                reason = "Count to 30 and try again.";
            } else {
                J2MC_Manager.getCore();
                reason = J2MC_Core.combineSplit(0, args, " ");
            }
            this.plugin.getLogger().info(ChatColor.RED + "Kicking all players by " + sender.getName() + ": " + reason);
            if (reason.equalsIgnoreCase("")) {
                reason = "Count to 30 and try again.";
            }
            for (final Player p : this.plugin.getServer().getOnlinePlayers()) {
                if (p != null) {
                    p.kickPlayer(reason);
                }
            }
        }
    }

}
