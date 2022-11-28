package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import model.Station;
import view.AssessmentRecordView;
import view.StationView;

/*
    this class is the action listener for all buttons of the AssessmentRecordView
    this is a separate class (and not an inline class/method in the view),
    because it handels several commands and this is better 
    for readability and maintainability.
    additionally we can reuse methods (like 'saveData')
*/
public class AssessmentRecordController implements ActionListener {
    // we need parent - where we are called from, to get some inputs and write output
    private AssessmentRecordView parent;
    
    public AssessmentRecordController(AssessmentRecordView parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.setMessageLabelText(" "); // reset message label due to new action
        // check which command is executed
        if(e.getActionCommand().equals("Speichern")) {
            executeSave();
            saveData();
        }
        else if(e.getActionCommand().equals("Neue Station")) {
            executeNewStation();
        }
        else if(e.getActionCommand().equals("Station entfernen")) {
            executeDeleteStation();
            saveData();
        }
    }


    public void executeSave() {
        parent.setVarianceText("");
        String stationId = parent.getStationIdText();
        String dateString = parent.getDateText();
        String targetString = parent.getTargetText();
        String actualString = parent.getActualText();
        int target = 0;
        int actual = 0;
        Date date = null;

        // check if input is ok - else use message label and do not raise an exception
        if(HelperFunctions.isInteger(targetString))
            target = HelperFunctions.getIntegerValue(targetString);
        else {
            parent.setMessageLabelText("ERROR: Target muss Integer sein!");
            return;
        }
        if(HelperFunctions.isInteger(actualString))
            actual = HelperFunctions.getIntegerValue(actualString);
        else {
            parent.setMessageLabelText("ERROR: Actual muss Integer sein!");
            return;
        }
        if(HelperFunctions.isDate(dateString))
            date = HelperFunctions.getDateValue(dateString);
        else {
            parent.setMessageLabelText("ERROR: Date muss im Format 'dd.MM.yyyy' sein!");
            return;
        }

        Station station = new Station(stationId, date, target, actual);
        parent.changeSelectedStationListObject(station);
        parent.setVarianceText(station.getVariance() + "");
        // change font color according to variance
        parent.setVarianceFontColor(
                HelperFunctions.getCalculatedFontColor(
                                    station.getVariance(), station.getTarget()));
    }


    public void executeNewStation() {
        StationView sv = new StationView(this);
        String stationId = getNewStationId();
        sv.setStationIdText(stationId);
    }


    public void executeDeleteStation() {
        int index = parent.getSelectedStationListIndex();
        if(index >= 0) {
            parent.removeStationFromListAt(index);
        }
        else {
            parent.setMessageLabelText("ERROR: Keine Station zum Löschen selektiert!");
        }
    }


    public void addStation(Station station) {
        parent.addStationToList(station);
    }


    // save data after every command (save, add, delete) to keep database updated
    public void saveData() {
        DataPersistenceFile.getInstance().writeData(parent.getStationList());
    }


    public static String getNewStationId() {
        return "ID-" + new Date().getTime();
    }
}
