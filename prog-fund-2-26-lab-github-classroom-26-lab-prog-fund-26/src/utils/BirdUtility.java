package utils;

public class BirdUtility {

    public static String convertVocabularySize(int size) {
        if (size <= 20) return "Basic";
        if (size <= 100) return "Good";
        return "Amazing";
    }

    public static int convertVocabularySizeReverse(String vocab) {
        if (vocab == null) return 1;

        switch (vocab.toLowerCase()) {
            case "basic": return 10;
            case "good": return 50;
            case "amazing": return 200;
            default: return 10;
        }
    }

    public static boolean isValidWingSpan(double wingSpan) {
        return wingSpan >= 3 && wingSpan <= 400;
    }
}
