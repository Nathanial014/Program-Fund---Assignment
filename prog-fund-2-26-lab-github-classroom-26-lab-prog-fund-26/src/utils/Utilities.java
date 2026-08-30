package utils;

public class Utilities {

    public static boolean validString(String str) {
        return str != null && !str.isBlank();
    }

    public static boolean validRange(int num, int min, int max) {
        return num >= min && num <= max;
    }

    public static boolean validDoubleRange(double num, double min, double max) {
        return num >= min && num <= max;
    }

    public static String truncate(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength);
    }

    public static boolean isNumeric(String str) {
        if (str == null) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}