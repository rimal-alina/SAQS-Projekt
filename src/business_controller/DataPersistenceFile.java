package business_controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import model.Station;

/*
    implementation of DataPersistence into a file
*/
public class DataPersistenceFile  {
    private static DataPersistenceFile instance; // needed for singleton pattern
    private File file;
    private PropertyChangeSupport pcs;

    // use Singleton pattern to make sure that only one object exists
    // this object reads and writes data from/to the same file
    // first time we refer to DataPersistenceFile - we need to create the object
     
    private DataPersistenceFile() { // constructor private due to singleton-pattern
        file = new File(System.getProperty("user.dir").concat("/icecreamparticulate.dat"));
        this.pcs = new PropertyChangeSupport(this);
    }

    /*
        Singleton-pattern: We only want to have exact one instance, so that
        the file-object is created only once and we use the same file every time.
        This was, consistency can be ensured. In code we need to call the class via
        the 'getInstance' method and can not call the constructor directly.
    */
    public static DataPersistenceFile getInstance() {
        if (instance == null){
            instance = new DataPersistenceFile();
        }
        return instance;
    }

    public void writeData(List<Station> list) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write the whole list at once as elements of our list are serializable
            oos.writeObject(list);
            oos.close();
            // because value isn't needed, it is set to null
            pcs.firePropertyChange("List changed", null, list); 
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR beim Speichern der Daten!", "Speicher-Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Station> readData() {
        List<Station> list = new ArrayList<>();
        // the first time we start application there is no data file
        if(file.isFile()) {  
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                // read the whole list at once as elements of our list are serializable
                list = (List<Station>) ois.readObject();
                ois.close();
            } catch(IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "ERROR beim Laden der Daten!", "Speicher-Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
        return list;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

     
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

}
