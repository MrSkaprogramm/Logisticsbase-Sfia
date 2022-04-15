package by.epam.tr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.controller.Controller;
import by.epam.tr.controller.VansController;

public class Logistics {
  private static Logger logger = LogManager.getLogger(Logistics.class);

  public static void main(String[] args) {
    Controller controller = new VansController();

    logger.info("Good time of day sir! Your logistics company is working perfectly" + "\n"
        + "Please wait, I will make you a report on today's cargo transportation...");

    String request = "driveVans";
    String vansWork = controller.doAction(request);
    logger.info("Your report of vans work sir: " + "\n" + vansWork);
  }
}
