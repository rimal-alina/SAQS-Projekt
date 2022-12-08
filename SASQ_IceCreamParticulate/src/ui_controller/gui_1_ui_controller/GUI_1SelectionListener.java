package ui_controller.gui_1_ui_controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.gui_1_view.GUI_1;

/*
    this class is the list selection listener for the JList in GUI_1
    this is a separate class (and not an inline class/method in the view),
    because it is better for readability and maintainability
*/
public class GUI_1SelectionListener implements ListSelectionListener {
    // we need parent - where we are called from, to get some inputs and write output
    private GUI_1 parent;

    public GUI_1SelectionListener(GUI_1 parent) {
        this.parent = parent;
    }

    @Override
    public void valueChanged(ListSelectionEvent arg0) {
        if (!arg0.getValueIsAdjusting()) {
            if(parent.getSelectedStationListIndex() >= 0) {
                parent.updateCurrentStation();
            }else {
                // nothing selected -> clear textfields
                parent.clearCurrentStation();
            } 
        }       
    }
}
