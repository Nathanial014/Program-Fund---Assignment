package models;

import utils.Utilities;

public abstract class Pet {

    // static field //
    private static int nextId = 1000;

    // instance fields //
    private int id;
    private String name;
    private int age;
    private Owner owner;
    private boolean[] daysAttending;


    // constructor //
    public Pet(String name, int age, Owner owner, int id) {
        // If id is passed as 0, generate automatically
        if (id == 0) {
            this.id = nextId++;
        } else {
            this.id = id;
        }

        setName(name);   // ensures truncation to 30 chars
        setAge(age);
        setOwner(owner);

        // Default: all days false (not attending)
        this.daysAttending = new boolean[]{false, false, false, false, false, false};
    }

    // getters //
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean[] getDaysAttending() {
        return daysAttending;
    }


    // setters //
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        initName(name);
    }

    public void initName(String name) {
        if (name.length() > 30) {
            this.name = name.substring(0, 30);
        } else {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setDaysAttending(boolean[] daysAttending) {
        if (daysAttending != null && daysAttending.length == 6) {
            this.daysAttending = daysAttending;
        }
    }

    public void checkIn(int dayIndex) {
        // Valid days: 0–5
        if (dayIndex >= 0 && dayIndex <= 5) {
            daysAttending[dayIndex] = true;
        }
    }

    public void checkOut(int dayIndex) {
        if (dayIndex >= 0 && dayIndex <= 5) {
            daysAttending[dayIndex] = false;
        }
    }

    public int numOfDaysAttending() {
        int count = 0;
        for (boolean attending : daysAttending) {
            if (attending) {
                count++;
            }
        }
        return count;
    }

    // abstract method //
    public abstract double calculateWeeklyFee();

    // toString //
    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", owner=" + owner +
                ", daysAttending=" + Arrays.toString(daysAttending) +
                '}';
    }
}
