package cr.utils;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketListener;

import cr.anticow.listeners.PacketPlayInBlockPlace;

public class PacketEnabler {

	private static PacketListener blockPlacementFix;
	
	public static void enable(){
		ProtocolLibrary.getProtocolManager().addPacketListener(blockPlacementFix = new PacketPlayInBlockPlace());
	}
	
	public static void disable(){
		if(blockPlacementFix != null)
			ProtocolLibrary.getProtocolManager().removePacketListener(blockPlacementFix);
	}
	
}
