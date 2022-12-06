package business_controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import model.StationInterface;

/*
    implementation of DataPersistence into a file
*/
public class DataPersistenceFile implements DataPersistenceInterface{
    private File file;
    private PropertyChangeSupport pcs;
     
    // constructor protected due to singleton-pattern
    // this object should only be initialized once in FactoryBusinessController Class
    protected DataPersistenceFile() {
        file = new File(System.getProperty("user.dir").concat("/icecreamparticulate.dat"));
        this.pcs = new PropertyChangeSupport(this);
    }

    @Override
    public void writeData(List<StationInterface> list) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write the whole list at once as elements of our list are serializable
            oos.writeObject(list);
            oos.close();
            // because value isn't needed, it is set to null
            pcs.firePropertyChange("List changed", null, list); 
        } catch(IOException e) {
            System.err.println("ERROR beim Speichern der Daten!");
        }
    }

    @Override
    public List<StationInterface> readData() {
        List<StationInterface> list = new ArrayList<>();
        // the first time we start application there is no data file
        if(file.isFile()) {  
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                // read the whole list at once as elements of our list are serializable
                list = (List<StationInterface>) ois.readObject();
                ois.close();
            } catch(IOException | ClassNotFoundException e) {
                System.err.println("ERROR beim Laden der Daten!");
            }
        }
        return list;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    @Override     
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

}
