package business_controller;

import java.util.TimerTask;
import java.util.List;
import model.Station;

/*
    this class is the TimerTask for the Main
    this class creates a new station periodically.
    this is a separate class (and not an inline class/method),
    because it is better for readability and maintainability.
    additionally we could reuse methods (nothing to reuse at the moment)
*/
public class StationTimerTask extends TimerTask {

    private List<Station> stationList;

    protected StationTimerTask(List<Station> stationList) {
        this.stationList = stationList;
    }

    @Override
    public void run() {
        this.stationList.add(new Station()); 
        DataPersistenceFile.getInstance().writeData(stationList);
    }
}
