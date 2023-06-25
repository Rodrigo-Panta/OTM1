package Algoritmo;

import java.util.HashSet;
import java.util.List;
import java.util.HashMap;

public class Grafo {

    // O nó sorvedouro deve possuir id = 0

    public HashMap<Long, Vertice> sensores;
    public HashMap<Long, Vertice> pontosDeDemanda;
    public Vertice sink;
    public HashSet<Aresta> arestas;
    public HashMap<Long, HashSet<Aresta>> listaDeAdjacencia;

    public Grafo(long nSensores, long nPD) {
        listaDeAdjacencia = new HashMap<Long, HashSet<Aresta>>();
        arestas = new HashSet<Aresta>();

        sink = new Vertice(TipoVertice.SINK, 0);

        sensores = new HashMap<Long, Vertice>();
        for (long i = 1; i <= nSensores; i++) {
            sensores.put(i, new Vertice(TipoVertice.SENSOR, i));
        }

        pontosDeDemanda = new HashMap<Long, Vertice>();
        for (long i = 1; i <= nPD; i++) {
            pontosDeDemanda.put(i + nSensores, new Vertice(TipoVertice.PONTO_DE_DEMANDA, i + nSensores));
        }
    }

    public void addAresta(Aresta aresta) {
        arestas.add(aresta);

        // Adiciona a aresta na lista de adjacência de v1
        if (!listaDeAdjacencia.containsKey(aresta.v1.id)) {
            listaDeAdjacencia.put(aresta.v1.id, new HashSet<Aresta>());
        }
        listaDeAdjacencia.get(aresta.v1.id).add(aresta);
    }

    public Vertice getSensor(long id) {
        return sensores.get(id);
    }

    public Vertice getPD(long id) {
        return pontosDeDemanda.get(id);
    }

    public Vertice getSink() {
        return sink;
    }

    public Vertice[] getS() {
        return (Vertice[]) sensores.values().toArray();
    }

    public Vertice[] getD() {
        return (Vertice[]) pontosDeDemanda.values().toArray();
    }

    public HashSet<Aresta> getAS() {
        HashSet<Aresta> as = new HashSet<Aresta>();
        for (Aresta a : arestas) {
            if (a.v1.tipo == TipoVertice.SENSOR && a.v2.tipo == TipoVertice.SENSOR)
                as.add(a);
        }
        return as;
    }

    public HashSet<Aresta> getAD() {
        HashSet<Aresta> ad = new HashSet<Aresta>();
        for (Aresta a : arestas) {
            if (a.v1.tipo == TipoVertice.SENSOR && a.v2.tipo == TipoVertice.PONTO_DE_DEMANDA) {
                ad.add(a);
            }
        }
        return ad;
    }

    public HashSet<Aresta> getAm() {
        HashSet<Aresta> am = new HashSet<Aresta>();
        for (Aresta a : arestas) {
            if (a.v1.tipo == TipoVertice.SINK && a.v2.tipo == TipoVertice.SENSOR) {
                am.add(a);
            }
        }
        return am;
    }

    public HashSet<Aresta> getInD(long idPontoDeDemanda) {
        HashSet<Aresta> aD = getAD();
        HashSet<Aresta> inD = new HashSet<Aresta>();
        for (Aresta a : aD) {
            if (a.v2.id == idPontoDeDemanda)
                inD.add(a);
        }
        return inD;
    }

    public HashSet<Aresta> getInS(long idSensor) {
        HashSet<Aresta> aS = getAS();
        HashSet<Aresta> inS = new HashSet<Aresta>();
        for (Aresta a : aS) {
            if (a.v2.id == idSensor)
                inS.add(a);
        }
        return inS;
    }

    public HashSet<Aresta> getOutS(long idSensor) {
        HashSet<Aresta> aS = getAS();
        HashSet<Aresta> inS = new HashSet<Aresta>();
        for (Aresta a : aS) {
            if (a.v1.id == idSensor)
                inS.add(a);
        }
        return inS;
    }

    public HashSet<Aresta> getArestas() {
        return arestas;
    }

    public boolean containsAresta(long idV1, long idV2) {
        HashSet<Aresta> adjV1 = listaDeAdjacencia.get(idV1);
        if (adjV1 != null && adjV1.size() > 0)
            for (Aresta a : adjV1) {
                if (a.v2.id == idV2)
                    return true;
            }
        return false;
    }

}