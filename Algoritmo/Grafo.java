package OTM1.Algoritmo;

import java.util.Vector;
import java.util.HashMap;
import java.util.HashSet;

public class Grafo {
    
    // O nó sorvedouro deve possuir id = 0

    public HashSet<Vertice> sensores;
    public HashSet<Vertice> pontosDeDemanda;
    public Vertice sink;    
    public HashSet<Aresta> arestas;
    public HashMap<Long, HashSet<Aresta>> listaDeAdjacencia;

    public Grafo() {
        listaDeAdjacencia = new HashMap<Long, HashSet<Aresta>>();
        sensores = new HashSet<Vertice>();
        pontosDeDemanda = new HashSet<Vertice>();
        sink = new Vertice(TipoVertice.SINK, 0);
        arestas =  new HashSet<Aresta>();
    }

    public void addAresta(Aresta aresta){
        
        if(aresta.v1.tipo == TipoVertice.SENSOR)
            sensores.add(aresta.v1);
        if(aresta.v2.tipo == TipoVertice.SENSOR)
            sensores.add(aresta.v2);
        else if(aresta.v2.tipo == TipoVertice.PONTO_DE_DEMANDA)
            pontosDeDemanda.add(aresta.v1);
        
        arestas.add(aresta);

        //Adiciona a aresta na lista de adjacência de v1
        if(!listaDeAdjacencia.containsKey(aresta.v1.id)){
            listaDeAdjacencia.put(aresta.v1.id, new HashSet<Aresta>());
        }
        listaDeAdjacencia.get(aresta.v1.id).add(aresta);
    }

    public Vertice getSink(){
        return sink; 
    }

    public HashSet<Vertice> getS(){
        return sensores;
    }

    public HashSet<Vertice> getD(){
        return pontosDeDemanda;
    }

    public HashSet<Aresta> getAS(){
        HashSet<Aresta> as = new HashSet<Aresta>();
        for(Aresta a: arestas) {
            if(a.v1.tipo == TipoVertice.SENSOR && a.v2.tipo == TipoVertice.SENSOR)
                as.add(a);    
        }
        return as;
    }

    public HashSet<Aresta> getAD(){
        HashSet<Aresta> ad = new HashSet<Aresta>();
        for(Aresta a: arestas) {
            if( a.v1.tipo == TipoVertice.SENSOR && a.v2.tipo == TipoVertice.PONTO_DE_DEMANDA) {
                    ad.add(a);    
            }
        }
        return ad;
    }

    public HashSet<Aresta> getAm(){
         HashSet<Aresta> am = new HashSet<Aresta>();
        for(Aresta a: arestas) {
            if( a.v1.tipo == TipoVertice.SINK && a.v2.tipo == TipoVertice.SENSOR) {
                    am.add(a);    
            }
        }
        return am;
    }

    public  HashSet<Aresta> getInD(long idPontoDeDemanda) {
        HashSet<Aresta> aD = getAD();
        HashSet<Aresta> inD = new HashSet<Aresta>();
        for(Aresta a: aD){
            if(a.v2.id == idPontoDeDemanda)
                inD.add(a);
        }
        return inD;
    }

    public  HashSet<Aresta> getOutD(long idPontoDeDemanda) {
        HashSet<Aresta> aD = getAD();
        HashSet<Aresta> inD = new HashSet<Aresta>();
        for(Aresta a: aD){
            if(a.v1.id == idPontoDeDemanda)
                inD.add(a);
        }
        return inD;
    }

    public  HashSet<Aresta> getInS(long idSensor) {
        HashSet<Aresta> aS = getAS();
        HashSet<Aresta> inS = new HashSet<Aresta>();
        for(Aresta a: aS){
            if(a.v2.id == idSensor)
                inS.add(a);
        }
        return inS;
    }

    public HashSet<Aresta> getArestas() {
        return arestas;
    }
}