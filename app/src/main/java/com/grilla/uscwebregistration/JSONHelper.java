package com.grilla.uscwebregistration;

/**
 * @author Bill Derouin <bill@billderouin.com>
 */
public class JSONHelper {
    public static String term = "20151";

    /**
     * Base of all webreg api calls
     */
    public static String API_URL = "http://petri.esd.usc.edu/socAPI/";

    /**
     * Url to call a courses query
     */
    public static String COURSES_URL = API_URL + "/Courses/" + term + "/";

     /**
     * Url to load schools/departments for a single school (if school given)
     */
    public static String SCHOOLS_URL = API_URL + "/Schools/";

    /**
     * Url to load term data for term(s)
     */
    public static String TERMS_URL = API_URL + "/Terms/";

    /**
     * Compares two times given in a string format, for example
     * to compare two sections to see if they conflict.
     * @param t1    first time
     * @param t2    second time
     * @return      result of comparison
     *              1 - t1 is later
     *              0 - times are equal
     *              -1 - t2 is later
     */
    public static int compareTimes(String t1, String t2) {
        // First have to remove colon and convert to int
        // StringBuilder can delete characters in the middle of a String
        StringBuilder string1 = new StringBuilder(t1);
        string1.deleteCharAt(t1.indexOf(':'));
        int time1 = Integer.valueOf(string1.toString());

        StringBuilder string2 = new StringBuilder(t2);
        string2.deleteCharAt(t2.indexOf(':'));
        int time2 = Integer.valueOf(string2.toString());

        // Final comparison
        if (time1 > time2)
            return 1;
        else if (time1 == time2)
            return 0;
        else
            return -1;
    }
}
