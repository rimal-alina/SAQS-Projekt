package model;

import java.io.Serializable;
import java.util.Date;

// implement Serializable interface to be able to store object in file
public class Station implements Serializable, StationInterface {
    private String stationId;
    private int target;
    private String date;
    private int actual;

    protected Station() {
        this.stationId = calculateNewStationId();
        this.target = calculateNewTarget();
        this.date = "";
        this.actual = -1;
    }

    private String calculateNewStationId() {
        return "ID-" + new Date().getTime();
    }

    private int calculateNewTarget(){
        // simulate complex atmosperic modeling to calculate target between 1 and 100
        int target = 1 + (int)(Math.random()*100);
        return target;
    }

    @Override
    public String getStationId() {
        return stationId;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public int getTarget() {
        return target;
    }

    @Override
    public int getActual() {
        return actual;
    }

    @Override
    public int getVariance() {
        if (this.actual >= 0){
            return this.actual - this.target;
        // if actual value is negative, there is no valid variance calculation
        }else{
            return 0;
        }  
    }

    //
    // setter methods for all attributes, which should be editable after inizialisation
    //

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void setActual(int actual) {
        this.actual = actual;
    }

    // override toString so that a specific content is printed if we print Station object
    @Override
    public String toString() {
        return this.stationId;
    }
}
