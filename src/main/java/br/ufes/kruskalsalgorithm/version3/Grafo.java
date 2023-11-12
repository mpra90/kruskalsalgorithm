package br.ufes.kruskalsalgorithm.version3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Classe responsável por representar o Grafo.
 *
 * @author Marcos Paulo Rodrigues Alves
 * @version 0.4
 */

public class Grafo {

    private int totalVertice;
    private int totalAresta;
    private final List<Stack<Aresta>> arestas;

    /**
     * Constroi um Grafo com uma lista de arestas vazia com capacidade inicial de 10 elementos.
     */
    public Grafo() {
        this.totalVertice = 0;
        this.totalAresta = 0;
        this.arestas = new ArrayList<>();
    }

    /**
     * Constroi um Grafo com uma lista de arestas com capacidade de elementos já definida.
     *
     * @param capacidade Capacidade da lista de arestas
     */
    public Grafo(int capacidade) {
        this.totalVertice = 0;
        this.totalAresta = 0;
        this.arestas = new ArrayList<>(capacidade);

        for (int i = 0; i < capacidade; i++) {
            this.arestas.add(new Stack<>());
        }
    }

    /**
     * Adiciona o elemento no index - 1 referente ao peso. Ou seja, se o peso da Aresta for 3,
     * será adicionado a Aresta na posição 2 da lista.
     *
     * @param aresta o elemento a ser adicionado na lista.
     * @param index  o índice da lista a ser adicionado o elemento.
     */
    public void addAresta(Aresta aresta, int index) {
        this.arestas.get(index - 1).push(aresta);
    }

    /**
     * Adiciona a aresta na última posição na lista.
     *
     * @param aresta o elemento a ser adicionado na lista
     */
    public void addAresta(Aresta aresta) {
        final Stack<Aresta> pilha = new Stack<>();
        pilha.push(aresta);
        this.arestas.add(pilha);
        incrementarTotalAresta();
    }

    public void setTotalVertice(int totalVertice) {
        this.totalVertice = totalVertice;
    }

    public void setTotalAresta(int totalAresta) {
        this.totalAresta = totalAresta;
    }

    private void incrementarTotalAresta() {
        this.totalAresta++;
    }

    public int somarPesoTotal() {
        int pesoTotal = 0;
        for (Stack<Aresta> pilha : this.arestas) {
            for (Aresta aresta : pilha) {
                pesoTotal += aresta.getPeso();
            }
        }
        return pesoTotal;
    }

    /**
     * Método responsável por gerar a Árvore Geradora Mínima (AGM) usando a estrutura de dados Union-Find.
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

        for (Stack<Aresta> pilha : this.arestas) {
            while (!pilha.isEmpty()) {
                aresta = pilha.pop();
                final int uc = find(aresta.getVertice1(), chefes);
                final int vc = find(aresta.getVertice2(), chefes);

                if (uc != vc) {
                    agm.addAresta(aresta);
                    union(uc, vc, chefes, tamanhos);
                }
            }
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
        for (Stack<Aresta> pilhas : this.arestas) {
            for (Aresta a : pilhas) {
                string.append("(")//
                        .append(a.getVertice1())//
                        .append(", ")//
                        .append(a.getVertice2())//
                        .append(")");

                if (i < this.arestas.size() - 1) {
                    string.append(", ");
                }
            }
            i++;
        }
        string.append("]");
        return string.toString();
    }
}
