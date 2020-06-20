package org.machtnichts.carSimulator;

import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.management.monitor.GaugeMonitor;

import org.machtnichts.commons.CompareDays;
import org.machtnichts.commons.EnergyRecord;

public class CarModel {

  private static final int MIN_CURRENT=6;
  private static final int MAX_CURRENT=16;
  private static final int VOLTAGE=225;

  private double batteryCapacity;
  private double efficiency;
  private int phaseCount;
  private int timeFactor;

  private Date currentDate;
  private double currentAvailableEnergy;
  private List<CarSimulationResult> results;

  public CarModel(double batteryCapacity, double efficiency, int usedPhaseCount, int timeFactor ){    
    this.batteryCapacity = batteryCapacity;
    this.efficiency = efficiency;
    this.phaseCount = usedPhaseCount;
    this.timeFactor = timeFactor;    
    results = new LinkedList<>();
  }

  public void process(EnergyRecord energyRecord){
    if(currentDate==null)
      currentDate=energyRecord.getTimestamp();
    if(!CompareDays.isSameDay(currentDate, energyRecord.getTimestamp())){
      //new day, create result record...
      CarSimulationResult carSimulationResult = new CarSimulationResult(currentDate,currentAvailableEnergy);
      results.add(carSimulationResult);
      currentAvailableEnergy = 0;
      currentDate = energyRecord.getTimestamp();
    }
      
    double availableEnergy = energyRecord.getProducedEnergy() - energyRecord.getUsedEnergy();
    double availablePower = availableEnergy*timeFactor;
    if(availablePower>getMinPower()){
      double useableEnergy = availableEnergy;
      if(availablePower>getMaxPower()) //then the car cant use all... 
        useableEnergy = getMaxPower()/timeFactor;
      useableEnergy = useableEnergy * efficiency;
      currentAvailableEnergy+=useableEnergy;
    }
  }

  private double getMinPower(){
    return phaseCount * VOLTAGE * MIN_CURRENT;
  }

  private double getMaxPower(){
    return phaseCount * VOLTAGE * MAX_CURRENT;
  }

  public List<CarSimulationResult> getSimulationResults(){
    if(currentDate!=null){
      CarSimulationResult carSimulationResult = new CarSimulationResult(currentDate,currentAvailableEnergy);
      results.add(carSimulationResult);
      currentDate=null;
      currentAvailableEnergy=0;
    }
    return Collections.unmodifiableList(results);
  }

}