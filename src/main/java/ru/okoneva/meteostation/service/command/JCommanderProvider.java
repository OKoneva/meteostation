package ru.okoneva.meteostation.service.command;

import com.beust.jcommander.JCommander;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Koneva
 * Date: 19.05.2014:17:48
 * Version 1.0
 */
public class JCommanderProvider {
    private List<Action> commands = new ArrayList<Action>();

    /**
     * Constructs the new JCommander instance with all commands.

     */
    public JCommander create() {
        final JCommander commander = new JCommander();
        for (Action command : commands) {
            commander.addCommand(command);
        }
        return commander;
    }

    public void addCommand(Action command) {
        commands.add(command);
    }
}