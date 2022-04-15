package by.epam.tr.controller.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import by.epam.tr.controller.Command;
import by.epam.tr.service.VanService;

public class VanDriveCommand implements Command {

  @Override
  public String execute(String[] requestParts) {
    VanService vanService = new VanService();
    String vansWork = null;
    try {
      vansWork = vanService.startVans();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return vansWork;
  }

}
