package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Station;
import view.AssessmentRecordView;

/*
    this class is the key listener for the JTextField actual in AssessmentRecordView
    this is a separate class (and not an inline class/method in the view),
    because it is better for readability and maintainability.
    additionally we could reuse methods (nothing to reuse at the moment)
*/
public class AssessmentRecordKeyListener implements KeyListener {
    // we need parent - where we are called from, to get some inputs and write output
    private AssessmentRecordView parent;

    public AssessmentRecordKeyListener(AssessmentRecordView parent) {
        this.parent = parent;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        parent.setVarianceText("");
        String targetString = parent.getTargetText();
        String actualString = parent.getActualText();
        int target = 0;
        int actual = 0;

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

        Station station = new Station("", null, target, actual);
        parent.setVarianceText(station.getVariance() + "");
        // change font color according to variance
        parent.setVarianceFontColor(
                HelperFunctions.getCalculatedFontColor(
                                    station.getVariance(), station.getTarget()));
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
