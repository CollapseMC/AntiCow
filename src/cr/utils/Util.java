package cr.utils;

import cr.anticow.AntiCow;

public class Util {

	public static void log(Object text){
		AntiCow.getPlugin().getServer().getConsoleSender().sendMessage(text.toString());
	}
	
}
