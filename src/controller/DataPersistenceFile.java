package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
    implementation of DataPersistence into a file
*/
public class DataPersistenceFile implements DataPersistence {
    private static DataPersistenceFile instance; // needed for singleton pattern
    private File file;


    // use Singleton pattern to make sure that only one object exists
    // this object reads and writes data from/to the same file
    // first time we refer to DataPersistenceFile - we need to create the object
    
     
    private DataPersistenceFile() { // constructor private due to singleton-pattern
        // we also could ask the user for a file, but this is easier
        file = new File(System.getProperty("user.dir").concat("/icecreamparticulate.dat"));
    }

    /*
        Singleton-pattern. we only want to have exact one instance, so that
        the file-object is created only once and we use the same file every time.
        as the file is hardcoded it does not has so much effect, 
        but if we would ask the user for selecting a file, this makes much more sense.
        In code we need to call the class via the 'getInstance' method
        and can not call the constructor directly.
    */
    public static DataPersistenceFile getInstance() {
        if(instance == null)
            instance = new DataPersistenceFile();
        return instance;
    }

    
    @Override
    public void writeData(List list) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write the whole list at once as elements of our list are serializable
            oos.writeObject(list);
            oos.close();
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR beim Speichern der Daten!", "Speicher-Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public List readData() {
        List list = new ArrayList<>();
        if(file.isFile()) {  // the first time we start application there is no data file
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                // read the whole list at once as elements of our list are serializable
                list = (List) ois.readObject();
                ois.close();
            } catch(IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "ERROR beim Laden der Daten!", "Speicher-Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
        return list;
    }

}
