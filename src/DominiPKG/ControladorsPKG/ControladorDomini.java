package DominiPKG.ControladorsPKG;

import java.io.FileNotFoundException;

import DominiPKG.AlgorismePKG.MaxFlow;
import DominiPKG.GrafPKG.Vertex;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.GrafPKG.Aresta;

import DominiPKG.SiDonaTempsPKG.Grafics;
import DominiPKG.AgentPKG.Agent;
import DominiPKG.AgentPKG.cjtAgents;
import DominiPKG.PlanificacioPKG.Planificacio;
import DominiPKG.SolucioPKG.Solucio;
import DominiPKG.SolucioPKG.Ruta;
import PermanenciaPKG.IOPKG.CtrlDominifichers;
import javafx.util.converter.IntegerStringConverter;


import java.util.*;

/**
 * Created by Mirshi on 09/12/14.
 * 
 */
class ControladorDomini extends ControladorGraf  {

    private CtrlDominifichers ControlFichers;
    private cjtAgents agents;
    private Planificacio planing;
    private Solucio sol1;
    private HashMap<Agent,Ruta> cjtRutes;
    private ArrayList<Grafics> datos_grafs;   // posiciones 0 = 1 Origen 1 = 2 Origenes, 3 = Bfs, 4 = Dfs, 5 = Dikjstra



	public ControladorDomini(){
		ControlFichers = ControlFichers.getInstance();
		agents = new cjtAgents();
		cjtRutes = new HashMap<Agent, Ruta>();
		datos_grafs = new ArrayList<Grafics>();
	}

	public void resetSolucio(){
		sol1 = new Solucio();
	}

	// no esta b del tot perque ls contadors em fan la furla i no tinc un constructor adequat per solventaru
	public void afegirRutesassignades(String filename)
			throws FileNotFoundException {

		resetSolucio();
		ArrayList<String> rutesass = ControlFichers.cargardades(filename + ".txt");
		int i = 0;

		while ( i < rutesass.size() ) {
			int control = Integer.parseInt(rutesass.get(i));
			Aresta araux = null;
			Ruta raux = null;
			while (control != -2) {

				while (control != -1) {
					araux = new Aresta(control, Integer.parseInt(rutesass.get(i + 1)), Integer.parseInt(rutesass.get(i + 2)), Integer.parseInt(rutesass.get(i + 3)));
					i += 4;
					control = Integer.parseInt(rutesass.get(i));
				}
				raux.afegirAresta(araux);
				i++;
				control = Integer.parseInt(rutesass.get(i));
			}
			sol1.afegirRuta(raux);
			i++;
		}
	}


//afegeix els agents del archiu filename
   public void afegirConjAgents(String filename)
	   throws FileNotFoundException {
	   int i = 0;
	   ArrayList<String> cnjagents = ControlFichers.cargardades(filename + ".txt");
	   while (i < cnjagents.size()) {

		   agents.addAgent(Integer.parseInt(cnjagents.get(i)), cnjagents.get(i+1) );
		   i = i+2;

	   }
   }

	//borra tots els agents del conjunt
	public void resetcnjAgents(){
		agents = new cjtAgents();
	}

	//posa el cnj de agents en format de strings no hi ha un metode que em pe
	public ArrayList<String> llegeixConjAgents()
	{

		ArrayList<String> Agentsdata = new ArrayList<String>();
		ArrayList<Agent> agents1 = agents.getAgents();
		Agent Aaux;

		for (int i = 0; i < getNumAgents(); i++ ) {
			Aaux = agents1.get(i);
			Agentsdata.add(Integer.toString(Aaux.getId()) );
			Agentsdata.add(Aaux.getNom());
		}
		return Agentsdata;
	}
    /**
     * funcio que permet afegir un agent al conjunt.
     * @param id id de la persona a afegir
     * @param nom nom de la persona a afegir
     */
    public void afegirAgent(int id, String nom){
        agents.addAgent(id, nom);
    }

    /**
     * Funció que permet eliminar un agent al conjunt.
     * @param id id de la persona a eliminar
     */
    public void eliminarPersona(int id){
        agents.deleteAgent(id);
    }

    /**
     * Consultora d'una persona determinada
     * @param id id de la persona a consultar
     * @return Persona amb l'id indicat
     */
    public Agent consultaPersona(int id){
        return agents.getAgent(id);
    }

    /**
     * Consultora del numero d'agents presents al conjunt.
     * @return numero d'agents al conjunt
     */
    public int getNumAgents(){
        return agents.getNumAgents();
    }

    /**
     * Modificadora d'un agent
     * @param id de l'agent a modificar
     * @param nom nom nou per l'agent
     */
    public void modificarAgent(int id, String nom){
        Agent aux = agents.getAgent(id);
        aux.setNom(nom);
    }


    /**
     * Resolucio de planificacions
     * @Rsol1 , amb totes les rutes existens donat el Graf G de controlador de Graf i les especificacions de la planificacio, usant un unic Origen
     * @
     */


	public void generarSolucio(){

		sol1 = new Solucio();
		Graf g1 = getGraf();

		MaxFlow m = new MaxFlow();
		ArrayList<HashMap> cjt_cami = new ArrayList<HashMap>();
		Graf residual = m.getResidual(g1, planing.getAlg(),cjt_cami);

		for(int i=0;i<cjt_cami.size();++i){

			ArrayList<Aresta>arestas_ruta =  new ArrayList<Aresta>();
			Iterator it = cjt_cami.get(i).entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry ar = (Map.Entry)it.next();
				arestas_ruta.add(residual.getAresta((int)ar.getKey(),(int)ar.getValue()));
			}
			Ruta r = new Ruta(arestas_ruta);
			sol1.afegirRuta(r);
		}

	}
    /**
     * Resolucio de planificacions
     * @Generem la solucio sol1, amb un o laltre funcio en funcio del nombre de Origens 1 o 2, calculem que cumpleix les pautes definides, si es que no returnem false
     * @SI es que si cumpleix returnem true, en el cas de true  rellenem el cost total del planing, resolt pasa a ser 1 i assignem per cada agent una Ruta començant per la primera (en dikstra aquesta sera de millo calitat mes a menys nan de dalt a baix, en 
     */

	public boolean generarPlanificacio(){

		cjtRutes.clear();					//si generem planificacio  es reseteja la solucio sol1, i el hashmap de agents i rutes en el cas que hi hagues algo
		ArrayList<Agent> cnjAg;
		ArrayList<Ruta> cnjRt;
		int costt;
		cnjAg = agents.getAgents();

		generarSolucio();

			cnjRt = sol1.getLlistaRutas();

			if(agents.getNumAgents() > cnjRt.size()) return false;	

			else{
				costt = 0;	

				for(int i = 0; i < agents.getNumAgents(); i++){

					Agent Agaux = cnjAg.get(i);
					Ruta Rtaux = cnjRt.get(i);
					cjtRutes.put(Agaux, Rtaux);

					costt += Rtaux.getCost();
				}
				planing.setCostTotal(costt);
				planing.setcapTotal(cnjRt.size());
				planing.setResolt(1);
				return true;
			}
		
	}


    /**
     * Aquesta funcio crea una planificacio, els System.out.print serán subsituits per comunicació amb
     * el controlador grafic pertinent un cop aquest estigui definit.
     * @param algorisme identificador del algorisme (0 bfs, 1 dfs, 2 dijkstra)
     * @param Origen nom de la ciutat origen
     */
    public void crearPlanificacio(int algorisme, Vertex Origen){

		planing = new Planificacio(algorisme, Origen);

		if(planing.getResolt() == 0){

			if(!generarPlanificacio()){

				segonOrigen();

				if(!generarPlanificacio()) System.out.println("no es pot generar una PLanificacio ni amb 2 Origens");
				else System.out.println("planificacio generada correctament usant 2 Origens");
			}

			 else System.out.println("planificacio generada correctament amb 1 Origen");
		}
    }

/*
    public void modificarPlanificacio(){

        Scanner input = new Scanner(System.in);
	int opcio = input.next();

	if(opcio == 1) //modifica Resolt 0 or 1
	if(opcio == 2) //modifica Origen
	if(opcio == 3) //modifica Origen2
	if(opcio == 4) //moidifica algor
	if(opcio == 5) //moidifica numero de Origens 1 o 2

	//if() alguna modificacio != resolt  (guardar i crear new planificacio() copia de lanterior + modificacio and Resolt = 0;

     }
*/
    /**
     * Funcio cridad quan no es posible generar una planificacio amb un unic origen i es necessari que l'usuari
     * especifiqui un segon origen. L'input i output seran serán subsituits per comunicació amb el controlador
     * grafic pertinent un cop aquest estigui definit.
     */
    private void segonOrigen (){
        Scanner input = new Scanner(System.in);
        System.out.println("please, input the name of the new Origin");

		Vertex vauxin = new Vertex();
		vauxin.setNom(input.next());
		Aresta a1 = new Aresta(0, agents.getNumAgents() / 2, vauxin.getId(), planing.getOrigen().getId());
		Aresta a2 = new Aresta(0, agents.getNumAgents() / 2, vauxin.getId(), planing.getOrigen2().getId());

		getGraf().afegirAresta(a1);
		getGraf().afegirAresta(a2);
		vauxin.afegirOrigen(a1.getId());
		vauxin.afegirOrigen(a2.getId());
		getGraf().afegirVertex(vauxin);

		getGraf().getVertex(planing.getOrigen().getId()).afegirDesti(a1.getId());
		getGraf().getVertex(planing.getOrigen2().getId()).afegirDesti(a2.getId());

		getGraf().setInici(vauxin.getId());

        planing.setOrigen2(vauxin);
    }


    /**
     * Funcions per gestionar les dades per generar grafics
     */

	public void afegir_plan_resolt(){  //afegir un planing Pre: Resolt  == 1
	
	if(planing.getResolt() == 1){

		int opt = 0;
		if(planing.getNumOrigens() == 1) opt = 0; 
		if(planing.getNumOrigens() == 2) opt = 1; 

		Grafics Graux = datos_grafs.get(opt);

		Graux.setcaT(planing.getcapTotal() + Graux.getcaT());
		Graux.setcst(planing.getcostTotal() + Graux.getcsT());
		Graux.setNA(agents.getNumAgents() + Graux.getNA());
		Graux.setNM(Graux.getNM() + 1);

		datos_grafs.set(opt, Graux);

	}
   }

	public void resetejar_mostres(){

		Grafics Graux = new Grafics();
		datos_grafs.set(0, Graux);
		datos_grafs.set(1, Graux);
    }


								//pre: nmos1 > 0 and nmos2 >0
    private ArrayList<String> get_comparacio_mostres(){     /// cost1, cost2 (etc..()), capacitat, num agents, num mostres devuelve la coleccion de datos relevantes de 2 grupos 1ero con 1 origen 2ndo con 2 origenes
							// con los datos ponderados por la cantidad de muestras en el momento de la comparacion
	Grafics GrauxO1 = datos_grafs.get(0);
	Grafics GrauxO2 = datos_grafs.get(1);

	ArrayList<String> vect_g = new ArrayList<>();
	int nmos1, nmos2;

	nmos1 = GrauxO1.getNM();
	nmos2 = GrauxO2.getNM();

	if(nmos1 > 0 && nmos2 > 0){

		vect_g.add(Integer.toString(GrauxO1.getcsT() / nmos1)) ;
		vect_g.add( Integer.toString(GrauxO1.getcaT() / nmos1));
		vect_g.add(  Integer.toString(GrauxO1.getNA() / nmos1));
		vect_g.add( Integer.toString(nmos1));

		vect_g.add(  Integer.toString( GrauxO2.getcsT() / nmos2));
		vect_g.add(  Integer.toString(GrauxO2.getcaT() / nmos2));
		vect_g.add(Integer.toString(GrauxO2.getNA() / nmos2));
		vect_g.add(  Integer.toString(nmos2));

		return vect_g;
	}
	return null; 

    }

    /**
     * retorna la Planificació, el cnj de rutes triades, el mapa usat, Total de camins posibles existens en el mapa per poderla representar graficament
     * @return planing Planificacio generada.
     */
    public Planificacio getPlanificacio(){
        return planing;
    }
    public HashMap<Agent,Ruta> getCnjRutes(){
        return cjtRutes;
    }
    public Solucio getTotaldeRutesExistens(){
        return sol1;
    }




//MODIficacio, eliminacio, lectura, creacio es fa via Controlador Graf






}
