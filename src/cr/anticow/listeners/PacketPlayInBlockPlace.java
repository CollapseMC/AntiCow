package cr.anticow.listeners;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import cr.anticow.AntiCow;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class PacketPlayInBlockPlace extends PacketAdapter {
	
	private static int MAX_PACKET_SEND_LIMIT = 8;
    private static final Map<Player, AtomicInteger> PACKET_SENDED = new ConcurrentHashMap<>();
	
	public PacketPlayInBlockPlace(){
		super(
				AntiCow.getPlugin(), 
				ListenerPriority.HIGHEST, 
				PacketType.Play.Client.BLOCK_PLACE
			);
	}

	@Override
	public void onPacketReceiving(PacketEvent e){
		Player p = e.getPlayer();	
		
		if(!canSendPacket(p)){
			e.setCancelled(true);
			return;
		}
		
		org.bukkit.inventory.ItemStack item = e.getPacket().getItemModifier().read(0);		
		if(item == null || !item.getType().equals(Material.BOOK_AND_QUILL)) return;
			
		ItemStack book = CraftItemStack.asNMSCopy(item);
		if(!book.hasTag() || !book.getTag().hasKey("pages")) return;
		
		NBTTagList pages = book.getTag().getList("pages", 8);
		
		if(pages.size() > 50){
			e.setCancelled(true);
			p.kickPlayer("Çok fazla veri gönderimi yapýyorsunuz!");
		}else if(pages.size() >= 1){				
			for(int i = 0; i < pages.size(); i++){
				if(pages.getString(i).length() > 255){
					e.setCancelled(true);
					p.kickPlayer("Çok fazla veri gönderimi yapýyorsunuz!");
					break;
				}
			}
		}
	}
	
	public boolean canSendPacket(Player p){
		if(!PACKET_SENDED.containsKey(p))
			PACKET_SENDED.put(p, new AtomicInteger());
		
		return PACKET_SENDED.get(p).incrementAndGet() <= MAX_PACKET_SEND_LIMIT;
	}
	
	private BukkitTask packetResetter = new BukkitRunnable() { @Override public void run() { 
		PACKET_SENDED.clear(); 
	}}.runTaskTimer(AntiCow.getPlugin(), 20, 20);

}