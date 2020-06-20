package org.machtnichts;

import java.nio.file.*;
import org.apache.commons.cli.*;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;
import java.nio.charset.Charset;

import org.machtnichts.csv.*;
import org.machtnichts.carSimulator.CarModel;
import org.machtnichts.carSimulator.CarSimulationResult;
import org.machtnichts.commons.RecordConverter;
import org.machtnichts.commons.EnergyRecord;

public class ECar {
  public static void main( String[] args ){
    final Options options = createOptions();
    CommandLineParser parser = new DefaultParser();
    try {
      // parse the command line arguments
      CommandLine line = parser.parse( options, args );
      int batteryCapacity = Integer.parseInt(line.getOptionValue("batteryCapacity", "10"));
      if(line.hasOption("input")){
        File inputFile = new File(line.getOptionValue("input"));
        int timeFactor = Integer.parseInt(line.getOptionValue("timeFactor","4"));

        System.out.println("batteryCapacity(kwh), batteryDrawn(kwh), batteryStored(kwh), produced(kwh), imported(kwh), used(kwh), exported(kwh)");
        for(int i=0;i<=batteryCapacity;i++)
          simulateCar(inputFile,i,timeFactor);
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

  private static void simulateCar(File inputFile, double batteryCapacity, int timeFactor){
    CarModel ecar = new CarModel(batteryCapacity*1000,1.0d,1,timeFactor);    
    if (inputFile.isFile()) {
      simulateFile(inputFile.toPath(), ecar, timeFactor);
    } else if (inputFile.isDirectory()) {
      File[] allFilesAndDirs = inputFile.listFiles();
      Arrays.sort(allFilesAndDirs, (f1, f2) -> f1.getName().compareTo(f2.getName()));
      for (File file : allFilesAndDirs) {
        simulateFile(file.toPath(), ecar, timeFactor);
      }
    }

    //ecar.getSimulationResults().stream().map(x->String.format("%s : %d kWh",x.getDay(),Math.round(x.getAvailableEnergy()/1000))).forEach(System.out::println);

    long countDaysWith100km = ecar.getSimulationResults().stream().filter(x->x.getAvailableEnergy()>=16000).count();
    long countDaysWith50km = ecar.getSimulationResults().stream().filter(x->x.getAvailableEnergy()>=8000).count();
    long countDaysWith25km = ecar.getSimulationResults().stream().filter(x->x.getAvailableEnergy()>=4000).count();
    LongAdder energyInkWh = new LongAdder();
    ecar.getSimulationResults().stream().forEach(x->energyInkWh.add(Math.round(x.getAvailableEnergy()/1000)));
    long countAllEnergy = energyInkWh.longValue();
    long allRangeInKm = countAllEnergy/16*100;

    System.out.println(String.format("days with 100km: %d, days with 50km: %d, days with 25km: %d, all range: %dkm, all energy: %dkWh",countDaysWith100km,countDaysWith50km,countDaysWith25km,allRangeInKm,countAllEnergy));

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

  public static void simulateFile(Path path, CarModel ecar, int timeFactor){
    try{
      final CsvAdapter adapter = new CsvAdapter(",");
      final RecordConverter converter = new RecordConverter(timeFactor);
      Files.readAllLines(path,Charset.forName("UTF-8")).stream()
        .map(adapter::convertToTokens)
        .map(converter::fromTokens)
        .filter(Objects::nonNull)
        .forEach(ecar::process);
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }

}