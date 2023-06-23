package OTM1.Algoritmo;

import java.util.Vector;
import java.util.HashMap;

public class Grafo {
    
    // O nó sorvedouro deve possuir id = 0

    public HashMap<Long, Vertice> vertices;    
    public Vector<Aresta> arestas;
    public HashMap<Long, Vector<Aresta>> listaDeAdjacencia;

    public Grafo() {
        listaDeAdjacencia = new HashMap<Long, Vector<Aresta>>();
        vertices = new HashMap<Long, Vertice>();
        arestas =  new Vector<Aresta>();
    }
    
    public void addAresta(Aresta aresta){
        vertices.putIfAbsent(aresta.v1.id,aresta.v1);
        vertices.putIfAbsent(aresta.v2.id,aresta.v2);

        arestas.add(aresta);

        //Adiciona a aresta na lista de adjacência de v1
        if(!listaDeAdjacencia.containsKey(aresta.v1.id)){
            listaDeAdjacencia.put(aresta.v1.id, new Vector<>());
        }
        listaDeAdjacencia.get(aresta.v1.id).add(aresta);
    }

    public Vertice getM(){
        return vertices.get(0); 
    }

    public Vector<Vertice> getS(){
        Vector<Vertice> s = new Vector<Vertice>();
        for(Vertice v: vertices.values()) {
            if(v.tipo == TipoVertice.SENSOR)
                s.add(v);    
        }
        return s;
    }

    public Vector<Vertice> getD(){
        Vector<Vertice> d = new Vector<Vertice>();
        for(Vertice v: vertices.values()) {
            if(v.tipo == TipoVertice.PONTO_DE_DEMANDA)
                d.add(v);    
        }
        return d;
    }

    public Vector<Aresta> getAS(){
        Vector<Aresta> as = new Vector<Aresta>();
        for(Aresta a: arestas) {
            if(a.v1.tipo == TipoVertice.SENSOR && a.v2.tipo == TipoVertice.SENSOR)
                as.add(a);    
        }
        return as;
    }

    public Vector<Aresta> getAD(){
        Vector<Aresta> ad = new Vector<Aresta>();
        for(Aresta a: arestas) {
            if( a.v1.tipo == TipoVertice.SENSOR && a.v2.tipo == TipoVertice.PONTO_DE_DEMANDA) {
                    ad.add(a);    
            }
        }
        return ad;
    }

    public Aresta getAm(){
        for(Aresta a: arestas) {
            if(a.v1.tipo == TipoVertice.SINK && a.v2.tipo == TipoVertice.SENSOR) {
                    return a;
                }
        }
        return null;
    }

    public  Vector<Aresta> getInD(long idPontoDeDemanda) {
        Vector<Aresta> aD = getAD();
        Vector<Aresta> inD = new Vector<Aresta>();
        for(Aresta a: aD){
            if(a.v2.id == idPontoDeDemanda)
                inD.add(a);
        }
        return inD;
    }

    public  Vector<Aresta> getOutD(long idPontoDeDemanda) {
        Vector<Aresta> aD = getAD();
        Vector<Aresta> inD = new Vector<Aresta>();
        for(Aresta a: aD){
            if(a.v1.id == idPontoDeDemanda)
                inD.add(a);
        }
        return inD;
    }

    public  Vector<Aresta> getInS(long idSensor) {
        Vector<Aresta> aS = getAS();
        Vector<Aresta> inS = new Vector<Aresta>();
        for(Aresta a: aS){
            if(a.v2.id == idSensor)
                inS.add(a);
        }
        return inS;
    }

    public Vector<Aresta> getArestas() {
        return arestas;
    }
}