package DominiPKG.AlgorismePKG;
import DominiPKG.GrafPKG.Aresta;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.GrafPKG.Vertex;

import java.util.*;
/**
 *
 * @author Roger Cendrós
 */

public class DFS implements Algorisme {

    //pre: vector valid i el seu tamany > 0
    //post: retorna un vector inicialitzat amb totes les posicions a -1
    private void inicialitzar(HashMap Path, int tam){
        for(int i = 0; i < tam; i++){
            Path.put(i,-1);
        }
    }

    //pre: graf G residual vàlid
    //post: retorna la capacitat del cami i Path[] la id dels vertex que formenen el cami
    public int trobaCami(Graf G, HashMap Path)
    {
        Vertex vertex_inici = G.getInici();
        Vertex vertex_final = G.getFi();
        Stack<Vertex> s = new Stack();
        int numV = G.getDimensio();
        inicialitzar(Path, numV);

        Path.put(vertex_inici.getId(), -2);
        int capacitat = Integer.MAX_VALUE;

        s.push(vertex_inici);
        while (!s.empty()){
            Vertex top = s.peek();
            s.pop();
            for (int aID : G.getAdjacentsDestins(top.getId()))
            {
                Aresta a = G.getAresta(aID);
                int v = a.getId_vertex_adjunt();
                if (a.getCapacitat() > 0 && Path.get(v).equals(-1))
                    Path.put(v,top.getId());
                int cap = a.getCapacitat();
                capacitat = Math.min(cap, capacitat);
                if (v != vertex_final.getId()) s.push(G.getVertex(v));
                else return capacitat;
            }
        }
        return 0;
    }
}