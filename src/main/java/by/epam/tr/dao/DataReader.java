package by.epam.tr.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataReader {
  private final String datasourceFileName = "VanData.txt";
  private final String datasourseDirectory = "src\\main\\resources";
  private Path datasourcePath = Paths.get(datasourseDirectory).toAbsolutePath();
  private File file = new File(datasourcePath.toString(), datasourceFileName);

  public String readSource() throws FileNotFoundException, IOException {
    StringBuffer text = new StringBuffer();

    try (FileReader readStreetsfile = new FileReader(file);
        BufferedReader streetReader = new BufferedReader(readStreetsfile)) {
      String res = streetReader.readLine();
      while (res != null) {
        text.append(res + "\n");
        res = streetReader.readLine();
      }
    }
    return text.toString();
  }
}
