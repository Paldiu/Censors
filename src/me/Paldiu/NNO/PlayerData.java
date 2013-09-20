package me.Paldiu.NNO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerData
{
    public final static Map<Player, PlayerData> userinfo = new HashMap<Player, PlayerData>();
    private final Player player;
    private final String ip_address;
    private final String player_name;
    private boolean censorEnabled = false;
    
    public PlayerData(Player player)
    {
        this.player = player;
        this.ip_address = player.getAddress().getAddress().getHostAddress();
        this.player_name = player.getName();
    }
    
    public static PlayerData getPlayerData(Player player)
    {
        PlayerData playerdata = PlayerData.userinfo.get(player);

        if (playerdata == null)
        {
            Iterator<Entry<Player, PlayerData>> it = userinfo.entrySet().iterator();
            while (it.hasNext())
            {
                Entry<Player, PlayerData> pair = it.next();
                PlayerData playerdata_test = pair.getValue();

                if (playerdata_test.player_name.equalsIgnoreCase(player.getName()))
                {
                    if (Bukkit.getOnlineMode())
                    {
                        playerdata = playerdata_test;
                        break;
                    }
                    else
                    {
                        if (playerdata_test.ip_address.equalsIgnoreCase(player.getAddress().getAddress().getHostAddress()))
                        {
                            playerdata = playerdata_test;
                            break;
                        }
                    }
                }
            }
        }
        
        if (playerdata == null)
        {
            playerdata = new PlayerData(player);
            PlayerData.userinfo.put(player, playerdata);
        }

        return playerdata;
    }
    
    public void setCensorEnabled(boolean censor_enabled)
    {
        this.censorEnabled = censor_enabled;
    }
    
    public boolean censorIsEnabled()
    {
        return censorEnabled;
    }
}
