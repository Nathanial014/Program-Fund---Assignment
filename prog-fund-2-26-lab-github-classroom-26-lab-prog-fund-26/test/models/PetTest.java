package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    static class TestPet extends Pet {
        public TestPet(String name, int age, Owner owner, int id) {
            super(name, age, owner, id);
        }
        @Override
        public double calculateWeeklyFee() { return 0; }
    }

    @Test
    void nameIsTruncatedTo30Chars() {
        Owner o = new Owner("Bob", "999");
        String longName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        Pet p = new TestPet(longName, 3, o, 1);
        assertTrue(p.getName().length() <= 30);
    }

    @Test
    void checkInMarksDayTrue() {
        Owner o = new Owner("Bob", "999");
        Pet p = new TestPet("Rex", 3, o, 1);
        p.checkIn(2);
        assertTrue(p.getDaysAttending()[2]);
    }

    @Test
    void checkOutMarksDayFalse() {
        Owner o = new Owner("Bob", "999");
        Pet p = new TestPet("Rex", 3, o, 1);
        p.checkIn(2);
        p.checkOut(2);
        assertFalse(p.getDaysAttending()[2]);
    }

    @Test
    void numOfDaysAttendingCountsCorrectly() {
        Owner o = new Owner("Bob", "999");
        Pet p = new TestPet("Rex", 3, o, 1);
        p.checkIn(0);
        p.checkIn(1);
        assertEquals(2, p.numOfDaysAttending());
    }
}
