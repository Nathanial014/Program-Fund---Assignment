package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatTest {

    @Test
    void favouriteToyValidatedByUtility() {
        Owner o = new Owner("Bob", "999");
        Cat c = new Cat("Misty", 2, o, 1, 'F', true, 4, false,
                false, "InvalidToyName");
        assertEquals("Unknown", c.getFavouriteToy());
    }

    @Test
    void indoorCatsPayHigherRate() {
        Owner o = new Owner("Bob", "999");

        Cat indoor = new Cat("Misty", 2, o, 1, 'F', true, 4, false,
                true, "Ball");
        Cat outdoor = new Cat("Misty", 2, o, 2, 'F', true, 4, false,
                false, "Ball");

        indoor.checkIn(0);
        indoor.checkIn(1);

        outdoor.checkIn(0);

        assertTrue(indoor.calculateWeeklyFee() > outdoor.calculateWeeklyFee());
    }
}
