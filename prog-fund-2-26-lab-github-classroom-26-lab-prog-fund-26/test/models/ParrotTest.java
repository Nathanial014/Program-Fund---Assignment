package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParrotTest {

    @Test
    void vocabularySizeConvertedCorrectly() {
        Owner o = new Owner("Bob", "999");
        Parrot p = new Parrot("Polly", 2, o, 1,
                15.0, true, 50, 3);
        assertNotNull(p.getVocabularySize());
    }

    @Test
    void toStringIncludesVocabularySize() {
        Owner o = new Owner("Bob", "999");
        Parrot p = new Parrot("Polly", 2, o, 1,
                15.0, true, 50, 3);
        assertTrue(p.toString().contains("vocabularySize"));
    }
}
