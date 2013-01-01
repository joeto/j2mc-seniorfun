package to.joe.j2mc.seniorfun.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.core.J2MC_Core;
import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class SayCommand extends MasterCommand<J2MC_SeniorFun> {

    public SayCommand(J2MC_SeniorFun seniorfun) {
        super(seniorfun);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Dude, you gotta /say SOMETHING");
            return;
        }
        final String message = J2MC_Core.combineSplit(0, args, " ");
        J2MC_Manager.getCore().adminAndLog(ChatColor.LIGHT_PURPLE + "[" + sender.getName() + "] " + message);
        J2MC_Manager.getCore().messageNonAdmin(ChatColor.LIGHT_PURPLE + "[SERVER] " + message);
    }

}
