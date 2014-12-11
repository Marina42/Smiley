package DominiPKG.ControladorsPKG;

import java.util.*;

/**
 * Created by Mirshi on 09/12/14.
 * 
 */
class ControladorDomini extends ControladorGraf  {

   // private CtrlDominifichers ControlFichers;
    private cjtAgents agents;
    private Planificacio planing;
    private Solucio sol1;
    private HashMap<Agent,Ruta> cjtRutes;
   private ArrayList<Grafics> datos_grafs;   // posiciones 0 = 1 Origen 1 = 2 Origenes, 3 = Bfs, 4 = Dfs, 5 = Dikjstra



	public ControladorDomini(){
	//	ControlFichers = ControlFichers.getInstance();

		agents = new cjtAgents();
		datos_grafs = new ArrayList<Grafics>();
	}


/*
   public void afegirConjAgents(String filename){


		HashMap<Integer,String> Agentsdata = ControlFichers.cargarAgentes(filename + ".txt");

		HashMap<Integer,String> agents = new LinkedList<Agent>();

		for (String Agentsdata : Agentsdata)
			agents.afegirAgent(new Agent(Agentsdata));

		ArrayList<String> lista = new ArrayList<String>();

		for (Agent agent : agents) {
			lista.add(agent.toString());
			this.agents.put(Integer.parseInt(agent), asignatura);
		}

	}
*/

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


	public void generarSolucio(int val){

		sol1 = new Solucio();
		Graf g1 = getgraf();

		if(val == 1) {

			Vertex vauxin = new Vertex();
			Aresta a1 = new Aresta(0, agents.getNumAgents() / 2, vauxin.getId(), Origen.getId());
			Aresta a2 = new Aresta(0, agents.getNumAgents() / 2, vauxin.getId(), Origen2.getId());

			g1.afegirAresta(a1);
			g1.afegirAresta(a2);
			vauxin.afegirOrigen(a1.getId());
			vauxin.afegirOrigen(a2.getId());
			g1.afegirVertex(vauxin);

			g1.getVertex(Origen.getId()).afegirDesti(a1.getId());
			g1.getVertex(Origen2.getId()).afegirDesti(a2.getId());

			g1.setInici(vauxin);
		}

		MaxFlow m = new MaxFlow();
		ArrayList<HashMap> cjt_cami = new ArrayList<HashMap>();
		Graf residual = m.getResidual(g1, planing.getAlg(),cjt_cami);

		for(int i=0;i<cjt_cami.size();++i){

			ArrayList<Aresta>arestas_ruta =  new ArrayList<Aresta>();
			Iterator it = cjt_cami.get(i).entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry ar = (Map.Entry)it.next();  
				Aresta aaux = residual.getAresta((int)ar.getKey(),(int)ar.getValue());
				if(val == 1) {
					if (aaux.getId() != a1.getId && aaux.getId() != a1.getId) arestas_ruta.add(aaux);
				}
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

	public bool generarPlanificacio(){

		cjtRutes.clear();					//si generem planificacio  es reseteja la solucio sol1, i el hashmap de agents i rutes en el cas que hi hagues algo
		ArrayList<Agent> cnjAg;
		ArrayList<Ruta> cnjRt;
		int costt = 0;
		cnjAg = agents.getAgents();

		if(planing.getNumOrigens() == 1) generarSolucio(0);
		else generarSolucio(1);

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

		if(planing.get_Resolt() == 0){

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
		Vertex vaux = getGraf().getVertex(input.next());
        planing.setOrigen2(vaux);
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

		Graux.inc_Cap(planing.getcapTotal());
		Graux.inc_Costo(planing.getCosTotal());
		Graux.inc_num_ag(agents.size());
		Graux.inc_muestras();

		datos_grafs.set(opt, Graux);

	}
   }

	public void resetejar_mostres(){

		Grafics Graux = new Grafics();
		datos_grafs.set(0, Graux);
		datos_grafs.set(1, Graux);
    }


								//pre: nmos1 > 0 and nmos2 >0
    private ArrayList<Integer> get_comparacio_mostres(){     /// cost1, cost2 (etc..()), capacitat, num agents, num mostres devuelve la coleccion de datos relevantes de 2 grupos 1ero con 1 origen 2ndo con 2 origenes
							// con los datos ponderados por la cantidad de muestras en el momento de la comparacion
	Grafics GrauxO1 = datos_grafs.get(0);
	Grafics GrauxO2 = datos_grafs.get(1);

	int[] vect_g = new int[8];
	int nmos1, nmos2;

	nmos1 = GrauxO1.getNM();
	nmos2 = GrauxO2.getNM();

	if(nmos1 > 0 && nmos2 > 0){

		vect_g[0] = GrauxO1.getcsT() / nmos1;
		vect_g[2] = GrauxO1.getcaT() / nmos1;
		vect_g[4] = GrauxO1.getNA() / nmos1;

		vect_g[1]= GrauxO2.getcsT() / nmos2;
		vect_g[3] = GrauxO2.getcaT() / nmos2;
		vect_g[5] = GrauxO2.getNA() / nmos2;

		vect_g[6] = nmos1;
		vect_g[7] = nmos2;

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
