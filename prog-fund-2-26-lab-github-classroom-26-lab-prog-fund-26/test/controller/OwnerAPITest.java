package controller;

import controllers.OwnerAPI;
import models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OwnerAPITest {

    private OwnerAPI api;

    @BeforeEach
    void setup() {
        api = new OwnerAPI();
    }

    @Test
    void addOwnerIncreasesCount() {
        api.addOwner(new Owner("Alice", "123"));
        assertEquals(1, api.numberOfOwners());
    }

    @Test
    void deleteOwnerByIndexRemovesCorrectOwner() {
        Owner o1 = new Owner("Alice", "123");
        Owner o2 = new Owner("Bob", "456");
        api.addOwner(o1);
        api.addOwner(o2);
        Owner removed = api.deleteOwnerByIndex(0);
        assertEquals(o1, removed);
    }

    @Test
    void getOwnerByNameFindsOwner() {
        Owner o = new Owner("Alice", "123");
        api.addOwner(o);
        assertEquals(o, api.getOwnerByName("alice"));
    }

    @Test
    void isValidIndexWorks() {
        api.addOwner(new Owner("Alice", "123"));
        assertTrue(api.isValidIndex(0));
        assertFalse(api.isValidIndex(1));
    }
}
