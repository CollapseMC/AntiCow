package cr.anticow;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import cr.utils.PacketEnabler;
import cr.utils.Util;

public class AntiCow extends JavaPlugin {
	
	public static AntiCow instance;

	@Override
	public void onEnable(){
		instance = this;
		
		if(Bukkit.getPluginManager().getPlugin("ProtocolLib") == null){
			Util.log("�cAntiCow eklentisi devre d��� b�rak�ld�! ProtocolLib y�kl� de�il.");
			return;
		}
		
		Util.log(" ");
		Util.log("�eAntiCow eklentisi aktif! ["+getVersion()+"]");		

		Util.log("�a------------------------------------------");
		Util.log("�eBuldu�unuz Crash/Exploit t�revi sorunlar� Discord �zerinden ileti�ime ge�erek bildirmekten �ekinmeyin.");
		Util.log("�eEkibimiz her zaman bu durumlar i�in yard�m etmeye a��kt�r.");
		Util.log(" ");
		Util.log("�eDiscord ileti�im adresimiz: CRAFTRISE#0083");
		Util.log("�a------------------------------------------");		
		Util.log(" ");
		
		PacketEnabler.enable();
	}
	
	@Override
	public void onDisable(){
		Util.log(" ");
		Util.log("�cAntiCow eklentisi devred���! ["+getVersion()+"]");
		Util.log(" ");
		
		PacketEnabler.disable();
	}
	
	public static AntiCow getPlugin(){
		return instance;
	}
	
	public String getVersion(){
		return this.getDescription().getVersion();
	}
	
}
