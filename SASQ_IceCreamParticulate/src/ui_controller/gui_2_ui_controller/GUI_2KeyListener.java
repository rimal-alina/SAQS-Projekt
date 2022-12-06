package ui_controller.gui_2_ui_controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import business_controller.DataPersistenceFile;
import business_controller.FactoryBusinessController;
import model.StationInterface;
import ui_controller.HelperFunctions;
import view.gui_2_view.GUI_2;

/*
    this class is the key listener for the JTextField actual in GUI_2
    this is a separate class (and not an inline class/method in the view),
    because it is better for readability and maintainability
*/
public class GUI_2KeyListener implements KeyListener {
    // we need parent - where we are called from, to get some inputs and write output
    private GUI_2 parent;

    public GUI_2KeyListener(GUI_2 parent) {
        this.parent = parent;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        StationInterface station = (StationInterface)parent.getSelectedStationListObject();
        station.setDate(parent.getDateText());
        // check if input is ok - else use message label and do not raise an exception
        try {
            station.setActual(HelperFunctions.getPositiveIntegerValue(parent.getActualText()));
            parent.setMessageLabelText("");
        } catch (NumberFormatException nfe) {
            // input wasn't positive integer
            parent.setMessageLabelText("ACHTUNG: Der Messwert muss positiv ganzzahlig sein!");
            parent.setVarianceText("");
            parent.setActualText("");
            // since there is no valid new actual value, but user modified field, set value to -1 in database
            station.setActual(-1);
        } finally {
            parent.setVarianceText(station.getVariance() + "");
            // change font color according to variance
            parent.setVarianceFontColor(HelperFunctions.getCalculatedFontColor(station.getVariance(), station.getTarget()));
            // save data after every command (save, add, delete) to keep database updated
            FactoryBusinessController.getDataPersistenceInstance().writeData(parent.getStationList());
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        // do nothing
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // do nothing
    }
}
