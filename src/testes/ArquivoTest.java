package testes;

import arquivo.Arquivo;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ArquivoTest {
    @Test
    public void testValidarMatricula() {
        // Testa quando a matrícula existe no arquivo
        assertTrue(Arquivo.validarMatricula("202200059722"));
    }
}
