package models;

public class Parrot extends Bird {

    // field //
    private String vocabularySize;

    // constructor //
    public Parrot(String name, int age, Owner owner, int id,
                  double wingSpan, boolean canFly, int vocabularySize) {

        super(name, age, owner, id, wingSpan, canFly);
        setVocabularySize(vocabularySize);   // convert using BirdUtility
    }

    // getters and setters //
    public String getVocabularySize() {
        return vocabularySize;
    }

    public void setVocabularySize(int vocabularySize) {
        // Convert int → String using BirdUtility
        this.vocabularySize = BirdUtility.convertVocabularySize(vocabularySize);

        // If BirdUtility returns null or empty, use default
        if (this.vocabularySize == null || this.vocabularySize.isEmpty()) {
            this.vocabularySize = "Amazing";   // default value
        }
    }

    // abstract method implemenation //
    @Override
    public double calculateWeeklyFee() {
        // base rate = 10 per day → 70 per week
        double baseRate = 10 * 7;

        return baseRate;
    }
    // toString //
    @Override
    public String toString() {
        return super.toString() +
                ", vocabularySize=" + vocabularySize;
    }

    // equals //
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Parrot)) return false;

        Parrot other = (Parrot) obj;
        return this.vocabularySize.equals(other.vocabularySize);
    }
}
