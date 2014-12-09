package DominiPKG.SiDonaTempsPKG;

import DominiPKG.ControladorsPKG.ControladorGraf;
import DominiPKG.GrafPKG.Aresta;
import DominiPKG.GrafPKG.Graf;

/**
 * Created by Mirshi on 06/12/14.
 */
public class ControladorMapa extends ControladorGraf {
    private final Graf G;

    /* Pre: cert
     * Post: El resultat és un controlador de graf buit
     */
    public ControladorMapa(){
        G = new Graf();
    }

    /* Pre:
     * Post: Afegeix la aresta al graf, si la aresta ja existia no fa res
     */
    public int afegirCami(int costT, int costE, int capacitat, int id_vertex_inici, int id_vertex_final){
        Aresta a = new Cami(costT, costE , capacitat, id_vertex_inici, id_vertex_final);
        G.getVertex(id_vertex_inici).afegirDesti(a.getId());
        G.getVertex(id_vertex_final).afegirOrigen(a.getId());
        G.afegirAresta(a);
        return a.getId();
    }
}
