package to.joe.j2mc.seniorfun.command;

import java.util.HashSet;
import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.Packet60Explosion;

import org.bukkit.craftbukkit.entity.CraftPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import to.joe.j2mc.core.J2MC_Manager;
import to.joe.j2mc.core.command.MasterCommand;
import to.joe.j2mc.seniorfun.J2MC_SeniorFun;

public class FireAllAdminsCommand extends MasterCommand {

    Random randomGen = new Random();
    Plugin j2mc_Seniorfun;

    public FireAllAdminsCommand(J2MC_SeniorFun j2mc_SeniorFun) {
        super(j2mc_SeniorFun);
        this.j2mc_Seniorfun = j2mc_SeniorFun;
    }

    @Override
    public void exec(CommandSender sender, String commandName, String[] args, Player player, boolean isPlayer) {
        if (isPlayer && player.hasPermission("j2mc.senior")) {
            this.plugin.getLogger().info(player.getName() + " is firing all admins");
            for (final Player plr : Bukkit.getServer().getOnlinePlayers()) {
                final int x = plr.getLocation().getBlockX();
                final double y = plr.getLocation().getBlockY();
                final int z = plr.getLocation().getBlockZ();
                for (int i = 0; i < 10; i++) {
                    double xToStrike;
                    double zToStrike;
                    if (this.randomGen.nextBoolean()) {
                        xToStrike = x + this.randomGen.nextInt(6);
                    } else {
                        xToStrike = x - this.randomGen.nextInt(6);
                    }
                    if (this.randomGen.nextBoolean()) {
                        zToStrike = z + this.randomGen.nextInt(6);
                    } else {
                        zToStrike = z - this.randomGen.nextInt(6);
                    }
                    final Location toStrike = new Location(plr.getWorld(), xToStrike, y, zToStrike);
                    plr.getWorld().strikeLightningEffect(toStrike);
                }
                if (plr.hasPermission("j2mc.admin")) {
                    final Packet60Explosion boom = new Packet60Explosion(x, y, z, 10, new HashSet<Block>());
                    ((CraftPlayer) plr).getHandle().netServerHandler.sendPacket(boom);
                    final Vector newVelocity = new Vector(((this.randomGen.nextFloat() * 1.5) - 0.75) * 1, (this.randomGen.nextFloat() / 2.5) + (0.4 * 1), ((this.randomGen.nextFloat() * 1.5) - 0.75) * 1);
                    plr.setVelocity(newVelocity);
                }
            }
            if (!((args.length > 0) && args[0].equals("jk"))) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(this.j2mc_Seniorfun, new Runnable() {
                    @Override
                    public void run() {
                        for (final Player plr : J2MC_Manager.getVisibility().getOnlinePlayers(null)) {
                            if (plr.hasPermission("j2mc.admin")) {
                                plr.kickPlayer("Immediately fucking fired.");
                                FireAllAdminsCommand.this.plugin.getLogger().info("Kicked " + plr.getName() + " for being an admin.");
                            }
                        }
                    }
                }, 200);
            }
        }
    }

}
