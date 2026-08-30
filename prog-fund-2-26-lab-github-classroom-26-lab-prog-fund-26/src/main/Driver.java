package main;

import controllers.PetsDayCareAPI;
import models.*;
import utils.ScannerInput;

import java.io.File;

public class Driver {

    private PetsDayCareAPI dayCare = new PetsDayCareAPI("SETU Kennels", 200, new File("pets.txt"));

    public static void main(String[] args) {
        new Driver().run();
    }

    private void run() {
        int option;

        do {
            printMainMenu();
            option = ScannerInput.readNextInt("==>> ");

            switch (option) {
                case 1 -> petsCRUDMenu();
                case 2 -> reportsMenu();
                case 3 -> searchMenu();
                case 4 -> sortMenu();
                case 10 -> saveAll();
                case 11 -> loadAll();
                case 0 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid option");
            }

        } while (option != 0);
    }

    // ------------------------------------------------------------
    // Main Menu
    // ------------------------------------------------------------
    private void printMainMenu() {
        System.out.println("""
                ------- Pet Day Care -------------
                | 1) Pets CRUD MENU              |
                | 2) Reports MENU                |
                |--------------------------------|
                | 3) Search Pets                 |
                | 4) Sort Pets                   |
                |--------------------------------|
                | 10) Save all                   |
                | 11) Load all                   |
                |--------------------------------|
                | 0) Exit                        |
                ---------------------------------
                """);
    }

    // ------------------------------------------------------------
    // CRUD Menu
    // ------------------------------------------------------------
    private void petsCRUDMenu() {
        int option;

        do {
            System.out.println("""
                    ----- Pets CRUD Menu -----
                    | 1) Add a new Pet        |
                    | 2) Delete a Pet         |
                    | 3) List all Pets        |
                    | 4) Update Pet           |
                    | 0) Return to main menu  |
                    ---------------------------
                    """);

            option = ScannerInput.readNextInt("==>> ");

            switch (option) {
                case 1 -> addPet();
                case 2 -> deletePet();
                case 3 -> System.out.println(dayCare.listAllPets());
                case 4 -> updatePet();
                case 0 -> {}
                default -> System.out.println("Invalid option");
            }

        } while (option != 0);
    }

    private void addPet() {
        System.out.println("Add Pet:");
        String name = ScannerInput.readNextString("Enter name: ");
        int age = ScannerInput.readNextInt("Enter age: ");
        String ownerName = ScannerInput.readNextString("Owner name: ");
        String ownerPhone = ScannerInput.readNextString("Owner phone: ");
        int id = ScannerInput.readNextInt("Pet ID: ");
        int days = ScannerInput.readNextInt("Days per week: ");

        Owner owner = new Owner(ownerName, ownerPhone);

        System.out.println("""
                Choose Pet Type:
                1) Dog
                2) Cat
                3) Bird
                4) Parrot
                """);

        int type = ScannerInput.readNextInt("==>> ");

        Pet pet = null;

        char sex = ScannerInput.readNextChar("Sex (M/F): ");
        boolean vaccinated = ScannerInput.readNextBoolean("Vaccinated: ");
        double weight = ScannerInput.readNextDouble("Weight (kg): ");
        boolean neutered = ScannerInput.readNextBoolean("Neutered: ");

        switch (type) {
            case 1 -> {
                String breed = ScannerInput.readNextString("Breed: ");
                boolean dangerousBreed = ScannerInput.readNextBoolean("Dangerous (true/false): ");
                pet = new Dog(name, age, owner, id,
                        sex, vaccinated, weight, neutered,
                        breed, dangerousBreed);
            }
            case 2 -> {
                boolean indoorCat = ScannerInput.readNextBoolean("Indoor Cat: ");
                String favouriteToy = ScannerInput.readNextString("Favourite Toy: ");
                pet = new Cat(name, age, owner, id,
                        sex, vaccinated, weight, neutered,
                        indoorCat, favouriteToy);
            }
            case 3 -> {
                double wingSpan = ScannerInput.readNextDouble("WingSpan: ");
                boolean canFly = ScannerInput.readNextBoolean("Can Fly (true/false): ");
                int vocab = ScannerInput.readNextInt("Vocabulary size (int): ");
                int numDaysPerWeek = ScannerInput.readNextInt("Days Attending Per Week: ");
                pet = new Parrot(name, age, owner, id,
                        wingSpan, canFly,
                        vocab, numDaysPerWeek);
            }
            default -> System.out.println("Invalid type");
        }

        if (pet != null && dayCare.addPet(pet)) {
            System.out.println("Pet added successfully.");
        } else {
            System.out.println("Failed to add pet.");
        }
    }

    private void deletePet() {
        int index = ScannerInput.readNextInt("Enter index to delete: ");
        Pet removed = dayCare.deletePetByIndex(index);

        if (removed != null) {
            System.out.println("Deleted: " + removed);
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void updatePet() {
        int index = ScannerInput.readNextInt("Enter index to update: ");

        if (!dayCare.isValidPetIndex(index)) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.println("Enter new details:");
        String name = ScannerInput.readNextString("Name: ");
        int age = ScannerInput.readNextInt("Age: ");
        String ownerName = ScannerInput.readNextString("Owner name: ");
        String ownerPhone = ScannerInput.readNextString("Owner phone: ");
        int id = ScannerInput.readNextInt("Pet ID: ");
        int days = ScannerInput.readNextInt("Days per week: ");

        Owner owner = new Owner(ownerName, ownerPhone);

        System.out.println("""
                Choose Pet Type:
                1) Dog
                2) Cat
                3) Bird
                4) Parrot
                """);

        int type = ScannerInput.readNextInt("==>> ");

        Pet updated = null;

        char sex = ScannerInput.readNextChar("Sex (M/F): ");
        boolean vaccinated = ScannerInput.readNextBoolean("Vaccinated: ");
        double weight = ScannerInput.readNextDouble("Weight (kg): ");
        boolean neutered = ScannerInput.readNextBoolean("Neutered: ");

        switch (type) {
            case 1 -> {
                String breed = ScannerInput.readNextString("Breed: ");
                boolean dangerousBreed = ScannerInput.readNextBoolean("Dangerous: ");
                updated = new Dog(name, age, owner, id,
                        sex, vaccinated, weight, neutered,
                        breed, dangerousBreed);
            }
            case 2 -> {
                boolean indoorCat = ScannerInput.readNextBoolean("Indoor: ");
                String favouriteToy = ScannerInput.readNextString("Favourite Toy: ");
                updated = new Cat(name, age, owner, id,
                        sex, vaccinated, weight, neutered,
                        indoorCat, favouriteToy);
            }
            case 3 -> {
                double wingSpan = ScannerInput.readNextDouble("WingSpan: ");
                boolean canFly = ScannerInput.readNextBoolean("Can Fly: ");
                int vocab = ScannerInput.readNextInt("Vocabulary size: ");
                int numDaysPerWeek = ScannerInput.readNextInt("Days Attending Per Week: ");
                updated = new Parrot(name, age, owner, id,
                        wingSpan, canFly,
                        vocab, numDaysPerWeek);
            }
        }

        if (updated != null) {
            dayCare.updatePet(index, updated);
            System.out.println("Pet updated.");
        }
    }

    // ------------------------------------------------------------
    // Reports Menu
    // ------------------------------------------------------------
    private void reportsMenu() {
        int option;

        do {
            System.out.println("""
                    --------- Pet Reports Menu ---------
                    | 1) List all Pets                 |
                    | 2) List all Dogs                 |
                    | 3) List all Cats                 |
                    | 4) List all Dangerous Dogs       |
                    | 5) List all Indoor Cats          |
                    | 6) Weekly Income                 |
                    | 0) Return to main menu           |
                    -----------------------------------
                    """);

            option = ScannerInput.readNextInt("==>> ");

            switch (option) {
                case 1 -> System.out.println(dayCare.listAllPets());
                case 2 -> System.out.println(dayCare.listAllDogs());
                case 3 -> System.out.println(dayCare.listAllCats());
                case 4 -> System.out.println(dayCare.listAllDangerousDogs());
                case 5 -> System.out.println(dayCare.numberOfIndoorCats());
                case 6 -> System.out.println("Weekly Income: €" + dayCare.getWeeklyIncome());
                case 0 -> {}
                default -> System.out.println("Invalid option");
            }

        } while (option != 0);
    }

    // ------------------------------------------------------------
    // Search Menu
    // ------------------------------------------------------------
    private void searchMenu() {
        System.out.println("""
                ----- Search Menu -----
                1) Search by ID
                2) Search by Name
                3) Search Dog by Owner/Breed/Age
                0) Return
                """);

        int option = ScannerInput.readNextInt("==>> ");

        switch (option) {
            case 1 -> {
                int id = ScannerInput.readNextInt("Enter ID: ");
                System.out.println(dayCare.getPetById(id));
            }
            case 2 -> {
                String name = ScannerInput.readNextString("Enter name: ");
                System.out.println(dayCare.getPet(name));
            }
            case 3 -> {
                String owner = ScannerInput.readNextString("Owner: ");
                String breed = ScannerInput.readNextString("Breed: ");
                int age = ScannerInput.readNextInt("Age: ");
                System.out.println(dayCare.findDogByOwnerAndBreedAndAge(owner, breed, age));
            }
            case 0 -> {}
            default -> System.out.println("Invalid option");
        }
    }

    // ------------------------------------------------------------
    // Sort Menu
    // ------------------------------------------------------------
    private void sortMenu() {
        System.out.println("""
                ----- Sort Menu -----
                1) Sort by ID (desc)
                2) Sort by Name (asc)
                0) Return
                """);

        int option = ScannerInput.readNextInt("==>> ");

        switch (option) {
            case 1 -> {
                dayCare.sortPetsById();
                System.out.println("Sorted by ID.");
            }
            case 2 -> {
                dayCare.sortPetsByName();
                System.out.println("Sorted by Name.");
            }
            case 0 -> {}
            default -> System.out.println("Invalid option");
        }
    }

    // ------------------------------------------------------------
    // Persistence
    // ------------------------------------------------------------
    private void saveAll() {
        dayCare.save();
        System.out.println("Saved successfully.");
    }

    private void loadAll() {
        dayCare.load();
        System.out.println("Loaded successfully.");
    }
}
