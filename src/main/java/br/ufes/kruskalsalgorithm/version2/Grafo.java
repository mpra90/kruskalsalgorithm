package br.ufes.kruskalsalgorithm.version2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
     * Adiciona o elemento no final da lista. Método usado para popular as arestas da Floresta (Árvore geradora mínima).
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
     * Método responsável por gerar uma Árvore Geradora Mínima (AGM) usando a estrutura de dados Union-Find.
     *
     * @return o objeto AGM
     */
    public Grafo gerarAGM() {
        final Grafo agm = new Grafo();
        final List<Integer> chefes = new ArrayList<>();
        final List<Integer> tamanhos = new ArrayList<>();
        Aresta aresta;

        popularChefes(chefes);
        popularTamanhos(tamanhos);

        int index = 0;
        while (index < this.totalAresta) {
            aresta = getAresta(index);
            final int uc = find(aresta.getVertice1(), chefes);
            final int vc = find(aresta.getVertice2(), chefes);

            if (uc != vc) {
                agm.addAresta2(aresta);
                union(uc, vc, chefes, tamanhos);
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

    private void popularTamanhos(List<Integer> tamanhos) {
        for (int v = 0; v < this.totalVertice; v++) {
            tamanhos.add(1);
        }
    }

    private int find(int vertice, List<Integer> chefes) {
        int vAux = vertice;
        while (vAux != chefes.get(vAux)) {
            vAux = chefes.get(vAux);
        }
        return vAux;
    }

    private void union(int uc, int vc, List<Integer> chefes, List<Integer> tamanho) {
        if (tamanho.get(uc) > tamanho.get(vc)) {
            atualizarChefe(vc, uc, chefes);
            atualizarTamanho(uc, vc, tamanho);
        } else {
            atualizarChefe(uc, vc, chefes);
            atualizarTamanho(vc, uc, tamanho);
        }
    }

    private void atualizarTamanho(int chefe1, int chefe2, List<Integer> tamanho) {
        tamanho.set(chefe1, tamanho.get(chefe1) + tamanho.get(chefe2));
    }

    private void atualizarChefe(int chefe1, int chefe2, List<Integer> chefes) {
        chefes.set(chefe1, chefe2);
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
