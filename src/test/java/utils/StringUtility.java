package utils;

public class StringUtility {

    public static void verifyEquals(String expected, String actual){
        if(expected.equals(actual)) {
            System.out.println(actual);
            System.out.println("PASS");
        }
        else {
            System.out.println("FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }
}
