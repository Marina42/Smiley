package DominiPKG.GrafPKG;
/**
 * Classe Aresta
 * @author arnau.santamaria
 */
public class Aresta {
    private int ID;
    private int cost;
    private int capacitat;
    private int id_vertex_original;
    private int id_vertex_adjunt;
    static private int idCount = 0;


    /**
     * Creadora per defecte
     */
    public Aresta() {
        this.ID = idCount++;
    }


    /** Creadora de la classe
     * @param cost Cost de la aresta
     @param capacitat Capacitat de la aresta
     @param id_vertex_inici ID del vertex del origen
     @param id_vertex_final ID del vertex Adjunt
     */
    public Aresta(int cost, int capacitat, int id_vertex_inici, int id_vertex_final) {
        this.ID = idCount++;
        this.cost = cost;
        this.capacitat = capacitat;
        this.id_vertex_original = id_vertex_inici;
        this.id_vertex_adjunt = id_vertex_final;
    }


    // Consultores

    /** Consultora de la ID del vertex origen
     *	@return ID del vertex origen
     */
    public int getId_vertex_original() {
        return id_vertex_original;
    }

    /** Consultora de la ID del vertex Adjunt
     *	@return ID del vertex Adjunt
     */
    public int getId_vertex_adjunt() {
        return id_vertex_adjunt;
    }

    /** Consultora del cost de l'aresta
     *  @return Cost de la aresta
     */
    public int getCost() {
        return cost;
    }

    /** Consultora de la capacitat de l'aresta
     * @return Capacitat de l'aresta
     */
    public int getCapacitat() {
        return capacitat;
    }


    /** Consultora de ID de l'aresta
     *   @return ID de l'aresta
     */
    public int getId() {
        return ID;
    }


    // Modificadores

    /** Modificadora del vertex origen de l'aresta
     *	@param id_vertex_original Nou id del vertex origen de l'aresta
     */
    public void setId_vertex_original(int id_vertex_original) {
        this.id_vertex_original = id_vertex_original;
    }

    /** Modificadora del id del vertex adjunt
     *  @param id_vertex_adjunt Nou id del vertex adjunt
     */
    public void setId_vertex_adjunt(int id_vertex_adjunt) {
        this.id_vertex_adjunt = id_vertex_adjunt;
    }

    /** Modificadora del cost de l'aresta
     *   @param cost Nou cost de l'aresta
     */
    public void setCost(int cost) {
        this.cost = cost;
    }


    /** Modificadora de la capacitat de l'aresta
     *   @param capacitat Nova capacitat de l'aresta
     */
    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    /** Modificadora de ID de l'aresta
     *   @param id Nou ID de l'aresta
     */
    public void setId(int id) {
        this.ID = id;
    }

}