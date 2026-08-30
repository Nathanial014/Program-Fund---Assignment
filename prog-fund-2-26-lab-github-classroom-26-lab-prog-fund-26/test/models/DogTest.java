package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void breedIsValidatedAndTruncated() {
        Owner o = new Owner("Bob", "999");
        Dog d = new Dog("Rex", 3, o, 1, 'M', true, 20, false,
                "ThisBreedNameIsWayTooLongForSpec", true);
        assertTrue(d.getBreed().length() <= 20);
    }

    @Test
    void dangerousBreedUsesHigherRate() {
        Owner o = new Owner("Bob", "999");
        Dog d = new Dog("Rex", 3, o, 1, 'M', true, 20, false,
                "Bulldog", true);
        d.checkIn(0);
        d.checkIn(1);
        double dangerousRate = d.calculateWeeklyFee();
        d.setDangerousBreed(false);
        double safeRate = d.calculateWeeklyFee();

        assertTrue(dangerousRate > safeRate);
    }

    @Test
    void equalsUsesId() {
        Owner o = new Owner("Bob", "999");
        Dog d1 = new Dog("Rex", 3, o, 1, 'M', true, 20, false, "Bulldog", true);
        Dog d2 = new Dog("Max", 4, o, 1, 'M', false, 25, true, "Collie", false);
        assertEquals(d1, d2);
    }
}
