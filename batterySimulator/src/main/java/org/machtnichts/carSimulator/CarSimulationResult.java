package org.machtnichts.carSimulator;

import java.util.Date;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class CarSimulationResult {
  private Date day;
  private double availableEnergy;
  
}