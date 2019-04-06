package org.machtnichts.batterySimulator;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
public class EnergyRecord{
  private Date timestamp;
  private double producedEnergy;
  private double usedEnergy;
}