package model;

import business_controller.DataPersistenceFile;
import java.util.List;

public class StationListModel {
    private static StationListModel instance;
    private List<Station> stationList;

    /*
        use Singleton pattern to make sure that only one object of StationListModel exists
        this object returns the current values of all Station
        first time we refer to StationModel - we need to create the object
    */
     
    private StationListModel() {  // constructor private due to singleton-pattern
        stationList = (List<Station>) DataPersistenceFile.getInstance().readData();
    }

    /*
        Singleton-pattern. we only want to have exact one instance, so that
        the data is kept only once during runtime.
        In code we need to call the class via the 'getInstance' method
        and can not call the constructor directly.
    */
    public static StationListModel getInstance() {
        if(instance == null)
            instance = new StationListModel();
        return instance;
    }

    public List<Station> getStationList() {
        return stationList;
    }
}
