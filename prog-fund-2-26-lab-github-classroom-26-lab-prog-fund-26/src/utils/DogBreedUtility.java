package utils;

public class DogBreedUtility {

    private static final String[] VALID_BREEDS = {
            "German Shepherd",
            "Labrador",
            "Golden Retriever",
            "Bulldog",
            "Poodle",
            "Beagle",
            "Rottweiler"
    };

    public static boolean isValidBreed(String breed) {
        if (breed == null || breed.isBlank()) return false;

        for (String b : VALID_BREEDS) {
            if (b.equalsIgnoreCase(breed)) {
                return true;
            }
        }
        return false;
    }
}

