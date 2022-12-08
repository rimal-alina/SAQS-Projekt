package business_controller;

import java.util.Timer;

import java.util.List;

import model.FactoryModel;
import model.StationInterface;

public class StationCreator{
    private List <StationInterface> stationList;
    private StationTimerTask task;

    // Singleton pattern, because schedule for creating new stations should only run once
     
    // constructor protected due to singleton-pattern
    // this object should only be initialized once in FactoryBusinessController Class
    protected StationCreator() { 
        this.stationList = FactoryModel.getStationListModelInstance().getStationList();
        this.task = new StationTimerTask(this.stationList);
        Timer timer = new Timer();
        timer.schedule(task, 20000, 20000);  // schedule every 20 seconds
    }
}
