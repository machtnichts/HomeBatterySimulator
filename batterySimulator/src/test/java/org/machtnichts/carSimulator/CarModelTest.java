package org.machtnichts.carSimulator;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.machtnichts.commons.EnergyRecord;

public class CarModelTest {
  
  @Test
    public void testOneRecord() {
    CarModel carModel = new CarModel(32*1000,1.0d,3,4);
    //20.06.2018 12:30,"0","4300.3008","287.6011","4587.9019","287.6011"
    //20.06.2018 12:45,"0","4414.0229","180.4697","4594.4927","180.4697"
    //20.06.2018 13:00,"0","4418.0972","178.1855","4596.2827","178.1855"
    Date d = new Date();
    carModel.process(new EnergyRecord(d,4587.9019,287.6011));
    carModel.process(new EnergyRecord(d,4594.4927,180.4697));
    carModel.process(new EnergyRecord(d,4596.2827,178.1855));  
    assertNotNull(carModel.getSimulationResults());
    assertEquals(1,carModel.getSimulationResults().size());
    CarSimulationResult result = carModel.getSimulationResults().get(0);
    assertNotNull(result);
    assertFalse("!!!",result.getAvailableEnergy()<1);
  }
}