package icecreamparticulate;

// Main class - starting point

import controller.StationTimerTask;
import java.util.Timer;
import view.AssessmentRecordView;

public class IceCreamParticulate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create new object of the main view
        AssessmentRecordView arv = new AssessmentRecordView();
        StationTimerTask stt = new StationTimerTask(arv);
        Timer timer = new Timer();
        timer.schedule(stt, 20000, 20000);  // schedule every 20 seconds
    }
    
}
