package ui_controller;

import java.awt.Color;

/*
   The purpose of this class is to provide helper functions which are
   used by more than one class / more than one GUI
   Provide public static methods to call the methods without an instance of 
   the class HelperFunctions.
*/
public class HelperFunctions {

    public static Color getCalculatedFontColor(int variance, int target) {
        // please note that we multiply with 1.0 so that we receive a double value instead of int
        if((1.0 * variance / target) >= 0.05) {
            return new Color(25, 200, 0);  // darker green than 0, 255, 0 which is more readable
        }
        else if((1.0 * variance / target) <= -0.1) {
            return Color.RED;
        }
        return Color.BLACK;
    }

    public static int getPositiveIntegerValue(String value) throws NumberFormatException{
        int intValue = Integer.parseInt(value);
        if (intValue >= 0) {
            return intValue;
        }
        throw new NumberFormatException();
    }
}
