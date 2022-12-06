package business_controller;

import java.util.TimerTask;
import java.util.List;

import model.FactoryModel;
import model.StationInterface;

/*
    this class is the TimerTask for StationCreator
    this class creates a new station periodically.
    this is a separate class (and not an inline class/method),
    because it is better for readability and maintainability.
    additionally we could reuse methods (nothing to reuse at the moment)
*/
public class StationTimerTask extends TimerTask {

    private List<StationInterface> stationList;

    protected StationTimerTask(List<StationInterface> stationList) {
        this.stationList = stationList;
    }

    @Override
    public void run() {
        this.stationList.add(FactoryModel.getStation()); 
        FactoryBusinessController.getDataPersistenceInstance().writeData(stationList);
    }
}
