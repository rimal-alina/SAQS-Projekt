package model;

import controller.DataPersistenceFile;
import java.util.List;

public class StationModel {
    private static StationModel instance;
    private List<Station> stationList;

    /*
        use Singleton pattern to make sure that only one object of StationModel exists
        this object returns the current values of all Station
        first time we refer to StationModel - we need to create the object
    */
     
    private StationModel() {  // constructor private due to singleton-pattern
        stationList = DataPersistenceFile.getInstance().readData();
    }

    /*
        Singleton-pattern. we only want to have exact one instance, so that
        the data is kept only once during runtime.
        In code we need to call the class via the 'getInstance' method
        and can not call the constructor directly.
    */
    public static StationModel getInstance() {
        if(instance == null)
            instance = new StationModel();
        return instance;
    }

    public List getStationList() {
        return stationList;
    }
}
