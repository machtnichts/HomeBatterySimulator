package org.machtnichts.batterySimulator;

import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Test;

public class RecordConverterTest{
  @Test
  public void converterTest(){
    String tokens[]={"05.04.2019 13:30","0","131.9577","896.5451","1028.5028","896.5453"};
    EnergyRecord energyRecord = RecordConverter.fromTokens(tokens);
    assertNotNull(energyRecord);
    assertEquals(1028.5028/4,energyRecord.getProducedEnergy(),0.0000001);
    assertEquals(896.5453/4,energyRecord.getUsedEnergy(),0.0000001);
  }
}