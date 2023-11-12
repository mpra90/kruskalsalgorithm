package br.ufes.kruskalsalgorithm.version1;

/**
 * Classe responsável por representar a Aresta de um Grafo.
 *
 * @author Marcos Paulo Rodrigues Alves
 * @version 0.2
 */

public class Aresta {

    private final int vertice1;

    private final int vertice2;

    private final int peso;

    /**
     * Constroi a aresta com os vértices que a pertecem, e com o seu peso.
     *
     * @param vertice1 vértice inicial da aresta
     * @param vertice2 vértice final da aresta
     * @param peso     peso da aresta
     */
    public Aresta(int vertice1, int vertice2, int peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    /**
     * Obtem o vértice inical da aresta.
     *
     * @return o vértice inicial ligado a aresta.
     */
    public int getVertice1() {
        return this.vertice1;
    }

    /**
     * Obtem o vértice final da aresta.
     *
     * @return o vértice final ligado a aresta.
     */
    public int getVertice2() {
        return this.vertice2;
    }

    /**
     * Obtem o peso da aresta.
     *
     * @return o peso da aresta.
     */
    public int getPeso() {
        return this.peso;
    }
}
