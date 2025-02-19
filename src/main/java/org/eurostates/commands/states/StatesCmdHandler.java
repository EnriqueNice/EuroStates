package org.eurostates.commands.states;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.eurostates.commands.CommandInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// Following a tutorial for this one

@Deprecated
public class StatesCmdHandler implements CommandExecutor {
    private static HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();

    public void register(String name, CommandInterface cmd) {
        commands.put(name, cmd);
    }

    public boolean checkIfExists(String name) {
        return commands.containsKey(name);
    }

    public CommandInterface getCommandExec(String name) {
        return commands.get(name);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command_label, String[] args) {
        ArrayList<String> p_args = new ArrayList<String>(Arrays.asList(args));

        if (args.length == 0) {
            try {
                getCommandExec("states").onCommand(sender, cmd, command_label, p_args);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        if (args.length > 0) {
            if (checkIfExists(args[0])) {
                try {
                    p_args.remove(0); // Shave :)
                    getCommandExec(args[0]).onCommand(sender, cmd, command_label, p_args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                sender.sendMessage(ChatColor.BLUE + "[EuroStates]" + ChatColor.RED + " That command does not exist!");
                return true;
            }
        }
        return true;
    }
}


