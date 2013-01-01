package to.joe.j2mc.seniorfun.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class SetSpawnCommand extends MasterCommand<J2MC_SeniorFun> {

    public SetSpawnCommand(J2MC_SeniorFun seniorfun) {
        super(seniorfun);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (!isPlayer) {
            sender.sendMessage(ChatColor.RED + "Gotta be in game");
            return;
        }
        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "/setspawn yes omg yes");
            return;
        }
        final Location location = player.getLocation();
        player.getWorld().setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

}
