package me.Paldiu.NNO.Listeners;

import java.util.List;
import me.Paldiu.NNO.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener
{
    public Main plugin;
    public static boolean censorEnabled = false;
    private List<String> BLOCKED_WORDS = plugin.getConfig().getStringList("blacklisted_words");
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();
        String message = event.getMessage();
        if (isCensorEnabled(p))
        {
            if (message.contains((CharSequence) BLOCKED_WORDS))
            {
               String finalMessage = event.getMessage().replace((CharSequence) BLOCKED_WORDS, "*****");
               event.setCancelled(true);
               p.sendMessage(finalMessage);
            }
        }
    }
    
    public void setCensorEnabled(boolean censorEnabled)
    {
        PlayerListener.censorEnabled = censorEnabled;
    }

    public boolean isCensorEnabled(Player p)
    {
        return censorEnabled;
    }
    
}
