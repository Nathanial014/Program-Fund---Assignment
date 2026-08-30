package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RabbitTest {

    @Test
    void earTypeValidationWorks() {
        Owner o = new Owner("Alice", "123");
        Rabbit r = new Rabbit("Bunny", 2, o, 1,
                'F', true, 3, false,
                "invalid", 10, "solid", "Carrots");

        assertEquals("upright", r.getEarType());
    }

    @Test
    void earLengthMustBeNonNegative() {
        Owner o = new Owner("Alice", "123");
        Rabbit r = new Rabbit("Bunny", 2, o, 1,
                'F', true, 3, false,
                "lop", -5, "solid", "Carrots");

        assertEquals(0, r.getEarLengthCm());
    }

    @Test
    void furColourValidationWorks() {
        Owner o = new Owner("Alice", "123");
        Rabbit r = new Rabbit("Bunny", 2, o, 1,
                'F', true, 3, false,
                "lop", 10, "invalid", "Carrots");

        assertEquals("solid", r.getFurColour());
    }

    @Test
    void favouriteFoodDefaultsIfBlank() {
        Owner o = new Owner("Alice", "123");
        Rabbit r = new Rabbit("Bunny", 2, o, 1,
                'F', true, 3, false,
                "lop", 10, "solid", "");

        assertEquals("Carrots", r.getFavouriteFood());
    }

    @Test
    void weeklyFeeUsesDaysAttending() {
        Owner o = new Owner("Alice", "123");
        Rabbit r = new Rabbit("Bunny", 2, o, 1,
                'F', true, 3, false,
                "lop", 10, "solid", "Carrots");

        r.checkIn(0);
        r.checkIn(1);

        assertEquals(30, r.calculateWeeklyFee());
    }
}
