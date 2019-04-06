package org.machtnichts;

import org.machtnichts.batterySimulator.*;
import org.machtnichts.csv.*;

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
    try{
      Files.readAllLines(path,Charset.forName("UTF-8")).stream()
        .map(CsvReader::convertToTokens)
        .map(RecordConverter::fromTokens)
        .filter(Objects::nonNull)
        .forEach(home::process);
    }catch(IOException ioe){
      ioe.printStackTrace();
    }


    System.out.println("Home: "+home);

  }
}