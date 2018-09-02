package cr.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import cr.anticow.AntiCow;

public class Util {

	public static void log(Object text){
		if(text == null) return;
		AntiCow.getPlugin().getServer().getConsoleSender().sendMessage(text.toString());
	}
	
	public static boolean isElapsed(long time, double second){
		return (System.currentTimeMillis() - time >= second * 1000);
	}
	
	public static void kickPlayer(Player p, String cause){
		Bukkit.getScheduler().scheduleSyncDelayedTask(AntiCow.getPlugin(), () -> {
			if(p == null) return;
			p.kickPlayer(cause == null ? "" : cause);
		});
	}
	
}
