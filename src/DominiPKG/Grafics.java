package DominiPKG.ControladorsPKG;

import DominiPKG.AgentPKG.Agent;
import DominiPKG.AgentPKG.cjtAgents;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.PlanificacioPKG.Planificacio;

import java.util.Scanner;

/**
 * Created by Mirshi on 09/12/14.
 * WIP TODO
 */
public class Grafics {

	int num_muestras;
	int capacidad_total;
	int costo_total;
	int num_agentes;



    public Grafics(){

	this.num_muestras = 0;
	this.capacidad_total = 0;
	this.costo_total = 0;
	this.num_agentes = 0;

   }

    public int getNM() {
        return num_muestras;
    }
    public int getcaT() {
        return capacidad_total;
    }
    public int getcsT() {
        return costo_total;
    }
    public int getNA() {
        return num_agentes;
    }



	public void inc_muestras(){
		this.num_muestras++;
	}

	public void inc_Cap(int cap){
		this.capacidad_total += cap;
	}	

	public void inc_Costo(int cost){
		this.costo_total += cost;
	}
	
	public void inc_num_ag(int numa){
		this.num_agentes += numa ;
	}


}
