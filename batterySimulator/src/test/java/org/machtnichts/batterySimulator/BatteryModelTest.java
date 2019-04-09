package org.machtnichts.batterySimulator;

import static org.junit.Assert.*;
import org.junit.Test;

public class BatteryModelTest{

  @Test
  public void storeEnergy() {
    double energy = 2.0;
    BatteryModel batteryModel = new BatteryModel(1000,100);
    assertEquals(0, batteryModel.storeEnergy(energy),0.0000000001);
    assertEquals(energy, batteryModel.getEnergy(),0.0000000001);
    assertEquals(energy, batteryModel.storeEnergy(batteryModel.getMaxCapacity()),0.0000000001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void storeEnergyNegative(){
    BatteryModel batteryModel = new BatteryModel(1000,100);
    batteryModel.storeEnergy(-1);
  }

  @Test
  public void drawEnergy(){
    BatteryModel batteryModel = new BatteryModel(1000,100);
    batteryModel.storeEnergy(100.0);
    batteryModel.drawEnergy(batteryModel.getMaxCapacity()); // must be empty now
    assertEquals(1.0, batteryModel.drawEnergy(1.0),0.0000000001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void drawEnergyNegative(){
    BatteryModel batteryModel = new BatteryModel(1000,100);
    batteryModel.drawEnergy(-1);
  }

}