package org.machtnichts.batterySimulator;

public class BatteryModel{

    private final int maxCapacity;
    private int energy;
    public BatteryModel(int capacity){
        maxCapacity=capacity;
    }

    public String toString(){
        return String.valueOf(maxCapacity)+String.valueOf(energy);
    }
}