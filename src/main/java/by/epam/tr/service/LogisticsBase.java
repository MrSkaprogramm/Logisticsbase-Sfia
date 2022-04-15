package by.epam.tr.service;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticsBase {
  private static final LogisticsBase instance = new LogisticsBase();
  private Semaphore semaphore;
  private ReentrantLock lock;
  private ArrayList<Terminal> terminals;


  private LogisticsBase() {
    this.semaphore = new Semaphore(2);
    this.lock = new ReentrantLock();
  }

  public static LogisticsBase getInstance() {
    return instance;
  }

  public void setTerminals(ArrayList<Terminal> terminals) {
    this.terminals = terminals;
  }

  public void enterCar(boolean withPerishableGoods) throws InterruptedException {
      semaphore.acquire();
  }

  public String goToTerminal(boolean isFull, boolean withPerishableGoods)
      throws InterruptedException {
    lock.lock();
    Terminal terminal = null;
    while (terminal == null) {
      terminal = checkAvailableTerminal();
    }
    terminal.setBusy(true);
    lock.unlock();
    return terminal.process(withPerishableGoods, isFull);
  }

  public void exitCar() {
    semaphore.release();
  }

  public Terminal checkAvailableTerminal() {
    for (int i = 0; i < terminals.size(); i++) {
      if (!terminals.get(i).isBusy()) {
        return terminals.get(i);
      }
    }
    return null;
  }
}
