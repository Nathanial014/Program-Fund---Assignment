package controller;

import controllers.PetsDayCareAPI;
import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PetsDayCareAPITest {

    private PetsDayCareAPI api;
    private Owner owner;

    @BeforeEach
    void setup() {
        owner = new Owner("Alice", "123");
        api = new PetsDayCareAPI("MyDayCare", 100, new File("testPets.txt"));
    }

    private Dog sampleDog(boolean dangerous) {
        return new Dog("Rex", 3, owner, 1, 'M', true, 20, false,
                "Bulldog", dangerous);
    }

    private Cat sampleCat(boolean indoor) {
        return new Cat("Misty", 2, owner, 2, 'F', true, 4, false,
                indoor, "Ball");
    }

    private Parrot sampleParrot() {
        return new Parrot("Polly", 2, owner, 3,
                15.0, true, 50, 3);
    }

    @Test
    void addAndGetPetByIndex() {
        Pet d = sampleDog(false);
        api.addPet(d);
        assertEquals(d, api.getPet(0));
    }

    @Test
    void deletePetByIdRemovesCorrectPet() {
        Pet d = sampleDog(false);
        api.addPet(d);
        Pet removed = api.deletePetById(1);
        assertEquals(d, removed);
    }

    @Test
    void listAllPetsReturnsString() {
        api.addPet(sampleDog(false));
        api.addPet(sampleCat(true));
        String list = api.listAllPets();
        assertTrue(list.contains("Rex"));
        assertTrue(list.contains("Misty"));
    }

    @Test
    void countingMethodsWork() {
        api.addPet(sampleDog(true));
        api.addPet(sampleDog(false));
        api.addPet(sampleCat(true));
        api.addPet(sampleParrot());

        assertEquals(4, api.numberOfPets());
        assertEquals(2, api.numberOfDogs());
        assertEquals(1, api.numberOfCats());
        assertEquals(1, api.numberOfParrots());
        assertEquals(1, api.numberOfDangerousDogs());
        assertEquals(1, api.numberOfIndoorCats());
    }

    @Test
    void getWeeklyIncomeWorksEvenWithZeroDaysForMammals() {
        api.addPet(sampleDog(true));
        api.addPet(sampleCat(true));
        api.addPet(sampleParrot());
        assertTrue(api.getWeeklyIncome() > 0);
    }

    @Test
    void getAverageNumDaysPerWeekReturnsZeroWhenEmpty() {
        assertEquals(0, api.getAverageNumDaysPerWeek());
    }

    @Test
    void getAverageNumDaysPerWeekCalculatesCorrectly() {
        api.addPet(sampleParrot()); // 3 days
        api.addPet(new Parrot("Polly2", 2, owner, 4, 10.0, true, 20, 5));
        double avg = api.getAverageNumDaysPerWeek();
        assertEquals(4.0, avg, 0.001);
    }

    @Test
    void findDogByOwnerBreedAndAgeFindsMatch() {
        Dog d = sampleDog(false);
        api.addPet(d);
        Pet found = api.findDogByOwnerAndBreedAndAge("Alice", "Bulldog", 3);
        assertEquals(d, found);
    }

    @Test
    void sortPetsByNameOrdersAlphabetically() {
        api.addPet(sampleDog(false)); // Rex
        api.addPet(sampleCat(true));  // Misty
        api.sortPetsByName();
        ArrayList<Pet> pets = api.getPetsArray();
        assertEquals("Misty", pets.get(0).getName());
        assertEquals("Rex", pets.get(1).getName());
    }

    @Test
    void initNameTruncatesLongNames() {
        PetsDayCareAPI api2 = new PetsDayCareAPI("ThisNameIsWayTooLongForLimit", 10, new File("x.txt"));
        assertTrue(api2.getName().length() <= 20);
    }

    @Test
    void isValidPetIndexWorks() {
        api.addPet(sampleDog(false));
        assertTrue(api.isValidPetIndex(0));
        assertFalse(api.isValidPetIndex(1));
    }
}
