package ui_controller.GUI_1_ui_controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import model.Station;
import view.GUI_1_view.GUI_1;

public class GUI_1ListChangeListener implements PropertyChangeListener {

    // we need parent - where we are called from, to get some inputs
    private GUI_1 parent;
    
    public GUI_1ListChangeListener(GUI_1 parent) {
        this.parent = parent;
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        // update JList for GUI
        parent.setStationList((List<Station>) e.getNewValue());
        parent.updateCurrentStation();
    }   
}
