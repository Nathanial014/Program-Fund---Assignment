package utils;

public class CatToyUtility {

    private static final String[] VALID_TOYS = {
            "Feather Wand",
            "Laser Pointer",
            "Scratching Post",
            "Catnip Mouse",
            "Ball"
    };

    public static boolean isValidToy(String toy) {
        if (toy == null || toy.isBlank()) return false;

        for (String t : VALID_TOYS) {
            if (t.equalsIgnoreCase(toy)) {
                return true;
            }
        }
        return false;
    }
}

