package business_controller;
import java.beans.PropertyChangeListener;
import java.util.List;
import model.StationInterface;

public interface DataPersistenceInterface {
    public void writeData(List<StationInterface> list);
    public List<StationInterface> readData();
    public void addPropertyChangeListener(PropertyChangeListener listener);
    public void removePropertyChangeListener(PropertyChangeListener listener);
}