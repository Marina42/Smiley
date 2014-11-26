package DominiPKG.SolucioPKG;
/**
 *Clase que representa el objeto ruta
 * @author Leonardo Andrade
 */


import DominiPKG.ControladorsPKG.ControladorGraf;
import DominiPKG.GrafPKG.Aresta;

import java.util.ArrayList;


public class Ruta {
    /* Atributos de la clase */
    private static int id = 0;
    private int cost;
    private ArrayList<Aresta>ListaArestas = new ArrayList<Aresta>();
    /*contructor por defecto*/
    public Ruta(ArrayList<Aresta> LAresta) {
        id ++;
        this.ListaArestas = LAresta;
    }
    /*Funcion obtener id
    *@return id Identificador de clase
    */
    public int getId() {
        return id;
    }
    /*Funcion definir id
    *@param id Identificador de clase
    */
    public void setId(int id) {
        this.id = id;
    }
    /*Funcion obtener estructura de arestas
    *@return id Identificador de clase
    */
    public ArrayList<Aresta> getListaArestas() {
        return ListaArestas;
    }
    /*Funcion obtener estructura de arestas
    *@return id Identificador de clase
    */
    public void setListaArestas(ArrayList<Aresta> ListaArestas) {
        this.ListaArestas = ListaArestas;
    }
    /* Funcio per obtenir el cost de la ruta
    *@return retorna el cost asociat a la ruta
    */
    public int getCost(){
        int i=0;
        boolean eliminat = false;
        cost = 0;
        ControladorGraf ctrGraf = new ControladorGraf();
        if(!ListaArestas.isEmpty()){
            while((i<ListaArestas.size())){
                cost = cost + ctrGraf.getCostAresta(ListaArestas.get(i).getId());
                i++;
            }
        }
        return cost;
    }
    /*Funcio per afegir una aresta a la ruta
    *@param aresta
    */
    public void afegirAresta(Aresta aresta){
        ListaArestas.add(aresta);
    }

    public void eliminarAresta(int id){
        int i=0;
        boolean eliminat = false;
        if(!ListaArestas.isEmpty()){
            while((i<ListaArestas.size())&&(eliminat == false)){
                if(ListaArestas.get(i).getId()==id){
                    ListaArestas.remove(i);
                    eliminat=true;
                }
                i++;
            }
        }
    }
    public Aresta obtenirAresta(int id){
        int i = 0;
        boolean trobat = false;
        Aresta aresta = null;
        if(!ListaArestas.isEmpty()){
            while((i<ListaArestas.size())&&(trobat == false)){
                if(ListaArestas.get(i).getId()==id){
                    aresta = ListaArestas.get(i);
                    trobat=true;
                }
                i++;
            }
        }
        return aresta;
    }

}