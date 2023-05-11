package testes;

import criptografia.HashGenerator;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HashGeneratorTest {

    @Test
    void testGenerateHash() throws NoSuchAlgorithmException {
        String text = "202200059722";
        String expectedHash = "f8dcaac072d82e1265959c68fbb47ca11c33ad9f463bba3e76ff8dd61d12c96d";
        String generatedHash = HashGenerator.generateHash(text);
        assertEquals(expectedHash, generatedHash);
    }
}

