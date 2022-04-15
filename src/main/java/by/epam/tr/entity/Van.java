package by.epam.tr.entity;

import java.util.Objects;
import java.util.concurrent.Callable;
import by.epam.tr.service.LogisticsBase;

public class Van implements Callable<String> {
  private boolean isFull;
  private boolean withPerishableGoods;
  private LogisticsBase logisticsBase;

  public Van(LogisticsBase logisticsBase, boolean withPerishableGoods, boolean isFull) {
    this.isFull = isFull;
    this.withPerishableGoods = withPerishableGoods;
    this.logisticsBase = logisticsBase;
  }

  public boolean isFull() {
    return isFull;
  }

  public void setFull(boolean isFull) {
    this.isFull = isFull;
  }

  public boolean isWithPerishableGoods() {
    return withPerishableGoods;
  }

  public void setWithPerishableGoods(boolean withPerishableGoods) {
    this.withPerishableGoods = withPerishableGoods;
  }

  public LogisticsBase getLogisticsBase() {
    return logisticsBase;
  }

  public void setLogisticsBase(LogisticsBase logisticsBase) {
    this.logisticsBase = logisticsBase;
  }

  @Override
  public String call() throws Exception {
    logisticsBase.enterCar(withPerishableGoods);
    String call = logisticsBase.goToTerminal(isFull, withPerishableGoods);
    logisticsBase.exitCar();
    return call;
  }

  @Override
  public int hashCode() {
    return Objects.hash(isFull, withPerishableGoods);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Van other = (Van) obj;
    return isFull == other.isFull && withPerishableGoods == other.withPerishableGoods;
  }

  @Override
  public String toString() {
    return "Van [isFull=" + isFull + ", withPerishableGoods=" + withPerishableGoods + "]";
  }
}