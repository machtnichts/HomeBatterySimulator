package org.machtnichts.batterySimulator;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class RecordConverter
{
  //Time,Bezug (W),Einspeisung (W),Energie - Eigenverbrauch (W),Produktion (W),Verbrauch (W)
  //05.04.2019 13:30,"0","131.9577","896.5451","1028.5028","896.5451"
  private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

  public static EnergyRecord fromTokens(String tokens[]){
    if(tokens.length==6){
      try{
        Date date = formatter.parse(tokens[0]);
        double produced = Double.parseDouble(tokens[4])/4; //divided by 4 because it is average power for last 15 minutes
        double used = Double.parseDouble(tokens[5])/4; //divided by 4 because it is average power for last 15 minutes
        return new EnergyRecord(date,produced,used);

      }catch(ParseException pe){
        System.out.println("RecordConverter: ignored: "+tokens[0]);
      }

    }
    return null;
  }

}