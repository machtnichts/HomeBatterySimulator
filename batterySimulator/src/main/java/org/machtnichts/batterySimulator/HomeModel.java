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
    return String.format("produced: %.1f imported: %.1f used: %.1f exported: %.1f battery: %.1f",producedEnergy,importedEnergy,usedEnergy,exportedEnergy,battery.getEnergy());
  }

}