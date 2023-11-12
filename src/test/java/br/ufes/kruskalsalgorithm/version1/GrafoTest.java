package br.ufes.kruskalsalgorithm.version1;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class GrafoTest {

    private static Logger LOGGER = Logger.getLogger(GrafoTest.class.getName());

    @Test
    void createGrafoTest() {
        try {
            Grafo g = RepositorioTxt.readFile();

            assertNotNull(g);
        } catch (IOException | URISyntaxException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Test
    void gerarAGMTest() {
        try {
            Grafo g = RepositorioTxt.readFile();

            assertNotNull(g);

            g.ordernarArestasPorPeso();

            Grafo agm = g.gerarAGM();

            assertNotNull(agm);
        } catch (IOException | URISyntaxException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
