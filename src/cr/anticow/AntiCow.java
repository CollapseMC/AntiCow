package cr.anticow;

import org.bukkit.plugin.java.JavaPlugin;

import cr.anticow.listeners.GeneralListeners;
import cr.utils.PacketEnabler;
import cr.utils.Util;

public class AntiCow extends JavaPlugin {
	
	public static AntiCow instance;

	@Override
	public void onEnable(){
		instance = this;
		
		registerListeners();		
		Util.log("�eAntiCow eklentisi aktif! ["+getVersion()+"]");		

		Util.log("�a------------------------------------------");
		Util.log("�eBuldu�unuz Crash/Exploit t�revi sorunlar� Discord �zerinden ileti�ime ge�erek bildirmekten �ekinmeyin.");
		Util.log("�eEkibimiz her zaman bu durumlar i�in yard�m etmeye a��kt�r.");
		Util.log(" ");
		Util.log("�eDiscord ileti�im adresimiz: CRAFTRISE#0083");
		Util.log("�a------------------------------------------");		
		
		PacketEnabler.enable();
	}
	
	@Override
	public void onDisable(){
		Util.log("�cAntiCow eklentisi devred���! ["+getVersion()+"]");
		PacketEnabler.disable();
	}
	
	private void registerListeners(){
		this.getServer().getPluginManager().registerEvents(new GeneralListeners(), this);
	}
	
	public static AntiCow getPlugin(){
		return instance;
	}
	
	public String getVersion(){
		return this.getDescription().getVersion();
	}
	
}
