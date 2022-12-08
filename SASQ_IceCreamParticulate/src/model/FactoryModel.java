package model;

public final class FactoryModel {

    private static StationListModel stationListModelInstance;

    public static StationListModelInterface getStationListModelInstance(){
        // use Singleton pattern to make sure that only one object exists
        // this object reads and writes data from/to the same file
        // first time we refer to DataPersistenceFile - we need to create the object
        if (stationListModelInstance == null){
            stationListModelInstance = new StationListModel();
        }
        return stationListModelInstance;
    }

    public static StationInterface getStation(){
        return new Station();
    } 
}
