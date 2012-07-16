package to.joe.j2mc.seniorfun.command;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class ShowerCommand extends MasterCommand {
    
    public J2MC_SeniorFun plugin;
    private Random random = new Random();

    public ShowerCommand(J2MC_SeniorFun seniorfun) {
        super(seniorfun);
        this.plugin = seniorfun;
    }
    
    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer) {
            final Location target = player.getTargetBlock(null, 50).getLocation();
            for(int x=0;x<30;x++){
                final Location tar = new Location(target.getWorld(), target.getX(), target.getY(), target.getZ());
                this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new singleFireball(tar.add((this.random.nextInt(6)-3)*5,40,((this.random.nextInt(6)-3)*5)-20)), this.random.nextInt(60));
            }
        }
    }
    
    private class singleFireball implements Runnable{
        
        private final Location target;
        
        public singleFireball(Location target){
            this.target=target;
        }
        @Override
        public void run() {
            final Fireball fireball=(this.target.getWorld()).spawn(this.target, Fireball.class);
            fireball.setVelocity(new Vector(0, -4, 1));
            fireball.setIsIncendiary(true);
            fireball.setYield(3);
        }
        
    }

}
