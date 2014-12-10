package DominiPKG.ControladorsPKG;
import DominiPKG.GrafPKG.Aresta;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.GrafPKG.Vertex;

import java.util.ArrayList;


/**
 *
 * @author Kilian
 */
public class ControladorGraf {

    private final Graf G;


    /** Pre: cert
     * Post: El resultat és un controlador de graf buit 
     */

    public ControladorGraf(){
        G = new Graf();
    }

    /** Pre:
     * Post: Et diu si el vertex amb la id implícita existeix al graf
     * @param idV
     * @return
     */

    public boolean existeixVertex(int idV){
        return G.existeixVertex(idV);
    }


    /** Pre:
     * Post: Et diu si la aresta amb la id implícita existeix al graf
     * @param idA
     * @return
     */

    public boolean existeixAresta(int idA){
        return G.existeixAresta(idA);
    }


    /** Pre: 
     * Post: Afegeix el vertex implícit al graf, si aquest vertex ja hi era al graf, no fa res
     * @param nom
     * @return
     */

    public int afegirVertex(String nom){
        Vertex v = new Vertex(nom);
        G.afegirVertex(v);
        return v.getId();
    }


    /** Pre:
     * Post: Elimina el vertex amb id idV del graf
     * @param idV
     */

    public void eliminarVertex(int idV){
        G.eliminarVertex(idV);
    }

    /** Pre:
     * Post: Afegeix la aresta al graf, si la aresta ja existia no fa res
     * @param cost
     * @param capacitat
     * @param id_vertex_inici
     * @param id_vertex_final
     * @return
     */

    public int afegirAresta(int cost, int capacitat, int id_vertex_inici, int id_vertex_final){
        Aresta a = new Aresta(cost, capacitat, id_vertex_inici, id_vertex_final);
        G.afegirAresta(a);
        return a.getId();
    }

    /** Pre:
     * Post: Elimina l'aresta amb id idA del graf
     * @param idA
     */

    public void eliminarAresta(int idA){
        G.eliminarAresta(idA);
    }

    /**Pre:
     * Post: Retorna el cost de l'aresta
     * @param idA
     * @return
     */
    public Integer getCostAresta(int idA){
        return G.getAresta(idA).getCost();
    }

    /**Pre:
     * Post: Retorna la capacitat de l'aresta
     * @param idA
     * @return
     */
    public Integer getCapacitatAresta(int idA){
        return G.getAresta(idA).getCapacitat();
    }

    /**Pre:
     * Post: canvia el cost a l'aresta idA
     * @param idA
     * @param cost
     */
    public void setCostAresta(int idA, int cost){
        G.getAresta(idA).setCost(cost);
    }

    /** Pre:
     * Post:canvia la capacitat a l'aresta idA
     * @param idA
     * @param capacitat
     */
    public void setCapacitatAresta(int idA, int capacitat){
        G.getAresta(idA).setCapacitat(capacitat);
    }



    /** Pre:
     * Post: Retorna el vertex inici del graf
     * @return
     */

    public Vertex getInici(){
        return G.getInici();
    }


    /** Pre:
     * Post: El vertex implícit ara és el vertex inici del graf
     * @param idV
     */

    public void setInici(int idV){
        G.setInici(idV);
    }

    /** Pre: 
     * Post: Retorna el vertex fi del graf
     * @return
     */

    public Vertex getFi(){
        return G.getFi();
    }


    /** Pre:
     * Post: El vertex implícit ara és el vertex fi del graf
     * @param idV
     */

    public void setFi(int idV){
        G.setFi(idV);
    }

    /** Pre:
     * post: Retorna el numero de vertexs del graf
     * @return
     */

    public int getDimensio(){
        return G.getDimensio();
    }


    /** Pre: 
     * Post: Si hi ha algun vertex amb la id implicita al graf, ho retorna, si no, retorna null
     * @param idV
     * @return
     */

    public Vertex getVertex(int idV){
        return G.getVertex(idV);
    }

    /** Pre:
     * Post: Si els vertexs implicits son correctes, retorna l'aresta que va de idU a idV,
     * si no, retorna null
     * @param idU
     * @param idV
     * @return
     */

    public Aresta getAresta(int idU, int idV) {
        return G.getAresta(idU, idV);
    }

    /** Pre:
     * Post: Si la id d'aresta implicita es correcte, retorna l'aresta amb id idA,
     * si no, retorna null
     * @param idA
     * @return
     */

    public Aresta getAresta(int idA) {
        return G.getAresta(idA);
    }

    /** Pre:
     * Post: Si el vertex existeix, retorna les arestes que apunten a l'implícit,
     * si no, retorna una llista buida
     * @param idV
     * @return
     */

    public ArrayList<Integer> getAdjacentsOrigen(int idV){
        return G.getAdjacentsOrigen(idV);
    }

    /** Pre:
     * Post: Si el vertex existeix, retorna les arestes als que apunta l'implícit,
     * si no, retorna una llista buida
     * @param idV
     * @return
     */

    public ArrayList<Integer> getAdjacentsDestins(int idV){
        return G.getAdjacentsDestins(idV);
    }

    public Graf getGraf(){
        return G;
    }
}