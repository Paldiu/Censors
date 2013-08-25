package me.Paldiu.NNO.Commands;

import me.Paldiu.NNO.Listeners.PlayerListener;
import me.Paldiu.NNO.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Censor implements CommandExecutor
{
    public Main plugin;
    public Censor(Main instance)
    {
        plugin = instance;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (commandLabel.equalsIgnoreCase("censor"))
        {
            if (sender instanceof Player)
            {
                Player p = (Player) sender;
                if (p.hasPermission("censors.censor"))
                {
                    if (args.length != 1)
                    {
                        return false;
                    }
                    else
                    {
                        switch (args[0])
                        {
                            case "on":
                                p.sendMessage("Censor enabled.");
                                PlayerListener.censorEnabled = true;
                                return true;
                            case "off":
                                p.sendMessage("Censor disabled.");
                                PlayerListener.censorEnabled = false;
                                return true;
                        }
                    }
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }
            }
            else
            {
                sender.sendMessage("You cannot use this command from the console.");
                return true;
            }
        }
        return false;
    }
}
