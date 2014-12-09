package DominiPKG.AlgorismePKG;
import DominiPKG.GrafPKG.Aresta;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.GrafPKG.Vertex;

import java.util.*;

/**
 *
 * @author Arnau Farràs
 */
public class BFS implements Algorisme {

    private final int INFINIT = Integer.MAX_VALUE;      //infinit

    /**
     *
     */
    public BFS(){}  //creadora

    /**
     *
     * @param G
     * @param P
     * @return
     * @Pre:  G no nul, i una taula de hash buida
     * @Post: El resultat es un enter que indica la capacitat del camí trobat,
     *         l'array P contindrà la taula de pares del recorregut
     */
    public int trobaCami(Graf G, HashMap P){
        Vertex s= G.getInici();
        Vertex t= G.getFi();
        HashMap M = new HashMap();
        //int[] M = new int[G.getDimensio()];
        for(int i=0;i<G.getDimensio(); i++){
            P.put(i,-1);
        }
        P.put(s.getId(), -2);
        M.put(s.getId(),INFINIT);
        Queue<Vertex> Q = new LinkedList<>();
        Q.add(s);
        while(Q.size()>0){
            Vertex u=Q.poll();
            for(int aId : G.getAdjacentsDestins(u.getId())){
                Aresta a = G.getAresta(aId);
                int v = a.getId_vertex_adjunt();
                if(a.getCapacitat() > 0 && P.get(v).equals(-1)){
                    P.put(v,u.getId());
                    if((int)M.get(u.getId()) > a.getCapacitat()){
                        M.put(v, a.getCapacitat());
                    }
                    else M.put(v, M.get(u.getId()));

                    if(v!=t.getId()) Q.add(G.getVertex(v));
                    else return (int)M.get(t.getId());
                }
            }
        }

        return 0;
    }

}