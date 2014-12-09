package DominiPKG.SiDonaTempsPKG;

import DominiPKG.GrafPKG.Aresta;

/**
 * @author Marina Sauca
 * @version 0.2
 * @since 27.11.2014
 */
public class Cami extends Aresta {
    private int ID;
    private int costT;
    private int costE;
    private int capacitat;
    private int id_vertex_original;
    private int id_vertex_adjunt;
    static private int idCount = 0;


    /**
     * Creadora per defecte
     */
    public Cami() {
        this.ID = idCount++;
    }


    /** Creadora de la classe
     * @param costT Cost temporal del cami
     * @param costE cost economic del cami
     @param capacitat Capacitat de la cami
     @param id_vertex_inici ID del vertex del origen
     @param id_vertex_final ID del vertex Adjunt
     */
    public Cami(int costT, int costE, int capacitat, int id_vertex_inici, int id_vertex_final) {
        this.ID = idCount++;
        this.costT = costT;
        this.costE = costE;
        this.capacitat = capacitat;
        this.id_vertex_original = id_vertex_inici;
        this.id_vertex_adjunt = id_vertex_final;
    }

    public void setCostT(int costT) {
        this.costT = costT;
    }

    public void setCostE(int costE) {
        this.costE = costE;
    }

    public int getCostE() {
        return costE;
    }

    public int getCostT(){
        return costT;
    }

}
