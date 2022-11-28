package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Station;
import view.StationView;

/*
    this class is the action listener for all buttons of the StationView
    this is a separate class (and not an inline class/method in the view),
    because it handels several commands and this is better 
    for readability and maintainability.
    additionally we could reuse methods (nothing to reuse at the moment)
*/
public class StationController implements ActionListener {
    // we need parent - where we are called from, to get some inputs
    private StationView parent;
    
    public StationController(StationView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // check which command is executed
        if(e.getActionCommand().equals("Speichern")) {
            executeSave();
        }
        else if(e.getActionCommand().equals("Abbrechen")) {
            executeCancel();
        }
    }


    public void executeSave() {
        String stationId = parent.getStationIdText();
        String targetString = parent.getTargetText();
        int target = 0;

        // check if input is ok - else use message label and do not raise an exception
        if(stationId.isEmpty()) {
            parent.setMessageLabelText("ERROR: Station ID darf nicht leer sein!");
            return;
        }
        if(HelperFunctions.isInteger(targetString))
            target = HelperFunctions.getIntegerValue(targetString);
        else {
            parent.setMessageLabelText("ERROR: Target muss Integer sein!");
            return;
        }

        saveStation(parent.getParentController(), stationId, target);
        parent.dispose(); // close dialog window and return to main frame
    }


    public void executeCancel() {
        parent.dispose(); // close dialog window and return to main frame
    }

    public static void saveStation(AssessmentRecordController parent, String stationId, int target) {
        Station station = new Station(stationId, target);
        parent.addStation(station);
        parent.saveData();
    }
}
