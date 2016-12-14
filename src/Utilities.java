/**
 * Class containing public static methods that can be used amongst all classes
 * 
 * @author DLMcAuslan
 *
 */
public class Utilities {

    /**
     * Converts money from a string such as dd.cc to an integer value in cents.
     * @param money - Dollar value string from Json as 
     * @return integer value of the money string in cents
     */
    public static int moneyStringToInt(String money) {
        return (int) (100*Double.parseDouble(money));
    }
    
    /**
     * Converts a money value in cents to dollars and cents.
     * @param cents - integer value of money in cents
     * @return double value of money in dollars in cents
     */
    public static double centsToDollars(int cents) {
        return ((double)cents)/100;
    }

}
