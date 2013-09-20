package me.Paldiu.NNO.Listeners;

import java.util.List;
import me.Paldiu.NNO.Main;
import me.Paldiu.NNO.PlayerData;
import me.Paldiu.NNO.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener
{
    public Main plugin;
    public static final List<String> BLOCKED_WORDS = Main.plugin.getConfig().getStringList("blacklisted_words");
    
@EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        try
        {
            final Player player = event.getPlayer();
            String message = event.getMessage().trim();
            String[] args = message.split(".");

            PlayerData playerdata = PlayerData.getPlayerData(player);
            for (Player players : Bukkit.getServer().getOnlinePlayers())
            {
                PlayerData pd = PlayerData.getPlayerData(players);
                if (pd.censorIsEnabled())
                {
                    for (String word : BLOCKED_WORDS)
                    {
                        if (message.contains(word.toLowerCase()))
                        {
                            Util.censorReplaceMessage(players, message, args);
                            event.setCancelled(true);
                        }
                        else
                        {
                        }
                    }
                }
                else
                {
                    pd.setCensorEnabled(false);
                }
            }
        }
        catch (Exception ex)
        {
            Bukkit.getServer().getLogger().severe(ex.getMessage());
        }
    }
}
