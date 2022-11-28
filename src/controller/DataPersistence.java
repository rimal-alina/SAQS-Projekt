package controller;

import java.util.List;

/*
    create an interface for the DataPersistence as we could possibly
    store the data in a file, or in a database, etc.
    the interface makes sure that we have the same objects for all classes
    that implements this interface
*/
public interface DataPersistence {
    public void writeData(List list);
    public List readData();
}
