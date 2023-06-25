package Algoritmo;

import java.util.HashSet;

public class Teste {
    private static double soma;
    private static boolean[] visitados;
    public static void main(String[] args)
    {
        GeraGrafos geraGrafos = new GeraGrafos();
        Grafo grafo = geraGrafos.gerar(6, 3);
        GeraArquivoGrafo geraArquivo = new GeraArquivoGrafo();
        geraArquivo.adicionar(grafo);
        geraArquivo.gerar("grafoTeste");

        visitados = new boolean[10];

        HashSet<Long> idsPontosDeDemanda = new HashSet<Long>();
        for(long i = 7; i <= 9; i++){
            idsPontosDeDemanda.add(i);
        }
        soma = 0;
        HashSet<Aresta> am = grafo.getAm();

        for(Aresta arestaSink: am){
            System.out.println(arestaSink.v1.id + " " + arestaSink.v2.id + ": " + arestaSink.peso);
            if(idsPontosDeDemanda.isEmpty()) break;        
            long caminhos = buscaEmProfundidade(arestaSink.v2, idsPontosDeDemanda, grafo);
            soma+=caminhos*arestaSink.peso;
        }    
        System.out.println("Soma:" + soma);
        System.out.println("Visitados: ");
        for(var i: visitados) System.out.print(i + " ");
    }
    
    public static long buscaEmProfundidade(Vertice v, HashSet<Long> idsPontosDeDemanda, Grafo grafo) {
        if(visitados[(int) v.id]) return 0;
        visitados[(int) v.id] = true;

            long caminhos = 0;
        for(Aresta a: grafo.listaDeAdjacencia.get(v.id)){
            if(idsPontosDeDemanda.isEmpty()) break;        
            
            if(a.v2.tipo ==TipoVertice.PONTO_DE_DEMANDA){
                visitados[(int) a.v2.id] = true;
                idsPontosDeDemanda.remove(a.v2.id);
                soma+=a.peso;
                caminhos++;
            } else {
                long caminhosV2 = buscaEmProfundidade(a.v2, idsPontosDeDemanda, grafo);
                caminhos+=caminhosV2;
                soma+=(caminhosV2*a.peso);
            } 
        }
        return caminhos;
    }
}
