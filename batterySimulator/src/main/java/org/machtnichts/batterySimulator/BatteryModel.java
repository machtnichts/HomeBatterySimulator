package org.machtnichts.batterySimulator;
import lombok.Data;
@Data
public class BatteryModel{
  private final double maxCapacity;
  private double energy;
  private double efficiency;
  private double energyStored;
  private double energyDrawn;

  public BatteryModel(double capacity, double efficiency){
    maxCapacity=capacity;
    this.efficiency = efficiency;
  }

  /**
  * stores energy with configured efficiency
  * @return the amount of energy which can't be stored if the maxCapacity is exceeded, 0 if all energy can be stored.
  */
  public double storeEnergy(double energy){
    if(energy<0.0)
      throw new IllegalArgumentException("storeEnergy: energy must be positive (actual value: "+energy+")");

    double exceededAmount=0;
    double energyToStore=energy;
    if((this.energy+energyToStore)>maxCapacity){
      exceededAmount = (this.energy+energyToStore)-maxCapacity;
      energyToStore = energy-exceededAmount;
    }
    increaseEnergy(energyToStore);
    return exceededAmount;
  }

  /**
  * draws energy from the battery
  * @return the amount of energy which <strong>couldn't be drawn</strong> from baterry because request exceeds the current energy level
  */
  public double drawEnergy(double energy){
    if(energy<0.0)
      throw new IllegalArgumentException("drawEnergy: energy must be positive (actual value: "+energy+")");

    double deficit=0;
    double energyToDraw = energy;
    if((this.energy-energy)<0){
      deficit = energy-this.energy;
      energyToDraw = energy-deficit;
    }
    decreaseEnergy(energyToDraw);
    return deficit;
  }

  public double getMaxCapacity(){
    return maxCapacity;
  }


  public String toString(){
    return String.format("max capacity: %.2f, current energy: %.2f, energy ever stored: %.0f, energy ever drawn: %0.f",maxCapacity,energy,energyStored,energyDrawn);
  }

  private void increaseEnergy(double energy){
    this.energy+=energy;
    this.energyStored+=energy;
  }

  private void decreaseEnergy(double energy){
    this.energy-=energy;
    this.energyDrawn+=energy;
  }

}