package controllers;

import models.Pet;
import utils.ISerializer;

import java.io.File;
import java.util.ArrayList;

public class PetsDayCareAPI implements ISerializer {

    // fields //
    private ArrayList<Pet> pets;
    private int maxNumberOfPets;
    private String name;
    private File file;

    // constructor //
    public PetsDayCareAPI(String name, int maxNumberOfPets, File file) {
        this.pets = new ArrayList<>();
        initName(name);
        this.maxNumberOfPets = maxNumberOfPets;
        this.file = file;
    }

    // CRUD menu //
    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    public Pet deletePetByIndex(int index) {
        if (isValidPetIndex(index)) {
            return pets.remove(index);
        }
        return null;
    }

    public Pet deletePetById(int id) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == id) {
                return pets.remove(i);
            }
        }
        return null;
    }

    public Pet getPet(int index) {
        if (isValidPetIndex(index)) {
            return pets.get(index);
        }
        return null;
    }

    public Pet getPet(String name) {
        for (Pet p : pets) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public Pet getPetById(int id) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // report methods //
    public String listAllPets() {
        if (pets.isEmpty()) return "No Pets";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pets.size(); i++) {
            sb.append(i).append(": ").append(pets.get(i)).append("\n");
        }
        return sb.toString()
    }

    public String listAllCats() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Cat) {
                sb.append(i).append(": ").append(pets.get(i)).append("\n");
            }
        }
        return sb.length() == 0 ? "No cats" : sb.toString();
    }

    public String listAllDogs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Dog) {
                sb.append(i).append(": ").append(pets.get(i)).append("\n");
            }
        }
        return sb.length() == 0 ? "No Dogs" : sb.toString();
    }

    public String listAllParrots() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Parrot) {
                sb.append(i).append(": ").append(pets.get(i)).append("\n");
            }
        }
        return sb.length() == 0 ? "No Parrots" : sb.toString();
    }

    public String listAllDangerousDogs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i) instanceof Dog && ((Dog) pets.get(i)).isDangerous()) {
                sb.append(i).append(": ").append(pets.get(i)).append("\n");
            }
        }
        return sb.length() == 0 ? "No Dangerous Dogs in the Kennels" : sb.toString();
    }

    public String listAllPetsByOwner(String owner) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getOwner().getName().equalsIgnoreCase(owner)) {
                sb.append(i).append(": ").append(pets.get(i)).append("\n");
            }
        }
        return sb.length() == 0 ? "No Pet with owner " + owner : sb.toString();
    }

    public String listAllPetsThatStayMoreThanDays(int days) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getNumDaysPerWeek() > days) {
                sb.append(i).append(": ").append(pets.get(i)).append("\n");
            }
        }
        return sb.length() == 0 ? "No Pet stays longer than " + days : sb.toString();
    }

    // counting methods //
    public int numberOfPets() {
        return pets.size();
    }

    public int numberOfCats() {
        int count = 0;
        for (Pet p : pets) if (p instanceof Cat) count++;
        return count;
    }

    public int numberOfDogs() {
        int count = 0;
        for (Pet p : pets) if (p instanceof Dog) count++;
        return count;
    }

    public int numberOfParrots() {
        int count = 0;
        for (Pet p : pets) if (p instanceof Parrot) count++;
        return count;
    }

    public int numberOfDangerousDogs() {
        int count = 0;
        for (Pet p : pets)
            if (p instanceof Dog && ((Dog) p).isDangerous()) count++;
        return count;
    }

    public int numberOfIndoorCats() {
        int count = 0;
        for (Pet p : pets)
            if (p instanceof Cat && ((Cat) p).isIndoor()) count++;
        return count;
    }

    public int numberOfParrotsByVocabularySize(int vocabSize) {
        int count = 0;
        for (Pet p : pets)
            if (p instanceof Parrot &&
                    ((Parrot) p).getVocabularySize().equalsIgnoreCase(
                            BirdUtility.convertVocabularySize(vocabSize))) {
                count++;
            }
        return count;
    }

    // update //
    public Pet updatePet(int index, Pet updatedPet) {
        if (!isValidPetIndex(index)) return null;
        pets.set(index, updatedPet);
        return updatedPet;
    }

    // validation //
    public boolean isValidPetIndex(int index) {
        return index >= 0 && index < pets.size();
    }

    // other //
    public double getWeeklyIncome() {
        double total = 0;
        for (Pet p : pets) {
            total += p.calculateWeeklyFee();
        }
        return total;
    }

    public double getAverageNumDaysPerWeek() {
        if (pets.isEmpty()) return 0;

        double total = 0;
        for (Pet p : pets) {
            total += p.getNumDaysPerWeek();
        }
        return total / pets.size();
    }

    public Pet findDogByOwnerAndBreedAndAge(String owner, String breed, int age) {
        for (Pet p : pets) {
            if (p instanceof Dog) {
                Dog d = (Dog) p;
                if (d.getOwner().getName().equalsIgnoreCase(owner)
                        && d.getBreed().equalsIgnoreCase(breed)
                        && d.getAge() == age) {
                    return d;
                }
            }
        }
        return null;
    }

    public String getPetsByOwnersName(String owner) {
        StringBuilder sb = new StringBuilder();
        for (Pet p : pets) {
            if (p.getOwner().getName().equalsIgnoreCase(owner)) {
                sb.append(p).append("\n");
            }
        }
        return sb.length() == 0 ? "No Pets for " + owner : sb.toString();
    }

    // sorting //
    public void sortPetsById() {
        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = i + 1; j < pets.size(); j++) {
                if (pets.get(i).getId() < pets.get(j).getId()) {
                    swapPets(i, j);
                }
            }
        }
    }

    public void sortPetsByName() {
        for (int i = 0; i < pets.size() - 1; i++) {
            for (int j = i + 1; j < pets.size(); j++) {
                if (pets.get(i).getName().compareToIgnoreCase(pets.get(j).getName()) > 0) {
                    swapPets(i, j);
                }
            }
        }
    }

    private void swapPets(int i, int j) {
        Pet temp = pets.get(i);
        pets.set(i, pets.get(j));
        pets.set(j, temp);
    }

    private void swapPets(Pet a, Pet b) {
        int indexA = pets.indexOf(a);
        int indexB = pets.indexOf(b);
        if (indexA != -1 && indexB != -1) {
            swapPets(indexA, indexB);
        }
    }

    // xml persistance - WORK IN PROGRESS //
    @Override
    public void save() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file));

            for (Pet p : pets) {

                if (p instanceof Dog) {
                    Dog d = (Dog) p;
                    pw.println("DOG|" +
                            d.getName() + "|" +
                            d.getAge() + "|" +
                            d.getOwner().getName() + "|" +
                            d.getOwner().getPhone() + "|" +
                            d.getId() + "|" +
                            d.getBreed() + "|" +
                            d.isDangerous() + "|" +
                            d.getNumDaysPerWeek());
                }

                else if (p instanceof Cat) {
                    Cat c = (Cat) p;
                    pw.println("CAT|" +
                            c.getName() + "|" +
                            c.getAge() + "|" +
                            c.getOwner().getName() + "|" +
                            c.getOwner().getPhone() + "|" +
                            c.getId() + "|" +
                            c.isIndoor() + "|" +
                            c.getNumDaysPerWeek());
                }

                else if (p instanceof Parrot) {
                    Parrot par = (Parrot) p;
                    pw.println("PARROT|" +
                            par.getName() + "|" +
                            par.getAge() + "|" +
                            par.getOwner().getName() + "|" +
                            par.getOwner().getPhone() + "|" +
                            par.getId() + "|" +
                            par.getWingSpan() + "|" +
                            par.isCanFly() + "|" +
                            par.getVocabularySize() + "|" +
                            par.getNumDaysPerWeek());
                }

                else if (p instanceof Bird) {
                    Bird b = (Bird) p;
                    pw.println("BIRD|" +
                            b.getName() + "|" +
                            b.getAge() + "|" +
                            b.getOwner().getName() + "|" +
                            b.getOwner().getPhone() + "|" +
                            b.getId() + "|" +
                            b.getWingSpan() + "|" +
                            b.isCanFly() + "|" +
                            b.getNumDaysPerWeek());
                }
            }

            pw.close();

        } catch (Exception e) {
            System.out.println("Error saving pets: " + e.getMessage());
        }
    }

    @Override
    public void load() {
        pets.clear();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                String type = parts[0];
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String ownerName = parts[3];
                String ownerPhone = parts[4];
                int id = Integer.parseInt(parts[5]);

                Owner owner = new Owner(ownerName, ownerPhone);

                switch (type) {

                    case "DOG":
                        String breed = parts[6];
                        boolean dangerous = Boolean.parseBoolean(parts[7]);
                        int daysDog = Integer.parseInt(parts[8]);
                        pets.add(new Dog(name, age, owner, id, breed, dangerous, daysDog));
                        break;

                    case "CAT":
                        boolean indoor = Boolean.parseBoolean(parts[6]);
                        int daysCat = Integer.parseInt(parts[7]);
                        pets.add(new Cat(name, age, owner, id, indoor, daysCat));
                        break;

                    case "PARROT":
                        double wingSpan = Double.parseDouble(parts[6]);
                        boolean canFly = Boolean.parseBoolean(parts[7]);
                        String vocab = parts[8];
                        int daysParrot = Integer.parseInt(parts[9]);

                        int vocabInt = BirdUtility.convertVocabularySizeReverse(vocab);

                        pets.add(new Parrot(name, age, owner, id, wingSpan, canFly, vocabInt));
                        break;

                    case "BIRD":
                        double ws = Double.parseDouble(parts[6]);
                        boolean cf = Boolean.parseBoolean(parts[7]);
                        int daysBird = Integer.parseInt(parts[8]);
                        pets.add(new Bird(name, age, owner, id, ws, cf));
                        break;
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error loading pets: " + e.getMessage());
        }
    }

    // name handling //
    public void initName(String name) {
        if (name.length() > 20) {
            this.name = name.substring(0, 20);
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        initName(name);
    }

    public ArrayList<Pet> getPetsArray() {
        return pets;
    }

    public void setPetsArray(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public int getMaxNumberOfPets() {
        return maxNumberOfPets;
    }

    public void setMaxNumberOfPets(int maxNumberOfPets) {
        this.maxNumberOfPets = maxNumberOfPets;
    }

    public String fileName() {
        return file.getName();
    }
}
