package DominiPKG.GrafPKG;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author Marina Sauca
 * @version 0.2
 * @since 13.11.2014
 */
public class Vertex {
    private int iD;
    private String nom;
    private ArrayList <Integer> OrigensArray;
    private ArrayList <Integer> DestinsArray;
    static private int idCount = 0;

    /**
     * Creadora per defecte
     */
    public Vertex(){
        this.iD = idCount++;
        nom = "";
        OrigensArray = new ArrayList <Integer>();
        DestinsArray = new ArrayList <Integer>();
    }
    /**
     * Creadora de la clase
     * @param nom del vertex
     */
    public Vertex (String nom){
        this.iD = idCount++;
        this.nom = nom;
        OrigensArray = new ArrayList <Integer>();
        DestinsArray = new ArrayList <Integer>();
    }

    /**
     * creadora-clonadora de Vertex
     * @param v vertex a clonar
     */
    public Vertex (Vertex v){
        this.iD = idCount++;
        this.nom = v.getNom();
        this.OrigensArray = new ArrayList<Integer>(v.getOrigens());
        this.DestinsArray = new ArrayList<Integer>(v.getDestins());
    }

    /**
     * Consultora del ID
     * @return ID del vertex
     */
    public int getId(){
        return iD;
    }

    /**
     * Consultora del nom
     * @return nom del vertex
     */
    public String getNom(){ return nom;}

    public void setNom(String nom){ this.nom = nom; }

    /**
     * Consultora de l'array d'arestes que tenen aquest vertex com a origen
     * @return ArrayList que conté la llista d'id de les arestes
     */
    public ArrayList<Integer> getOrigens(){
        return OrigensArray;
    }
    /**
     * Consultora de l'array d'arestes que tenen aquest vertex com a desti
     * @return ArrayList que conté la llista d'id de les arestes
     */
    public ArrayList<Integer> getDestins(){
        return DestinsArray;
    }

    /**
     * Fucnió encarregada d'afegir una aresta a la llista d'origens
     * @param idOrigen id de l'aresta a afegir
     */
    public void afegirOrigen (int idOrigen){
        OrigensArray.add(idOrigen);
    }
    /**
     * Fucnió encarregada d'afegir una aresta a la llista de destins
     * @param idDesti id de l'aresta a afegir
     */
    public void afegirDesti (int idDesti){
        DestinsArray.add(idDesti);
    }

    /**
     * eliminadora d'una aresta de la llista d'origens
     * @param idOrigen id de l'aresta a eliminar
     * @return true si existia i pertant s'ha eliminat, false si no existia.
     */
    public boolean eliminarOrigen(int idOrigen){
        boolean deleted = false;
        if (!OrigensArray.isEmpty()) {
            Iterator<Integer> it = OrigensArray.iterator();
            while (it.hasNext())
                if (idOrigen == it.next()) {
                    it.remove();
                    deleted = true;
                }
        }
        return deleted;
    }
    /**
     * eliminadora d'una aresta de la llista de destins
     * @param idDesti id de l'aresta a eliminar
     * @return true si existia i pertant s'ha eliminat, false si no existia.
     */
    public boolean eliminarDesti(int idDesti){
        boolean deleted = false;
        if (!DestinsArray.isEmpty()) {
            Iterator<Integer> it = DestinsArray.iterator();
            while (it.hasNext())
                if (idDesti == it.next()) {
                    it.remove();
                    deleted = true;
                }
        }
        return deleted;
    }

}