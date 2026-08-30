package models;

public abstract class Bird extends Pet {

    // fields //
    private double wingSpan;
    private boolean canFly;

    // constructor //
    public Bird(String name, int age, Owner owner, int id, double wingSpan, boolean canFly, int numDaysPerWeek) {
        super(name, age, owner, id, numDaysPerWeek);
        setWingSpan(wingSpan);   // use setter for validation
        this.canFly = canFly;
    }

    // getters and setters //
    public double getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(double wingSpan) {
        if (wingSpan < 3 || wingSpan > 400) {
            this.wingSpan = 3;   // default value
        } else {
            this.wingSpan = wingSpan;
        }
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    // toString //
    @Override
    public String toString() {
        return super.toString() +
                ", wingspan=" + wingSpan +
                "cm, canFly=" + canFly;
    }

    // equals //
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Bird)) return false;

        Bird other = (Bird) obj;

        return this.getId() == other.getId() &&
                this.getWingSpan() == other.getWingSpan() &&
                this.isCanFly() == other.isCanFly();
    }
}
