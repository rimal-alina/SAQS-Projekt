package ui_controller.GUI_1_ui_controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import business_controller.DataPersistenceFile;
import model.Station;
import ui_controller.HelperFunctions;
import view.GUI_1_view.GUI_1;

/*
    this class is the key listener for the JTextField actual in GUI_1
    this is a separate class (and not an inline class/method in the view),
    because it is better for readability and maintainability
*/
public class GUI_1KeyListener implements KeyListener {
    // we need parent - where we are called from, to get some inputs and write output
    private GUI_1 parent;

    public GUI_1KeyListener(GUI_1 parent) {
        this.parent = parent;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Station station = (Station)parent.getSelectedStationListObject();
        station.setDate(parent.getDateText());
        // check if input is ok - else use message label and do not raise an exception
        try {
            station.setActual(HelperFunctions.getPositiveIntegerValue(parent.getActualText()));
            parent.setMessageLabelText("");
        } catch (NumberFormatException nfe) {
            // input wasn't positive integer
            parent.setMessageLabelText("ATTENTION! Actual value needs to be an positive integer");
            parent.setVarianceText("");
            parent.setActualText("");
            // since there is no valid new actual value, but user modified field, set value to -1 in database
            station.setActual(-1);
        } finally {
            parent.setVarianceText(station.getVariance() + "");
            // change font color according to variance
            parent.setVarianceFontColor(HelperFunctions.getCalculatedFontColor(station.getVariance(), station.getTarget()));
            // save data after every command (save, add, delete) to keep database updated
            DataPersistenceFile.getInstance().writeData(parent.getStationList());
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
