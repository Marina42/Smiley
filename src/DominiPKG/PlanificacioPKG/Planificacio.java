package DominiPKG.PlanificacioPKG;

import DominiPKG.SolucioPKG.Ruta;
import java.lang.NullPointerException;
import DominiPKG.GrafPKG.Vertex;


public class Planificacio {

	private Vertex Origen;
	private Vertex Origen2;
	private int algUsat;
	private int costTotal;
	private int capTotal;
	private int numOrigens;
	private int resolt;

	public Planificacio(){}

	public Planificacio(int algorisme, Vertex Origen) {
		this.algUsat = algorisme;
		this.Origen = Origen;
		this.Origen2 = new Vertex();
		this.numOrigens = 1;
		this.resolt = 0;
	}

	public int getAlg() {
		return algUsat;
	}

	public int getResolt() {
		return resolt;
	}

	public int getcostTotal() {
		return costTotal;
	}

	public int getcapTotal() {
		return capTotal;
	}

	public int getNumOrigens() {
		return numOrigens;
	}

	public Vertex getOrigen2() {
		return Origen2;
	}

	public Vertex getOrigen() {
		return Origen;
	}


	public void setOrigen(Vertex origen) {
		this.Origen = origen;
	}

	public void setOrigen2(Vertex origen) {

		Origen2 = origen;

		if (numOrigens == 1) numOrigens++;

	}

	public void setCostTotal(int cst) {
		this.costTotal = cst;
	}

	public void setAlgUsat(int cst) {
		this.algUsat = cst;
	}

	public void setResolt(int estat) {  // 1 Resolt 0 per resoldre
		this.resolt = estat;
	}

	public void setcapTotal(int cst) {
		this.capTotal = cst;
	}

	public void setNumOrigens(int cst) {
		this.numOrigens = cst;
	}

}






