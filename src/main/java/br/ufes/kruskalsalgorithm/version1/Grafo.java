package br.ufes.kruskalsalgorithm.version1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Classe responsável por representar o Grafo.
 *
 * @author Marcos Paulo Rodrigues Alves
 * @version 0.3
 */

public class Grafo {

    private int totalVertice;
    private int totalAresta;
    private final List<Aresta> arestas;

    /**
     * Constroi um Grafo com uma lista de arestas vazia com capacidade inicial de 10 elementos.
     */
    public Grafo() {
        this.totalVertice = 0;
        this.totalAresta = 0;
        this.arestas = new ArrayList<>();
    }

    /**
     * Adiciona o elemento no final da lista. Método usado para popular as arestas do Grafo cíclico.
     *
     * @param aresta o elemento a ser adicionado na lista
     */
    public void addAresta(Aresta aresta) {
        this.arestas.add(aresta);
    }

    /**
     * Adiciona o elemento no final da lista. Método usado para popular as arestas da Floresta.
     *
     * @param aresta o elemento a ser adicionado na lista
     */
    public void addAresta2(Aresta aresta) {
        this.arestas.add(aresta);
        incrementarAresta();
    }

    private void incrementarAresta() {
        this.totalAresta++;
    }

    public Aresta getAresta(int index) {
        return this.arestas.get(index);
    }

    public void setTotalVertice(int totalVertice) {
        this.totalVertice = totalVertice;
    }

    public void setTotalAresta(int totalAresta) {
        this.totalAresta = totalAresta;
    }

    public void ordernarArestasPorPeso() {
        this.arestas.sort(Comparator.comparingInt(Aresta::getPeso));
    }

    public int somarPesoTotal() {
        int pesoTotal = 0;
        for (Aresta aresta : this.arestas) {
            pesoTotal += aresta.getPeso();
        }
        return pesoTotal;
    }

    /**
     * Método responsável por gerar a Árvore Geradora Mínima (AGM) usando uma estrutura de dados ineficiente.
     *
     * @return o objeto AGM
     */
    public Grafo gerarAGM() {
        final Grafo agm = new Grafo();
        Aresta aresta;
        List<Integer> chefes = new ArrayList<>();

        popularChefes(chefes);

        int index = 0;
        while (index < this.totalAresta) {
            aresta = getAresta(index);
            if (naoFormaCiclo(aresta, chefes)) {
                agm.addAresta2(aresta);
            }
            index++;
        }
        agm.setTotalVertice(this.totalVertice);
        return agm;
    }

    private void popularChefes(List<Integer> chefes) {
        for (int v = 0; v < this.totalVertice; v++) {
            chefes.add(v);
        }
    }

    /**
     * Caso não forme ciclo, atualiza o vetor de chefes. O chefe do vértice de Origem (chefeOrigem) é o chefe dominante.
     *
     * @param arestaCandidata o objeto a ser verificado
     * @param arestasAgm
     * @return {@code true} se o objeto irá formar ciclo, caso contrário {@code false}
     */
    private boolean naoFormaCiclo(Aresta arestaCandidata, List<Integer> chefes) {
        int vOrigem = arestaCandidata.getVertice1();
        int vDestino = arestaCandidata.getVertice2();

        if (Objects.equals(chefes.get(vOrigem), chefes.get(vDestino))) {
            return false;
        }

        int chefeOrigem = chefes.get(vOrigem);
        int chefeDestino = chefes.get(vDestino);
        for (int v = 0; v < this.totalVertice; v++) {
            if (Objects.equals(chefes.get(v), chefeDestino)) {
                chefes.set(v, chefeOrigem);
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("Grafo:\n...Total de arestas = ")//
                .append(this.totalAresta)//
                .append("\n...Total de vértices = ")//
                .append(this.totalVertice)//
                .append("\n...Soma total dos pesos = ")//
                .append(somarPesoTotal())//
                .append("\n...Arestas = [");

        int i = 0;
        for (Aresta a : this.arestas) {
            string.append("(")//
                    .append(a.getVertice1())//
                    .append(", ")//
                    .append(a.getVertice2())//
                    .append(")");

            if (i < this.totalAresta - 1) {
                string.append(", ");
                i++;
            }
        }

        string.append("]");
        return string.toString();
    }
}
