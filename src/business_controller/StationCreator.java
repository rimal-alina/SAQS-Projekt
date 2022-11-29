package business_controller;

import java.util.Timer;

import java.util.List;
import model.Station;
import model.StationListModel;

public class StationCreator {

    private static StationCreator instance; // needed for singleton pattern
    private List <Station> stationList;
    private StationTimerTask task;

    // Singleton pattern, because schedule for creating new stations should only run once
     
    private StationCreator() { // constructor private due to singleton-pattern
        this.stationList = StationListModel.getInstance().getStationList();
        this.task = new StationTimerTask(this.stationList);
        Timer timer = new Timer();
        timer.schedule(task, 20000, 20000);  // schedule every 20 seconds
    }
    
    public static StationCreator getInstance() {
        if (instance == null) {
            instance = new StationCreator();
        }
        return instance;
    }
}
