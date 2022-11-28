package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Station;
import view.AssessmentRecordView;

/*
    this class is the list selection listener for the JList in AssessmentRecordView
    this is a separate class (and not an inline class/method in the view),
    because it is better for readability and maintainability.
    additionally we could reuse methods (nothing to reuse at the moment)
*/
public class AssessmentRecordListSelectionListener implements ListSelectionListener {
    // we need parent - where we are called from, to get some inputs and write output
    private AssessmentRecordView parent;

    public AssessmentRecordListSelectionListener(AssessmentRecordView parent) {
        this.parent = parent;
    }

    @Override
    public void valueChanged(ListSelectionEvent arg0) {
        if (!arg0.getValueIsAdjusting()) {
            if(parent.getSelectedStationListIndex() >= 0) {
                parent.setMessageLabelText(" "); // reset message label due to new action
                Station station = (Station)parent.getSelectedStationListObject();
                parent.setStationIdText(station.getStationId());
                if(station.getDate() != null) {
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    parent.setDateText(dateFormat.format(station.getDate()));
                }
                else {
                    parent.setDateText("");
                }
                parent.setTargetText(station.getTarget() + "");
                parent.setActualText(station.getActual() + "");
                parent.setVarianceText(station.getVariance() + "");
                // change font color according to variance
                parent.setVarianceFontColor(
                    HelperFunctions.getCalculatedFontColor(
                                        station.getVariance(), station.getTarget()));

                parent.enablePossibleTextFields();
            }
            else {   // nothing selected -> clear textfields
                parent.setStationIdText("");
                parent.setDateText("");
                parent.setTargetText("");
                parent.setActualText("");
                parent.setVarianceText("");
                // change font color according to variance
                parent.setVarianceFontColor(HelperFunctions.getCalculatedFontColor(0, 0));

                // if nothing is selected the textfields should not be enabled
                // as the question will come up: which station will we modify with our input?!
                parent.disableAllTextFields();
            }
        }
    }
}
