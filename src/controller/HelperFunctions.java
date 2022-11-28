package controller;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
   The purpose of this class is to provide helper functions which are
   used by more than one class.
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


    public static boolean isInteger(String value) {
        try {
            int intValue = Integer.parseInt(value);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public static int getIntegerValue(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException nfe) {
            return -1;
        }
    }


    public static boolean isDate(String value) {
        try {
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(value);  
        } catch(ParseException pe) {
            return false;
        }
        return true;
    }


    public static Date getDateValue(String value) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(value);  
        } catch(ParseException pe) {
            return null;
        }
    }

}
