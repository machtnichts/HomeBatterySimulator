package org.machtnichts;

import org.machtnichts.batterySimulator.BatteryModel;

/**
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BatteryModel model = new BatteryModel(123);
        System.out.println( "model " + model);
    }
}
