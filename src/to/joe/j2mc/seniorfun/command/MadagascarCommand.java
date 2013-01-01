package to.joe.j2mc.seniorfun.command;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class MadagascarCommand extends MasterCommand<J2MC_SeniorFun> {

    public MadagascarCommand(J2MC_SeniorFun seniorfun) {
        super(seniorfun);
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        J2MC_Manager.getCore().adminAndLog(sender.getName() + " wants to SHUT. DOWN. EVERYTHING.");
        for (final Player p : this.plugin.getServer().getOnlinePlayers()) {
            if (p != null) {
                p.saveData();
                p.kickPlayer("We'll be back after these brief messages");
            }
        }
        for (final World world : this.plugin.getServer().getWorlds()) {
            world.save();
        }
        this.plugin.getServer().shutdown();
    }

}
