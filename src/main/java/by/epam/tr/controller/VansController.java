package by.epam.tr.controller;

public class VansController implements Controller {

  @Override
  public String doAction(String request) {
    String[] requestParts;
    requestParts = request.split(" ");
    String commandName = requestParts[0];

    CommandProvider commandProvider = new CommandProvider();
    Command command = commandProvider.getCommand(commandName);

    return command.execute(requestParts);
  }

}
