package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BirdTest {

    static class TestBird extends Bird {
        public TestBird(String name, int age, Owner owner, int id,
                        double wingSpan, boolean canFly, int numDaysPerWeek) {
            super(name, age, owner, id, wingSpan, canFly, numDaysPerWeek);
        }
        @Override
        public double calculateWeeklyFee() { return 0; }
    }

    @Test
    void wingspanIsValidated() {
        Owner o = new Owner("Bob", "999");
        TestBird b = new TestBird("Polly", 2, o, 1, 500, true, 3);
        assertEquals(3, b.getWingSpan());
    }

    @Test
    void equalsChecksWingSpanAndCanFly() {
        Owner o = new Owner("Bob", "999");
        TestBird b1 = new TestBird("Polly", 2, o, 1, 10, true, 3);
        TestBird b2 = new TestBird("Polly", 2, o, 1, 10, true, 3);
        assertEquals(b1, b2);
    }
}
