package model;

import business_controller.FactoryBusinessController;

import java.util.List;

public class StationListModel implements StationListModelInterface{
    private List<StationInterface> stationList;
     
    // constructor protected due to singleton-pattern
    // this object should only be initialized once in FactoryModel Class
    protected StationListModel() {  
        stationList = (List<StationInterface>) FactoryBusinessController.getDataPersistenceInstance().readData();
    }

    @Override
    public List<StationInterface> getStationList() {
        return stationList;
    }
}
