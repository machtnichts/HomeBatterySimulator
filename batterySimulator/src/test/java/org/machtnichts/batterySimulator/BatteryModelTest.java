package org.machtnichts.batterySimulator;

import static org.junit.Assert.*;
import org.junit.Test;

public class BatteryModelTest{

  @Test
  public void storeEnergy() {
    double energy = 2.0;
    BatteryModel batteryModel = new BatteryModel(1000,100);
    System.out.println("bm.storeEnergy: "+batteryModel);
    assertEquals(0, batteryModel.storeEnergy(energy),0.0000000001);
    System.out.println("bm.storeEnergy: "+batteryModel);
    assertEquals(energy, batteryModel.storeEnergy(batteryModel.getMaxCapacity()),0.0000000001);
    System.out.println("bm.storeEnergy: "+batteryModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void storeEnergyNegative(){
    BatteryModel batteryModel = new BatteryModel(1000,100);
    System.out.println("bm.storeEnergyNegative: "+batteryModel);
    batteryModel.storeEnergy(-1);
    System.out.println("bm.storeEnergyNegative: "+batteryModel);
  }

  @Test
  public void drawEnergy(){
    BatteryModel batteryModel = new BatteryModel(1000,100);
    batteryModel.storeEnergy(100.0);
    System.out.println("bm.drawEnergy: "+batteryModel);
    batteryModel.drawEnergy(batteryModel.getMaxCapacity()); // must be empty now
    System.out.println("bm.drawEnergy: "+batteryModel);
    assertEquals(1.0, batteryModel.drawEnergy(1.0),0.0000000001);
    System.out.println("bm.drawEnergy: "+batteryModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void drawEnergyNegative(){
    BatteryModel batteryModel = new BatteryModel(1000,100);
    System.out.println("bm.drawEnergyNegative: "+batteryModel);
    batteryModel.drawEnergy(-1);
    System.out.println("bm.drawEnergyNegative: "+batteryModel);
  }

}