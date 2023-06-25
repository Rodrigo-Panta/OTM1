package Algoritmo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
 
public class Teste
{
    public static void main(String[] args)
    {
        String path = "./json/grafo.json";
 
        JSONObject json = new JSONObject();
        try {
            GeraGrafos geraGrafos = new GeraGrafos();
            Grafo grafo = geraGrafos.gerar(6, 3);
            System.out.println("Arestas: " + grafo.arestas.size());
            json.put("grafo", grafoToJson(grafo));            
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString().replace("\\", "").replace("\"{", "{").replace("}\"", "}"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String verticeToJson (Vertice vertice) {
        JSONObject json = new JSONObject();
        try {
            json.put("id", vertice.id);
            json.put("tipo", vertice.tipo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    
    public static String arestaToJson (Aresta aresta) {
        JSONObject json = new JSONObject();
        // System.out.println(aresta.v1.id);
        // System.out.println(aresta.v2.id);

        try {
            json.put("v1",aresta.v1.id);
            json.put("v2", aresta.v2.id);
            json.put("peso", String.format("%.2f", aresta.peso));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    
    public static String grafoToJson (Grafo grafo) {
        JSONObject json = new JSONObject();
        try {
            for(Vertice v : grafo.sensores.values()) {
                json.append("sensores", verticeToJson(v));
            }
            for(Vertice v : grafo.pontosDeDemanda.values()) {
                json.append("pontosDeDemanda", verticeToJson(v));
            }
            json.append("sink", verticeToJson(grafo.sink));
            for(Aresta a : grafo.arestas) {
                json.append("arestas", arestaToJson(a));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    
    
}