package by.epam.tr.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import by.epam.tr.dao.DataReader;
import by.epam.tr.entity.Van;

public class VanService {
  private static final int NUM_OF_VANS = 20;
  private ExecutorService pool = Executors.newFixedThreadPool(NUM_OF_VANS);
  private List<Future<String>> futures = new ArrayList<>();
  private LogisticsBase logisticsBase = LogisticsBase.getInstance();

  public String startVans()
      throws InterruptedException, ExecutionException, FileNotFoundException, IOException {

    initTerminals(logisticsBase);

    ArrayList<Van> withPerishableGoods = new ArrayList<Van>();
    ArrayList<Van> withoutPerishableGoods = new ArrayList<Van>();

    ArrayList<List<String>> partsOfString = readInitData();

    for (int i = 0; i < NUM_OF_VANS; i++) {
      boolean havePerishableGoods = Boolean.valueOf(partsOfString.get(i + 1).get(1));
      boolean isFull = Boolean.valueOf(partsOfString.get(i + 1).get(2));
      Van van = new Van(logisticsBase, isFull, havePerishableGoods);
      if (van.isWithPerishableGoods()) {
        withPerishableGoods.add(van);
      } else {
        withoutPerishableGoods.add(van);
      }
    }

    for (int i = 0; i < withPerishableGoods.size(); i++) {
      futures.add(pool.submit(withPerishableGoods.get(i)));
    }

    TimeUnit.MILLISECONDS.sleep(5000);

    for (int i = 0; i < withoutPerishableGoods.size(); i++) {
      futures.add(pool.submit(withoutPerishableGoods.get(i)));
    }

    StringBuffer finalString = new StringBuffer();
    for (Future<String> currentFuture : futures) {
      String finalFuture = currentFuture.get();
      finalString.append(finalFuture + "\n");
    }

    pool.shutdown();
    return finalString.toString();
  }

  public void initTerminals(LogisticsBase logisticsBase) {
    ArrayList<Terminal> terminals = new ArrayList<Terminal>();
    terminals.add(new Terminal());
    terminals.add(new Terminal());
    terminals.add(new Terminal());
    terminals.add(new Terminal());
    terminals.add(new Terminal());
    logisticsBase.setTerminals(terminals);
  }

  public ArrayList<List<String>> readInitData() throws FileNotFoundException, IOException {
    DataReader dataReader = new DataReader();
    String initData = dataReader.readSource();

    List<String> parts = Arrays.asList(initData.split("\n"));

    ArrayList<List<String>> partsOfString = new ArrayList<List<String>>();
    for (String part : parts) {
      partsOfString.add(Arrays.asList(part.split("\t+")));
    }

    return partsOfString;
  }
}
