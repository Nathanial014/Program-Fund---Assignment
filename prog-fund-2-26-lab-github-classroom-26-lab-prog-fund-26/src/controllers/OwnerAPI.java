package controllers;
import models.Owner;

import java.util.ArrayList;

public class OwnerAPI {

    // field //
    private ArrayList<Owner> owners = new ArrayList<>();

    // CRUD //
    public boolean addOwner(Owner owner) {
        return owners.add(owner);
    }

    public Owner deleteOwnerByIndex(int index) {
        if (isValidIndex(index)) {
            return owners.remove(index);
        }
        return null;
    }

    public Owner deleteOwnerByName(String name) {
        for (int i = 0; i < owners.size(); i++) {
            if (owners.get(i).getName().equalsIgnoreCase(name)) {
                return owners.remove(i);
            }
        }
        return null;
    }

    public Owner getOwner(int index) {
        if (isValidIndex(index)) {
            return owners.get(index);
        }
        return null;
    }

    public Owner getOwnerByName(String name) {
        for (Owner o : owners) {
            if (o.getName().equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }

    // reporting //
    public String listOwners() {
        if (owners.isEmpty()) return "No Owners";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < owners.size(); i++) {
            sb.append(i).append(": ").append(owners.get(i)).append("\n");
        }
        return sb.toString();
    }

    public int numberOfOwners() {
        return owners.size();
    }

    // validation //
    public boolean isValidIndex(int index) {
        return index >= 0 && index < owners.size();
    }

    // update //
    public Owner updateOwner(int index, Owner updatedOwner) {
        if (!isValidIndex(index)) return null;
        owners.set(index, updatedOwner);
        return updatedOwner;
    }

    // getters //
    public ArrayList<Owner> getOwners() {
        return owners;
    }

    public void setOwners(ArrayList<Owner> owners) {
        this.owners = owners;
    }
}
