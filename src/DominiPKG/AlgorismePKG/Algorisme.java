package DominiPKG.AlgorismePKG;
import DominiPKG.GrafPKG.Aresta;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.GrafPKG.Vertex;

import java.util.*;
/**
 * Clase abstracta que representa l'algorisme de Max Flow
 * @author Federico Buldin
 */

public abstract class Algorisme {

    /**
     * Funció que troba un camí s-t amb capacitats
     * @param  G Grafo del cual se quiere encontrar un cami
     * @param Path camino que se ha encontrado
     * @return Flujo que se puede aumentar en el camino encontrado
     */

    public abstract int trobaCami(Graf G, HashMap Path);
    /**
     * Funció que crea el graf residual a partir del graf original
     * @param  G Graf del qual volem obtenir el graf residual
     * @return Graf residual
     * @pre El paràmetre implícit haurà de ser un graf no nul amb un inici i un destí
     * @post El valor que retorna es el graf original convertit a residual
     */

    public Graf getResidual(Graf G){
        Vertex sink = G.getFi();
        HashMap Path = new HashMap();
        int cap;

        while ((cap = trobaCami(G, Path)) > 0){

            int i = sink.getId();

            while ((int)Path.get(i) != -2){

                int v = (int)Path.get(i);
                Aresta a = G.getAresta(v, i);
                int capA = a.getCapacitat();
                a.setCapacitat(capA - cap);

                if (capA-cap == 0) G.eliminarAresta(a.getId());

                if (G.getAresta(i, v) == null){
                    Aresta a2 = new Aresta(0, cap, i, v);
                    G.afegirAresta(a2);}

                else{
                    Aresta a2 = G.getAresta(i, v);
                    int capA2 = a2.getCapacitat();
                    a2.setCapacitat(capA2 + cap);}

                i = v;
            }
        }
        return G;
    }
}