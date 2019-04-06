package org.machtnichts;

import org.machtnichts.batterySimulator.*;
import org.machtnichts.csv.*;

import java.util.Arrays;
import java.util.Objects;
import java.nio.charset.Charset;
import java.io.*;
import java.nio.file.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class BatterySimulatorTest{

  @Test
  public void test() {
    BatteryModel battery = new BatteryModel(0,100.0);
    HomeModel home = new HomeModel(battery);
    Path path = Paths.get("src","test","resources","testData.csv");
    simulateFile(path,home);
    System.out.println("Home: "+home);
    battery = new BatteryModel(0,100.0);
    home = new HomeModel(battery);
    path = Paths.get("src","test","resources","testData2.csv");
    simulateFile(path,home);
    System.out.println("Home: "+home);
  }

  @Test
  public void test201903() {
    for(int batCapa=0;batCapa<=50;batCapa++){
      testBattery(batCapa);
    }
  }

  private void testBattery(int batteryCapacity){
    BatteryModel battery = new BatteryModel(batteryCapacity*1000,100.0);
    HomeModel home = new HomeModel(battery);
    Path path = Paths.get("src","test","resources","2019");
    File[] allFilesAndDirs = path.toFile().listFiles();
    Arrays.sort(allFilesAndDirs, (f1,f2) -> f1.getName().compareTo(f2.getName()));
    for(File file: allFilesAndDirs )
    {
      simulateFile(file.toPath(),home);
    }
    System.out.println("Battery: "+batteryCapacity + " kwh, home stats: "+home);
  }



  private static void simulateFile(Path path, HomeModel home){
    //System.out.println("processing file "+path);
    try{
      Files.readAllLines(path,Charset.forName("UTF-8")).stream()
        .map(CsvReader::convertToTokens)
        .map(RecordConverter::fromTokens)
        .filter(Objects::nonNull)
        .forEach(home::process);
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

}