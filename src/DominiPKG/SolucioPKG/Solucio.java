package DominiPKG.SolucioPKG;

import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pablo
 */
public class Solucio {
    private ArrayList<Ruta> LlistaRutas;
    /* Constructor de la clase Solucio
    */

    public Solucio() {
        LlistaRutas = new ArrayList<>();
    }


    /*
    *metodo afegirRuta consisteix en afegir una ruta a la estructura del conjunt
    *@param ruta , la ruta que es vol afegir a l'estructure
    */
    public void afegirRuta(Ruta ruta){
        LlistaRutas.add(ruta);
    }


    /**
     * Consultora del conjunt de rutas de la solució
     * @return conjunt de rutas
     */
    public ArrayList<Ruta> getLlistaRutas() {
        return LlistaRutas;
    }

    /**
     * Modificadora del conjunt de rutas de la solució
     * @param LlistaRutas
     */
    public void setLlistaRutas(ArrayList<Ruta> LlistaRutas) {
        this.LlistaRutas = LlistaRutas;
    }



}
