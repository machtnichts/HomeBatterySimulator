package org.machtnichts.batterySimulator;

import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Test;
import org.machtnichts.commons.EnergyRecord;

public class HomeModelTest{

  @Test
  public void processTest(){
    BatteryModel battery = new BatteryModel(999999999.9,100.0);
    HomeModel home = new HomeModel(battery);

    EnergyRecord record = new EnergyRecord(new Date(),1.0,2.0);
    home.process(record);
    System.out.println("Step 1");

    assertEquals(1.0, home.getProducedEnergy(),0.0000000001);
    assertEquals(2.0, home.getUsedEnergy(),0.0000000001);
    assertEquals(0.0, home.getExportedEnergy(),0.0000000001);
    assertEquals(1.0, home.getImportedEnergy(),0.0000000001);


    record = new EnergyRecord(new Date(),2.0,1.0);
    home.process(record);
    System.out.println("Step 2");

    assertEquals(3.0, home.getProducedEnergy(),0.0000000001);
    assertEquals(3.0, home.getUsedEnergy(),0.0000000001);
    assertEquals(0.0, home.getExportedEnergy(),0.0000000001);
    assertEquals(1.0, home.getImportedEnergy(),0.0000000001);

    record = new EnergyRecord(new Date(),2.0,1.0);
    home.process(record);
    System.out.println("Step 3");

    assertEquals(5.0, home.getProducedEnergy(),0.0000000001);
    assertEquals(4.0, home.getUsedEnergy(),0.0000000001);
    assertEquals(0.0, home.getExportedEnergy(),0.0000000001);
    assertEquals(1.0, home.getImportedEnergy(),0.0000000001);

  }



}