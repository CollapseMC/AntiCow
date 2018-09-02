package cr.anticow.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

public class GeneralListeners implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void bookEditListener(PlayerEditBookEvent e){
	    if(e.getNewBookMeta().getEnchants().size() == 0) return;
	    e.setCancelled(true);
	}
	
}
