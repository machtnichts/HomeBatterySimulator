package org.machtnichts.batterySimulator;

import lombok.Data;

@Data
public class HomeModel{
  private BatteryModel battery;
  private double producedEnergy;
  private double usedEnergy;
  private double exportedEnergy;
  private double importedEnergy;

  public HomeModel(BatteryModel batteryModel){
    battery=batteryModel;
  }

  public void process(EnergyRecord energyRecord){
    double produced = energyRecord.getProducedEnergy();
    double used = energyRecord.getUsedEnergy();

    producedEnergy+=produced;
    usedEnergy+=used;

    if(produced>=used){//store in battery, export only the amount which didn't fit to battery
      exportedEnergy+=battery.storeEnergy(produced - used);
    }else{//draw the deficit from battery
      importedEnergy+=battery.drawEnergy(used-produced);
    }
  }



  public String toString(){
    return String.format("produced: %.2fkwh imported: %.2fkwh used: %.2fkwh exported: %.2fkwh in battery: %.2fkwh",producedEnergy/1000,importedEnergy/1000,usedEnergy/1000,exportedEnergy/1000,battery.getEnergy()/1000);
  }

}