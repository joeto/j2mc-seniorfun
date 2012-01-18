package to.joe.j2mc.seniorfun;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.seniorfun.command.KickAllCommand;
import to.joe.j2mc.seniorfun.command.MadagascarCommand;
import to.joe.j2mc.seniorfun.command.MaintenanceCommand;
import to.joe.j2mc.seniorfun.command.SayCommand;

public class J2MC_SeniorFun extends JavaPlugin{
	
	public boolean maintenance_enable;
	public String maintenance_message;

	@Override
	public void onDisable() {
		J2MC_Manager.getLog().info("Senior Fun module disabled");
	}

	@Override
	public void onEnable() {
		this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run(){
				J2MC_Manager.getLog().info("Senior Fun module enabled");
				if(maintenance_enable){
					J2MC_Manager.getLog().info("Server is in maintenance mode.");
				}
			}
		});
		
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_PRELOGIN,
		new PlayerListener(){
			public void onPlayerPreLogin(PlayerPreLoginEvent event) {
				//TODO: check if player is admin
				if(maintenance_enable){
				event.disallow(PlayerPreLoginEvent.Result.KICK_OTHER, maintenance_message);
				}
			}
		}
		, Priority.Normal,this);
		
		this.maintenance_enable = this.getConfig().getBoolean("Maintenance.enable");
		this.maintenance_message = this.getConfig().getString("Maintenance.message");
			
		this.getCommand("kickall").setExecutor(new KickAllCommand(this));
		this.getCommand("madagascar").setExecutor(new MadagascarCommand(this));
		this.getCommand("say").setExecutor(new SayCommand(this));
		this.getCommand("maintenance").setExecutor(new MaintenanceCommand(this));
		
		this.getConfig().options().copyDefaults(true);
		
		this.saveConfig();
	}
	
}
