package DominiPKG.SiDonaTempsPKG;

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

	private int num_muestras;
	private int capacidad_total;
	private int costo_total;
	private int num_agentes;

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


	public void setNM(int num_muestras) {
		this.num_muestras = num_muestras;
	}
	public void setcaT(int capacidad_total) {
		this.capacidad_total = capacidad_total;
	}
	public void setcst(int costo_total) {
		this.costo_total = costo_total;
	}
	public void setNA(int num_agentes) {
		this.num_agentes = num_agentes;
	}
}
