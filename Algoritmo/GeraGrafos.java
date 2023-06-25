package Algoritmo;

import java.util.Random;

public class GeraGrafos {

    private long tamS, tamD;
    private long maxConexoesSensores;
    private long maxConexoesPD;
    private long maxConexoesSink;
    private double maxPeso;
    

    public GeraGrafos() {
        maxConexoesSink = 3;
        maxPeso = 100;
    }

    public Grafo gerar(long tamS, long tamD) {
        this.tamS = tamS;
        this.tamD = tamD;
        maxConexoesSensores = tamS - 1;
        maxConexoesPD = Math.max(1, tamS / 5);
        Grafo grafo = new Grafo(tamS, tamD);
        geraConexoesSensores(grafo);
        geraConexoesPD(grafo);        
        geraConexoesM(grafo);

        return grafo;

    }

    private void geraConexoesSensores(Grafo grafo) {
        Random random = new Random();
        for (long i = 1; i <= tamS; i++) {
            long nConexoes = Math.max(1, random.nextLong(maxConexoesSensores));
            for (long x = 0; x < nConexoes; x++) {
                long j = random.nextLong(tamS) + 1;
                if(i==j || grafo.containsAresta(i,j)) continue;
                Vertice v1 = grafo.getSensor(i);
                Vertice v2 = grafo.getSensor(j);
                double peso = random.nextDouble(maxPeso);
                grafo.addAresta(new Aresta(v1, v2, peso));
                grafo.addAresta(new Aresta(v2, v1, peso));
            }
        }
    }

    private void geraConexoesPD(Grafo grafo){
        Random random = new Random();
        for (long i = 1+tamS; i <= tamD+tamS; i++) {
            long nConexoes = Math.max(1, random.nextLong(maxConexoesPD));
            for (long x = 0; x < nConexoes; x++) {
                long j = random.nextLong(tamS) + 1;
                Vertice v1 = grafo.getPD(i);
                Vertice v2 = grafo.getSensor(j);
                double peso = random.nextDouble(maxPeso/2);
                grafo.addAresta(new Aresta(v2, v1, peso));
            }
        }
    }   

    private void geraConexoesM(Grafo grafo) {
        Random random = new Random();
        long nConexoes = Math.max(1, random.nextLong(maxConexoesSink));
        for (long x = 0; x < nConexoes; x++) {
            long j = random.nextLong(tamS) + 1;
            Vertice v2 = grafo.getSensor(j);
            double peso = random.nextDouble(maxPeso);
            grafo.addAresta(new Aresta(grafo.sink, v2, peso));
        }
    }

    
}



/*
 * 
 * Graph GRAPHrand( int V, int A) {
 * Graph G = GRAPHinit( V);
 * while (G->A < A) {
 * vertex v = randV( G);
 * vertex w = randV( G);
 * if (v != w)
 * GRAPHinsertArc( G, v, w);
 * }
 * return G;
 * }
 * vertex randV( Graph G) {
 * double r;
 * r = rand( ) / (RAND_MAX + 1.0);
 * return r * G->V;
 * }
 */