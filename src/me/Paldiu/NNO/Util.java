package me.Paldiu.NNO;

import java.util.List;
import static me.Paldiu.NNO.Listeners.PlayerListener.BLOCKED_WORDS;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Util
{
    
    private Util()
    {
        throw new AssertionError();
    }
    
    public static void censorReplaceMessage(CommandSender sender, String message, String[] args)
    {
        String output = sender.getName() + ": " + message;
        
        for (String word : BLOCKED_WORDS)
        {
            if (output.contains(word.toLowerCase().trim()))
            {
                String finalOutput = output.replace(word.toLowerCase().trim(), "****");
                sender.sendMessage(colorise(StringUtils.join(args, finalOutput)));
            }
        }
    }
    
    public static String colorise(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
