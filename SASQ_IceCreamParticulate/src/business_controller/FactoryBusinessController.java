package business_controller;

public final class FactoryBusinessController {

    private static DataPersistenceFile dataPersistenceFileInstance; // needed for Singleton Pattern
    private static StationCreator stationCreator; // needed for Singleton Pattern

    public static DataPersistenceInterface getDataPersistenceInstance(){
        // use Singleton pattern to make sure that only one object exists
        // this object reads and writes data from/to the same file
        // first time we refer to DataPersistenceFile - we need to create the object
        if (dataPersistenceFileInstance == null){
            dataPersistenceFileInstance = new DataPersistenceFile();
        }
        return dataPersistenceFileInstance;
    }

    public static void createStationCreator(){
        // use Singleton pattern to make sure that only one object exists
        // this object reads and writes data from/to the same file
        // first time we refer to DataPersistenceFile - we need to create the object
        if (stationCreator == null){
            stationCreator = new StationCreator();
        }
    }    
}
