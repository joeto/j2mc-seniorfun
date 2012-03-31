package to.joe.j2mc.seniorfun.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class MaintenanceCommand extends MasterCommand {

    public J2MC_SeniorFun plugin;

    public MaintenanceCommand(J2MC_SeniorFun seniorfun) {
        super(seniorfun);
        this.plugin = seniorfun;
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        this.plugin.maintenance_enable = this.plugin.getConfig().getBoolean("Maintenance.enable");
        if (this.plugin.maintenance_enable == false) {
            J2MC_Manager.getCore().adminAndLog(ChatColor.AQUA + sender.getName() + " has turned on maintenance mode");
            this.plugin.maintenance_enable = true;
            for (final Player p : this.plugin.getServer().getOnlinePlayers()) {
                if ((p != null)) {
                    p.sendMessage(ChatColor.AQUA + "Server entering maintenance mode");
                    if (!p.hasPermission("j2mc.admin")) {
                        p.kickPlayer("Server entering maintenance mode");
                    }
                }
            }
        } else {
            J2MC_Manager.getCore().adminAndLog(ChatColor.AQUA + sender.getName() + " has turned off maintenance mode");
            this.plugin.maintenance_enable = false;
        }
        this.plugin.getConfig().set("Maintenance.enable", this.plugin.maintenance_enable);
        this.plugin.saveConfig();
    }

}