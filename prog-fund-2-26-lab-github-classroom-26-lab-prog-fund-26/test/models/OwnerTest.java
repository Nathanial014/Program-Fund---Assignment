package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    void constructorStoresFields() {
        Owner o = new Owner("Alice", "12345");
        assertEquals("Alice", o.getName());
        assertEquals("12345", o.getPhone());
    }

    @Test
    void setNameDefaultsOnBlank() {
        Owner o = new Owner("Alice", "12345");
        o.setName("   ");
        assertEquals("Unknown", o.getName());
    }

    @Test
    void setPhoneDefaultsOnBlank() {
        Owner o = new Owner("Alice", "12345");
        o.setPhone("");
        assertEquals("Unknown", o.getPhone());
    }

    @Test
    void equalsMatchesNameAndPhoneIgnoringCase() {
        Owner o1 = new Owner("Alice", "12345");
        Owner o2 = new Owner("alice", "12345");
        assertEquals(o1, o2);
    }
}
