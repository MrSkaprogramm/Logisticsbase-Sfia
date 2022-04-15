package by.epam.tr.controller;

import java.util.HashMap;
import java.util.Map;
import by.epam.tr.controller.impl.VanDriveCommand;

public class CommandProvider {
  private Map<String, Command> commands = new HashMap<String, Command>();

  public CommandProvider() {
    commands.put("driveVans", new VanDriveCommand());
  }

  public Command getCommand(String commandName) {
    Command command = commands.get(commandName);

    return command;
  }
}
