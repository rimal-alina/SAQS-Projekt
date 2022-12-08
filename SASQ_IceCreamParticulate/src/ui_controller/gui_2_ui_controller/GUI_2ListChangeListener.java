package ui_controller.gui_2_ui_controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import model.StationInterface;
import view.gui_2_view.GUI_2;

public class GUI_2ListChangeListener implements PropertyChangeListener {

    // we need parent - where we are called from, to get some inputs
    private GUI_2 parent;
    
    public GUI_2ListChangeListener(GUI_2 parent) {
        this.parent = parent;
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        // update JList for GUI
        parent.setStationList((List<StationInterface>) e.getNewValue());
        parent.updateCurrentStation();
    }   
}
