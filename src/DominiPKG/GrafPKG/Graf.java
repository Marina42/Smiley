package DominiPKG.GrafPKG;
import java.util.ArrayList;
/**
 *
 * @author kilian.peiro
 */
public class Graf {
    private ArrayList<Vertex> vertexArray;
    private ArrayList<Aresta> arestaArray;
    private Vertex vertexInici;
    private Vertex vertexFi;

    /** Pre: cert
     * Post: El resultat es un graf buit
     */
    public Graf(){
        vertexArray = new ArrayList<Vertex>();
        arestaArray = new ArrayList<Aresta>();
    }



    /** Pre:
     * Post: Et diu si el vertex amb la id implícita existeix al graf
     * @param idV
     * @return
     */
    public boolean existeixVertex(int idV){
        for(int i = 0; i < vertexArray.size(); ++i){
            if(vertexArray.get(i).getId() == idV) return true;
        }
        return false;
    }



    /** Pre:
     * Post: Et diu si la aresta amb la id implícita existeix al graf
     * @param idA
     * @return
     */
    public boolean existeixAresta(int idA){
        for(int i = 0; i < arestaArray.size(); ++i){
            if(arestaArray.get(i).getId()== idA) return true;
        }
        return false;
    }

    /** Pre:
     * Post: Afegeix el vertex implícit al graf, si aquest vertex ja hi era al graf, no fa res
     * @param v
     * @return
     */
    public int afegirVertex(Vertex v){
        if(!existeixVertex(v.getId())) {
            vertexArray.add(v);
            return v.getId();
        }
        return -1;
    }

    /** Pre:
     * Post: Elimina el vertex amb id idV del graf
     * @param idV
     */
    public void eliminarVertex(int idV){
        if(!existeixVertex(idV)) return;
        for(int i = 0; i < vertexArray.size(); ++i){
            if(vertexArray.get(i).getId() == idV){
                for(int j = 0; j < arestaArray.size(); ++j){
                    if(arestaArray.get(j).getId_vertex_adjunt()== vertexArray.get(i).getId()
                            || arestaArray.get(j).getId_vertex_original() == vertexArray.get(i).getId()){
                        arestaArray.remove(j);
                        --j;
                    }
                }
                vertexArray.remove(i);
            }
        }
        if(vertexInici.getId() == idV) vertexInici = null;
        if(vertexFi.getId() == idV) vertexFi = null;
    }

    /** Pre:
     * Post: Afegeix la aresta al graf, si la aresta ja existia no fa res
     * @param a
     * @return
     */
    public int afegirAresta(Aresta a){
        if(!existeixAresta(a.getId())){
            if(getVertex(a.getId_vertex_adjunt()) != null &&
                    getVertex(a.getId_vertex_original()) != null) {
                arestaArray.add(a);
                Vertex v = getVertex(a.getId_vertex_adjunt());
                v.afegirOrigen(a.getId());
                Vertex u = getVertex(a.getId_vertex_original());
                u.afegirDesti(a.getId());
            }
            return a.getId();
        }
        return -1;
    }

    /**Pre:
     *Post: Retorna les id's de tots els vertexs del graf
     * @return
     */
    public ArrayList<Integer> getVertexs(){
        ArrayList<Integer> sol = new ArrayList<Integer>();
        for(int i = 0; i < getDimensio(); ++i){
            sol.add(vertexArray.get(i).getId());
        }
        return sol;
    }


    /** Pre:
     * Post: Elimina l'aresta amb id idA del graf
     * @param idA
     */

    public void eliminarAresta(int idA){
        if(!existeixAresta(idA)) return;
        for(int i = 0; i < arestaArray.size(); ++i){
            if(arestaArray.get(i).getId() == idA){
                for(int j = 0; j < vertexArray.size(); ++j){
                    Vertex v = vertexArray.get(j);
                    v.eliminarOrigen(idA);
                    v.eliminarDesti(idA);
                }
                arestaArray.remove(i);
            }
        }
    }

    /** Pre:
     * Post: Retorna el vertex inici del graf
     * @return
     */
    /** Pre:
     * Post: Crea una copia del graf
     * @param
     */
    public Graf copiaGraf(){
        Graf G = new Graf();
        for(int i = 0; i < vertexArray.size(); ++i){
            Vertex v;
            v = new Vertex(vertexArray.get(i));
            G.vertexArray.add(v);
        }
        for(int i = 0; i < arestaArray.size(); ++i){
            Aresta a;
            a = new Aresta(arestaArray.get(i));
            G.arestaArray.add(a);
        }
        Vertex inici = new Vertex(vertexInici);
        G.setInici(inici.getId());
        Vertex fi = new Vertex(vertexFi);
        G.setFi(fi.getId());
        return G;
    }

    public Vertex getInici(){
        return vertexInici;
    }

    /** Pre:
     * Post: El vertex implícit ara és el vertex inici del graf
     * @param idV
     */

    public void setInici(int idV){
        if(!existeixVertex(idV)) return;
        for(int i = 0; i < vertexArray.size(); ++i){
            if(vertexArray.get(i).getId() == idV) vertexInici = vertexArray.get(i);
        }
    }


    /** Pre:
     * Post: Retorna el vertex fi del graf
     * @return
     */

    public Vertex getFi(){
        return vertexFi;
    }

    /** Pre:
     * Post: El vertex implícit ara és el vertex fi del graf
     * @param idV
     */

    public void setFi(int idV){
        if(!existeixVertex(idV)) return;
        for(int i = 0; i < vertexArray.size(); ++i){
            if(vertexArray.get(i).getId() == idV) vertexFi = vertexArray.get(i);
        }
    }

    /** Pre:
     * post: Retorna el numero de vertexs del graf
     * @return
     */

    public int getDimensio(){
        return vertexArray.size();
    }

    /** Pre:
     * Post: Si hi ha algun vertex amb la id implicita al graf, ho retorna, si no, retorna null
     * @param idV
     * @return
     */

    public Vertex getVertex(int idV){
        for(int i = 0; i < vertexArray.size(); ++i){
            if(vertexArray.get(i).getId() == idV) return vertexArray.get(i);
        }
        return null;
    }

    /** Pre:
     * Post: Si els vertexs implicits son correctes, retorna l'aresta que va de idU a idV,
     * si no, retorna null
     * @param idU
     * @param idV
     * @return
     */

    public Aresta getAresta(int idU, int idV) {
        for(int i = 0; i < arestaArray.size(); ++i){
            if(
                    arestaArray.get(i).getId_vertex_original() == idU
                            && arestaArray.get(i).getId_vertex_adjunt() == idV
                    )
                return arestaArray.get(i);
        }
        return null;
    }

    /** Pre:
     * Post: Si la id d'aresta implicita es correcte, retorna l'aresta amb id idA,
     * si no, retorna null
     * @param idA
     * @return
     */

    public Aresta getAresta(int idA) {
        for(int i = 0; i < arestaArray.size(); ++i){
            if(
                    arestaArray.get(i).getId() == idA)
                return arestaArray.get(i);
        }
        return null;
    }

    /** Pre:
     * Post: Si el vertex existeix, retorna les arestes que apunten a l'implícit,
     * si no, retorna una llista buida
     * @param idV
     * @return
     */

    public ArrayList<Integer> getAdjacentsOrigen(int idV){
        if(!existeixVertex(idV)) return null;
        return getVertex(idV).getOrigens();
    }

    /** Pre:
     * Post: Si el vertex existeix, retorna les arestes als que apunta l'implícit,
     * si no, retorna una llista buida
     * @param idV
     * @return
     */

    public ArrayList<Integer> getAdjacentsDestins(int idV){
        if(!existeixVertex(idV)) return null;
        return getVertex(idV).getDestins();
    }
}