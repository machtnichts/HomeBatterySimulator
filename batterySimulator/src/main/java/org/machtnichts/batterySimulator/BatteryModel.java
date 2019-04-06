package org.machtnichts.batterySimulator;
import lombok.Data;
@Data
public class BatteryModel{
  private final double maxCapacity;
  private double energy;
  private double efficiency;

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
    this.energy+=energy;
    if(this.energy>maxCapacity){
      exceededAmount = this.energy-maxCapacity;
      this.energy=maxCapacity;
    }
    return exceededAmount;
  }

  /**
  * draws energy from the battery
  * @return the amount of energy which <strong>couldn't be drawn</strong> from baterry because request exceeds the current energy level
  */
  public double drawEnergy(double energy){
    if(energy<0.0)
      throw new IllegalArgumentException("drawEnergy: energy must be positive (actual value: "+energy+")");

    double deficit=energy-this.energy;
    this.energy-=energy;
    if(this.energy<0){
      this.energy=0;
    }else{
      deficit = 0;
    }

    return deficit;
  }

  public double getMaxCapacity(){
    return maxCapacity;
  }


  public String toString(){
    return String.format("capacity: %.3f, energy: %.3f",maxCapacity,energy);
  }
}