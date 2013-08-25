package me.Paldiu.NNO.Listeners;

import java.util.Arrays;
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
    private static List<String> BLOCKED_WORDS = Arrays.asList("fuck", "shit", "cunt", "ass", "dick", "nigger", "slut", "whore", "");
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();
        String message = event.getMessage();
        String finalMessage = event.getMessage().replace((CharSequence) BLOCKED_WORDS, "*****");
        if (isCensorEnabled(p))
        {
            if (message.contains((CharSequence) BLOCKED_WORDS))
            {
               p.sendMessage(finalMessage);
               event.setCancelled(true);
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
