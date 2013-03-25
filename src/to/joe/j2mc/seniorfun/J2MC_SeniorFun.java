package to.joe.j2mc.seniorfun;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import to.joe.j2mc.seniorfun.command.*;

public class J2MC_SeniorFun extends JavaPlugin implements Listener {

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
        this.getCommand("firealladmins").setExecutor(new FireAllAdminsCommand(this));
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        this.getCommand("shower").setExecutor(new ShowerCommand(this));

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
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "SeniorFun");
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (J2MC_SeniorFun.this.maintenance_enable && !event.getPlayer().hasPermission("j2mc.maintenance.override")) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            try {
                out.writeUTF("Maintenance");
                out.writeUTF(J2MC_SeniorFun.this.maintenance_message);
            } catch (IOException e) {
                //Nope
            }

            event.getPlayer().sendPluginMessage(this, "SeniorFun", b.toByteArray());

            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, J2MC_SeniorFun.this.maintenance_message);
        }
    }

}
