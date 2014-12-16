package DominiPKG.AlgorismePKG;
import DominiPKG.GrafPKG.Aresta;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.GrafPKG.Vertex;


import java.util.*;
/**
 * Clase abstracta que representa l'algorisme de Max Flow
 * @author Federico Buldin
 */

public class MaxFlow {

    /**
     * Funció que crea el graf residual a partir del graf original
     * @param G Graf del qual volem obtenir el graf residual
     * @param idAlg Identificador d'Algorisme
     * @param R Taula on es guarden les rutes
     * @return Graf residual
     * @pre El paràmetre implícit haurà de ser un graf no nul amb un inici i un destí
     * @post El valor que retorna es el graf original convertit a residual
     */

    public Graf getResidual(Graf G, int idAlg, ArrayList<HashMap> R){
        Vertex sink = G.getFi();

        HashMap Path = new HashMap();
        int cap;
        Algorisme al;
        if(idAlg==1){
            al= new BFS();
        }
        else if(idAlg==2){
            al= new DFS();
        }
        else if(idAlg==3){
            al=new Dijkstra();
        }
        else return G;

        while ((cap = al.trobaCami(G, Path)) > 0){
            R.add(Path);
            int i = sink.getId();

            while ((int)Path.get(i) != -2){

                int v = (int)Path.get(i);
                Aresta a = G.getAresta(v, i);
                int capA = a.getCapacitat();
                a.setCapacitat(capA - cap);

                if (G.getAresta(i, v) == null){
                    Aresta a2 = new Aresta(0, cap, i, v);
                    G.afegirAresta(a2);}

                else{
                    Aresta a2 = G.getAresta(i, v);
                    int capA2 = a2.getCapacitat();
                    a2.setCapacitat(capA2 + cap);}

                i = v;
            }
            Path = new HashMap();
        }
        return G;
    }
}