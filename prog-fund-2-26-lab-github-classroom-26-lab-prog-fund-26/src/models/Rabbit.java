package models;

import utils.RabbitUtility;

public class Rabbit extends Mammal {

    private String earType;        // "lop" or "upright"
    private double earLengthCm;    // >= 0
    private String furColour;      // "solid", "spotted", "broken"
    private String favouriteFood;  // extra field

    public Rabbit(String name, int age, Owner owner, int id,
                  char sex, boolean vaccinated, double weight, boolean neutered,
                  String earType, double earLengthCm, String furColour, String favouriteFood) {

        super(name, age, owner, id, sex, vaccinated, weight, neutered);

        setEarType(earType);
        setEarLengthCm(earLengthCm);
        setFurColour(furColour);
        setFavouriteFood(favouriteFood);
    }

    // getters
    public String getEarType() {
        return earType;
    }

    public double getEarLengthCm() {
        return earLengthCm;
    }

    public String getFurColour() {
        return furColour;
    }

    public String getFavouriteFood() {
        return favouriteFood;
    }

    // setters
    public void setEarType(String earType) {
        if (RabbitUtility.isValidEarType(earType)) {
            this.earType = earType.toLowerCase();
        } else {
            this.earType = "upright"; // default
        }
    }

    public void setEarLengthCm(double earLengthCm) {
        if (earLengthCm >= 0) {
            this.earLengthCm = earLengthCm;
        } else {
            this.earLengthCm = 0;
        }
    }

    public void setFurColour(String furColour) {
        if (RabbitUtility.isValidFurColour(furColour)) {
            this.furColour = furColour.toLowerCase();
        } else {
            this.furColour = "solid"; // default
        }
    }

    public void setFavouriteFood(String favouriteFood) {
        if (favouriteFood == null || favouriteFood.isBlank()) {
            this.favouriteFood = "Carrots";
        } else {
            this.favouriteFood = favouriteFood;
        }
    }

    @Override
    public double calculateWeeklyFee() {
        // Rabbits: flat rate 15 per day
        return 15 * numOfDaysAttending();
    }

    @Override
    public String toString() {
        return super.toString() +
                ", earType=" + earType +
                ", earLengthCm=" + earLengthCm +
                ", furColour=" + furColour +
                ", favouriteFood=" + favouriteFood;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Rabbit)) return false;

        Rabbit other = (Rabbit) obj;

        return this.earType.equals(other.earType)
                && this.earLengthCm == other.earLengthCm
                && this.furColour.equals(other.furColour)
                && this.favouriteFood.equals(other.favouriteFood);
    }
}
