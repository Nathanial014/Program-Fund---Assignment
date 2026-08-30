package models;

public class Cat extends Mammal {

    // instance //
    private boolean indoorCat;
    private String favouriteToy;

    // constructor //
    public Cat(String name, int age, Owner owner, int id,
               char sex, boolean vaccinated, double weight, boolean neutered,
               boolean indoorCat, String favouriteToy) {

        // call mammal constructor first //
        super(name, age, owner, id, sex, vaccinated, weight, neutered);

        setIndoorCat(indoorCat);
        setFavouriteToy(favouriteToy);
    }

    // getters //
    public boolean isIndoorCat() {
        return indoorCat;
    }

    public boolean isIndoor() {
        return indoorCat;
    }

    public String getFavouriteToy() {
        return favouriteToy;
    }

    // setters //
    public void setIndoorCat(boolean indoorCat) {
        this.indoorCat = indoorCat;
    }

    public void setFavouriteToy(String favouriteToy) {
        // Validate using CatToyUtility
        if (utils.CatToyUtility.isValidToy(favouriteToy)) {
            this.favouriteToy = favouriteToy;
        } else {
            this.favouriteToy = "Unknown";
        }
    }

    // weekly fee calculation //
    @Override
    public double calculateWeeklyFee() {
        // Base rate: 20 per day
        // Indoor cats: +5 per day
        double dailyRate = 20;

        if (indoorCat) {
            dailyRate += 5;
        }

        return dailyRate * numOfDaysAttending();
    }

    // toString //
    @Override
    public String toString() {
        return super.toString() +
                ", indoorCat=" + indoorCat +
                ", favouriteToy=" + favouriteToy;
    }
}
