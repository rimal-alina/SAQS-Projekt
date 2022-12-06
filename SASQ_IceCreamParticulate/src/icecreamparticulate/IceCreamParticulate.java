package icecreamparticulate;

import business_controller.FactoryBusinessController;
import view.gui_1_view.GUI_1;
import view.gui_2_view.GUI_2;

public class IceCreamParticulate {
    public static void main(String[] args) {
        //create object for automatic station creation, if not already running (singleton)
        FactoryBusinessController.createStationCreator();
        // choose between GUIs by commentting them out
        new GUI_1();
        new GUI_2();
    }
    
}
