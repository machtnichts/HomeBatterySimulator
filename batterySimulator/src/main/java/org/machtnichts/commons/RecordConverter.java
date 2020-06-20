package org.machtnichts.commons;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class RecordConverter
{
  //Time,Bezug (W),Einspeisung (W),Energie - Eigenverbrauch (W),Produktion (W),Verbrauch (W)
  //05.04.2019 13:30,"0","131.9577","896.5451","1028.5028","896.5451"
  private static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
  private double timeFraction;

  public EnergyRecord fromTokens(String tokens[]){
    if(tokens.length==6){
      try{
        Date date = formatter.parse(tokens[0]);
        double produced = Double.parseDouble(tokens[4])/timeFraction; //divided by timeFraction because it is average power for last xy minutes
        double used = Double.parseDouble(tokens[5])/timeFraction; //divided by timeFraction because it is average power for last xy minutes
        return new EnergyRecord(date,produced,used);

      }catch(ParseException pe){
        //System.out.println("RecordConverter: ignored: "+tokens[0]);
      }

    }
    return null;
  }

}