package model;

import java.io.Serializable;
import java.util.Date;

// implement Serializable interface to be able to store object in file
public class Station implements Serializable {
    private String stationId;
    private Date date;
    private int target;
    private int actual;

    public Station() {
    }

    public Station(String stationId) {
        this.stationId = stationId;
        this.date = new Date();
    }

    public Station(String stationId, int target) {
        this.stationId = stationId;
        this.target = target;
        this.date = new Date();
    }

    public Station(String stationId, Date date, int target, int actual) {
        this.stationId = stationId;
        this.date = date;
        this.target = target;
        this.actual = actual;
    }


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getVariance() {
        return this.actual - this.target;
    }

    // override toString so that a specific content is printed if we print Station object
    @Override
    public String toString() {
        return this.stationId;
    }
}
