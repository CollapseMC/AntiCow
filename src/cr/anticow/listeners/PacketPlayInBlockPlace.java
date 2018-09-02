package cr.anticow.listeners;

import org.bukkit.Material;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import cr.anticow.AntiCow;

public class PacketPlayInBlockPlace extends PacketAdapter {
	
	public PacketPlayInBlockPlace(){
		super(
				AntiCow.getPlugin(), 
				ListenerPriority.HIGHEST, 
				PacketType.Play.Client.BLOCK_PLACE
			);
	}

	@Override
	public void onPacketReceiving(PacketEvent e){
		org.bukkit.inventory.ItemStack item = e.getPacket().getItemModifier().read(0);		
		if(item == null || (!item.getType().equals(Material.WRITTEN_BOOK) && !item.getType().equals(Material.BOOK_AND_QUILL))) return;

		e.setCancelled(true);
	}
	
}
