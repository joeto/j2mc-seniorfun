package to.joe.j2mc.seniorfun;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.seniorfun.command.FireAllAdminscommand;
import to.joe.j2mc.seniorfun.command.KickAllCommand;
import to.joe.j2mc.seniorfun.command.MadagascarCommand;
import to.joe.j2mc.seniorfun.command.MaintenanceCommand;
import to.joe.j2mc.seniorfun.command.SayCommand;

public class J2MC_SeniorFun extends JavaPlugin {

    public boolean maintenance_enable;
    private String maintenance_message;

    @Override
    public void onDisable() {
        this.getLogger().info("Senior Fun module disabled");
    }

    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);

        this.maintenance_enable = this.getConfig().getBoolean("Maintenance.enable");
        this.maintenance_message = this.getConfig().getString("Maintenance.message");

        this.getCommand("kickall").setExecutor(new KickAllCommand(this));
        this.getCommand("madagascar").setExecutor(new MadagascarCommand(this));
        this.getCommand("say").setExecutor(new SayCommand(this));
        this.getCommand("maintenance").setExecutor(new MaintenanceCommand(this));
        this.getCommand("firealladmins").setExecutor(new FireAllAdminscommand(this));
        
        this.saveConfig();

        this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                J2MC_SeniorFun.this.getLogger().info("Senior Fun module enabled");
                if (J2MC_SeniorFun.this.maintenance_enable) {
                    J2MC_SeniorFun.this.getLogger().info("Server is in maintenance mode.");
                }
            }
        });
        this.getServer().getPluginManager().registerEvents(new Listener() {
            @SuppressWarnings("unused")
            @EventHandler(priority = EventPriority.LOWEST)
            public void onPlayerPreLogin(PlayerPreLoginEvent event) {
                if (!J2MC_Manager.getPermissions().isAdmin(event.getName()) && J2MC_SeniorFun.this.maintenance_enable) {
                    event.disallow(PlayerPreLoginEvent.Result.KICK_OTHER, J2MC_SeniorFun.this.maintenance_message);
                }
            }
        }, this);
    }

}
