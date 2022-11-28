package controller;

import java.util.TimerTask;
import view.AssessmentRecordView;

/*
    this class is the TimerTask for the Main
    this class creates a new station periodically.
    this is a separate class (and not an inline class/method),
    because it is better for readability and maintainability.
    additionally we could reuse methods (nothing to reuse at the moment)
*/
public class StationTimerTask extends TimerTask {
    // we need parent - where the list should be adapted
    private AssessmentRecordView parent;

    public StationTimerTask(AssessmentRecordView parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        String stationId = AssessmentRecordController.getNewStationId();
        StationController.saveStation(parent.getController(), stationId, calculateTarget());
    }

    private int calculateTarget(){
        // simulate complex atmosperic modeling to calculate target between 1 and 100
        int target = 1 + (int)(Math.random()*100);
        return target;
    }
}
