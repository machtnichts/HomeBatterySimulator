package org.machtnichts;

import java.nio.file.*;
import org.apache.commons.cli.*;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.nio.charset.Charset;

import org.machtnichts.batterySimulator.*;
import org.machtnichts.csv.*;

public class App {
  public static void main( String[] args ){
    final Options options = createOptions();
    CommandLineParser parser = new DefaultParser();
    try {
      // parse the command line arguments
      CommandLine line = parser.parse( options, args );
      double batteryCapacity = Double.parseDouble(line.getOptionValue("batteryCapacity", "10"));
      if(line.hasOption("input")){
        File inputFile = new File(line.getOptionValue("input"));
        int timeFactor = Integer.parseInt(line.getOptionValue("timeFactor","4"));
        BatteryModel battery = new BatteryModel(batteryCapacity*1000,100.0);
        HomeModel home = new HomeModel(battery);

        if(inputFile.isFile()){
          simulateFile(inputFile.toPath(),home,timeFactor);
        }
        else if(inputFile.isDirectory()){
          File[] allFilesAndDirs = inputFile.listFiles();
          Arrays.sort(allFilesAndDirs, (f1,f2) -> f1.getName().compareTo(f2.getName()));
          for(File file: allFilesAndDirs ){
            simulateFile(file.toPath(),home,timeFactor);
          }
        }
        System.out.println("result: "+home);
      }
      else{
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "java -jar batterySimulator.jar", options );
      }
    } catch( ParseException exp ) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp( "java -jar batterySimulator.jar", options );
    }
  }

  private static Options createOptions(){
    Options options = new Options();
    options.addOption("bc","batteryCapacity", true, "battery capacity, kwh");
    options.addOption("i","input", true, "input data");
    //options.addOption("o","output", true, "simulation result");
    options.addOption("t","timeFactor", true, "time factor - 60 for minute, 4 for 15 minutes");
    options.addOption("h","help", false, "show this help");
    return options;
  }

  public static void simulateFile(Path path, HomeModel home, int timeFactor){
    try{
      final CsvAdapter adapter = new CsvAdapter(",");
      final RecordConverter converter = new RecordConverter(timeFactor);
      Files.readAllLines(path,Charset.forName("UTF-8")).stream()
        .map(adapter::convertToTokens)
        .map(converter::fromTokens)
        .filter(Objects::nonNull)
        .forEach(home::process);
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

}