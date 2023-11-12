package br.ufes.kruskalsalgorithm.version1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		final Grafo grafo;
		final Grafo agm;

		try {
			final long tempoIni = System.currentTimeMillis();

			grafo = RepositorioTxt.readFile();

			grafo.ordernarArestasPorPeso();

			agm = grafo.gerarAGM();

			final long tempoFim = System.currentTimeMillis();

			final long tempoResult = tempoFim - tempoIni;

			if (tempoResult >= 3600000L) {
				LOGGER.log(Level.INFO, "Tempo de execução: {0}h", (tempoResult / 3600000.00));
			} else if (tempoResult >= 60000L) {
				LOGGER.log(Level.INFO,"Tempo de execução: {0}m", (tempoResult / 60000.00));
			} else if (tempoResult >= 1000L) {
				LOGGER.log(Level.INFO,"Tempo de execução: {0}s", (tempoResult / 1000.00));
			} else {
				LOGGER.log(Level.INFO,"Tempo de execução: {0}ms", tempoResult);
			}

			LOGGER.info(agm::toString);
		} catch (IOException | URISyntaxException e) {
			LOGGER.log(Level.SEVERE, e, e::getMessage);
		}
    }
}
