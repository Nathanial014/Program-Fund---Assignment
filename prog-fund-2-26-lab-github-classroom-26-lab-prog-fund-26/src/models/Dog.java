package models;

import java.security.PublicKey;

public class Dog extends Mammal {

    // Constants //
    public static final float DANGEROUS_DAILY_RATE = 40f;
    public static final float NONDANGEROUS_DAILY_RATE = 30f;

    // instances //
    private String breed;
    private boolean dangerousBreed;

    // constructor //
    public Dog(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered,
               String breed, boolean dangerousBreed) {

        // call mammal first
        super(name, age, owner, id, sex, vaccinated, weight, neutered);

        setBreed(breed);
        setDangerousBreed(dangerousBreed);
    }

    // getters //
    public String getBreed() {
        return breed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public boolean isDangerous() {
        return dangerousBreed;
    }

    // setters //
    public void setBreed(String breed) {
        // Use DogBreedUtility to validate breed
        if (utils.DogBreedUtility.isValidBreed(breed)) {
            // Truncate to 20 chars max (spec requirement)
            if (breed.length() > 20) {
                this.breed = breed.substring(0, 20);
            } else {
                this.breed = breed;
            }
        } else {
            this.breed = "Unknown"; // fallback if invalid
        }
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    // weekly fee calculation //

    @Override
    public double calculateWeeklyFee() {
        float rate = dangerousBreed ? DANGEROUS_DAILY_RATE : NONDANGEROUS_DAILY_RATE;
        return rate * numOfDaysAttending();
    }

    // equals //
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dog)) {
            return false;
        }
        Dog other = (Dog) obj;
        return this.getId() == other.getId();
    }

    // toString //
    @Override
    public String toString() {
        return super.toString() +
                ", breed=" + breed +
                ", dangerousBreed=" + dangerousBreed;
    }
}
