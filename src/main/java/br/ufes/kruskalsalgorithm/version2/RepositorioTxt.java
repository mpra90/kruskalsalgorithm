package br.ufes.kruskalsalgorithm.version2;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe utilitária responsável por ler os dados do arquivo externo.
 *
 * <p>
 * O arquivo externo irá conter as informações do Grafo a ser lido pelo sistema no formato: vértice, vértice, peso.
 *
 * @author Marcos Paulo Rodrigues Alves
 * @version 0.3
 */

public final class RepositorioTxt {

    private static final String FILE_NAME = "n1000_m374625_75.txt";

    private static final URL resource = RepositorioTxt.class.getClassLoader().getResource(FILE_NAME);

    private RepositorioTxt() {
        throw new IllegalStateException("Classe utilitária");
    }

    /**
     * Ler os dados do arquivo e retorna objeto Grafo com suas respectivas arestas e peso.
     *
     * @return Grafo
     * @throws IOException Caso ocorra algum problema na leitura do arquivo
     * @throws URISyntaxException Caso não encontre o arquivo no classpath
     */
    public static Grafo readFile() throws IOException, URISyntaxException {
        final Grafo g = new Grafo();

        String line;
        String[] lineArray;
        int totalVertice;
        int totalAresta;
        int vertice1;
        int vertice2;
        int peso;
        int aux = 0;
        try (final BufferedReader buff = Files.newBufferedReader(Paths.get(resource.toURI()))) {
            while ((line = buff.readLine()) != null) {
                if (aux == 0) {
                    totalVertice = Integer.parseInt(line);
                    g.setTotalVertice(totalVertice);
                } else if (aux == 1) {
                    totalAresta = Integer.parseInt(line);
                    g.setTotalAresta(totalAresta);
                } else {
                    lineArray = line.split(",");
                    vertice1 = Integer.parseInt(lineArray[0]);
                    vertice2 = Integer.parseInt(lineArray[1]);
                    peso = Integer.parseInt(lineArray[2]);
                    final Aresta aresta = new Aresta(vertice1, vertice2, peso);
                    g.addAresta(aresta);
                }
                aux++;
            }
        }
        return g;
    }
}
