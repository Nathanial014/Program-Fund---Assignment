package models;

public abstract class Mammal extends Pet {

    // instances //
    private char sex;
    private boolean vaccinated;
    private boolean neutered;
    private double weight;

    // constructor //
    public Mammal(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered) {

        // calling the Pet constructor
        super(name, age, owner, id);

        setSex(sex);
        setVaccinated(vaccinated);
        setWeight(weight);
        setNeutered(neutered);
    }

    // getters //
    public char getSex() {
        return sex;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public double getWeight() {
        return weight;
    }

    // setters //
    public void setSex(char sex) {
        // Only allow M, F, U
        if (sex == 'M' || sex == 'F' || sex == 'U') {
            this.sex = sex;
        } else {
            this.sex = 'U'; // default
        }
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public void setWeight(double weight) {
        // Valid range: 2–200 kg
        if (weight >= 2 && weight <= 200) {
            this.weight = weight;
        }
    }

    // toString //
    @Override
    public String toString() {
        return super.toString() +
                ", sex=" + sex +
                ", vaccinated=" + vaccinated +
                ", neutered=" + neutered +
                ", weight=" + weight;
    }
}
