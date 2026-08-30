package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MammalTest {

    static class TestMammal extends Mammal {
        public TestMammal(String name, int age, Owner owner, int id,
                          char sex, boolean vaccinated, double weight, boolean neutered) {
            super(name, age, owner, id, sex, vaccinated, weight, neutered);
        }
        @Override
        public double calculateWeeklyFee() { return 0; }
    }

    @Test
    void sexValidationOnlyAllowsMFU() {
        Owner o = new Owner("Bob", "999");
        TestMammal m = new TestMammal("Rex", 3, o, 1, 'X', true, 10, false);
        assertEquals('U', m.getSex());
    }

    @Test
    void weightMustBeWithinRange() {
        Owner o = new Owner("Bob", "999");
        TestMammal m = new TestMammal("Rex", 3, o, 1, 'M', true, 300, false);
        assertEquals(0, m.getWeight());
    }
}
