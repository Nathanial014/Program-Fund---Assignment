package utils;

public class RabbitUtility {

    public static boolean isValidEarType(String earType) {
        if (earType == null) return false;
        return earType.equalsIgnoreCase("lop") ||
                earType.equalsIgnoreCase("upright");
    }

    public static boolean isValidFurColour(String furColour) {
        if (furColour == null) return false;
        return furColour.equalsIgnoreCase("solid") ||
                furColour.equalsIgnoreCase("spotted") ||
                furColour.equalsIgnoreCase("broken");
    }
}