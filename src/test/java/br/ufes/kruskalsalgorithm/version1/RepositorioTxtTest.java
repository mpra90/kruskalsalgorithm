package br.ufes.kruskalsalgorithm.version1;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class RepositorioTxtTest {

    private static Logger LOGGER = Logger.getLogger(RepositorioTxtTest.class.getName());

    @Test
    void readFileTest() {
        try {
            Grafo g = RepositorioTxt.readFile();

            assertNotNull(g);
        } catch (IOException | URISyntaxException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
