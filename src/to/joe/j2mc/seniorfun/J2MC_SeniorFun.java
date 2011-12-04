package to.joe.j2mc.seniorfun;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Result;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerJoinEvent;
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
		J2MC_Manager.getLog().info("Senior Fun module enabled");
		
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN,
		new PlayerListener(){
			public void onPlayerPreLogin(PlayerPreLoginEvent event) {
				String player = event.getName();
				//TODO: check if player is not admin.
				event.disallow(PlayerPreLoginEvent.Result.KICK_OTHER, maintenance_message);
			}
		}
		, Priority.Normal,this);
			
		this.getCommand("kickall").setExecutor(new KickAllCommand(this));
		this.getCommand("madagascar").setExecutor(new MadagascarCommand(this));
		this.getCommand("say").setExecutor(new SayCommand(this));
		this.getCommand("maintenance").setExecutor(new MaintenanceCommand(this));
		
		this.getConfig().options().copyDefaults(true);
		
		this.maintenance_enable = this.getConfig().getBoolean("Access.Maintenance.enable");
		this.maintenance_message = this.getConfig().getString("Access.Maintenance.message");
		
		this.saveConfig();
	}
	
}
