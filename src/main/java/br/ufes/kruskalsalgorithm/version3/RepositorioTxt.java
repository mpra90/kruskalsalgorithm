package br.ufes.kruskalsalgorithm.version3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe utilitária responsável por ler os dados do arquivo externo.
 *
 * <p>
 * O arquivo externo irá conter as informações do Grafo a ser lido pelo sistema no formato: vértice, vértice, peso.
 *
 * @author Marcos Paulo Rodrigues Alves
 * @version 0.4
 */

public final class RepositorioTxt {

    private static final String FILE_NAME = "n50_m306_25.txt";

    private static final URL resource = RepositorioTxt.class.getClassLoader().getResource(FILE_NAME);

    private RepositorioTxt() {
        throw new IllegalStateException("Classe utilitária");
    }

    /**
     * Ler os dados do arquivo na estrutura de Buckets e retorna objeto Grafo com suas respectivas arestas e peso.
     *
     * @return Grafo
     * @throws IOException Caso ocorra algum problema na leitura do arquivo
     * @throws URISyntaxException Caso não encontre o arquivo no classpath
     */
    public static Grafo readFile() throws IOException, URISyntaxException {
        int maiorPeso = 0;
        int totalVertice = 0;
        int totalAresta = 0;
        int lineNumber = 0;
        String linha = null;
        String[] lineArray;
        List<Aresta> listAresta = new ArrayList<>();

        try (final BufferedReader buff = Files.newBufferedReader(Paths.get(resource.toURI()))) {
            while ((linha = buff.readLine()) != null) {
                if (lineNumber == 0) {
                    totalVertice = Integer.parseInt(linha);
                } else if (lineNumber == 1) {
                    totalAresta = Integer.parseInt(linha);
                } else {
                    lineArray = linha.split(",");
                    int vertice1 = Integer.parseInt(lineArray[0]);
                    int vertice2 = Integer.parseInt(lineArray[1]);
                    int peso = Integer.parseInt(lineArray[2]);
                    final Aresta aresta = new Aresta(vertice1, vertice2, peso);
                    listAresta.add(aresta);
                    if (peso > maiorPeso) {
                        maiorPeso = peso;
                    }
                }
                lineNumber++;
            }
        }

        Grafo g = new Grafo(maiorPeso);
        g.setTotalVertice(totalVertice);
        g.setTotalAresta(totalAresta);

        for (Aresta a : listAresta) {
            g.addAresta(a, a.getPeso());
        }

        return g;
    }
}
