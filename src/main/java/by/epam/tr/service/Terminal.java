package by.epam.tr.service;

import java.util.concurrent.TimeUnit;

public class Terminal {
  private boolean isBusy = false;

  public boolean isBusy() {
    return isBusy;
  }

  public void setBusy(boolean isBusy) {
    this.isBusy = isBusy;
  }

  public String process(boolean withPerishableGoods, boolean isFull) throws InterruptedException {
    String vanLoad = checkVanLoad(isFull);
    String cargoType = determineCargoType(withPerishableGoods);
    TimeUnit.MILLISECONDS.sleep(1000);

    String process = "Van " + Thread.currentThread().getName() + " completed processing terminal"
        + "\n" + vanLoad + ": " + cargoType;
    setBusy(false);
    return process;
  }

  public String determineCargoType(boolean withPerishableGoods) {
    if (withPerishableGoods) {
      return "cargo consists of perishable goods";
    } else {
      return "cargo not consists of perishable goods";
    }
  }

  public String checkVanLoad(boolean isFull) {
    if (isFull) {
      return "Van unloaded";
    } else {
      return "Van loaded";
    }
  }
}
