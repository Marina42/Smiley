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
public class ControladorDomini extends ControladorGraf {

	private CtrlDominifichers ControlFichers;
	private cjtAgents agents;
	private Planificacio planing;
	private Solucio sol1;
	private ArrayList<Grafics> datos_grafs;   // posiciones 0 = 1 Origen 1 = 2 Origenes, 3 = Bfs, 4 = Dfs, 5 = Dikjstra
	private ArrayList<String> nomfilesactuals;


	public ControladorDomini() {
		super();
		ControlFichers = ControlFichers.getInstance();
		agents = new cjtAgents();
		datos_grafs = new ArrayList<Grafics>();
		datos_grafs.add(new Grafics());
		datos_grafs.add(new Grafics());
		planing = new Planificacio();
		agents = new cjtAgents();
		nomfilesactuals = new ArrayList<String>();
		for (int i = 0; i < 3; i++) nomfilesactuals.add(i, "buit");
	}

	//funcio privada auxiliar retorna un vertex segons el nom

	private Vertex getVertex(String nom) {
		ArrayList<Integer> Valaux = new ArrayList<Integer>();
		Valaux = getGraf().getVertexs();
		Vertex vaux = new Vertex();

		for (int i = 0; i < Valaux.size(); i++) {
			vaux = getGraf().getVertex(Valaux.get(i));
			if (vaux.getNom().equals(nom)) return vaux;
			vaux = new Vertex();
		}
		return vaux;
	}

	//et retorna el nom del cnjagents associat a la planificacio nom = nomfile

	public String nomProblemaassociat(String nomfile)
		throws FileNotFoundException {
			try {
				ArrayList<String> regprob;
				regprob = ControlFichers.cargardades("registrafiles.txt");
				int aux = 0;
				for (int i = 0; i< regprob.size(); i++){
					if(regprob.get(i).equals(nomfile)){
						aux = i-1;
						break;
					}
				}
				return regprob.get(aux);

			}
			catch (FileNotFoundException e){
				System.err.println("El fichero .txt no existe.");
				return null;
			}
		}


	public void guardaProblemaDomini(String nomProb){
		try {
			guardarAgents();
			guardarPlanificacio();
			guardarMapa();

			ArrayList<String> dadesreg = new ArrayList<String>();
			dadesreg.add(nomProb);
			dadesreg.addAll(nomfilesactuals);
			ControlFichers.escriuredades(dadesreg, "registrafiles");

			dadesreg = new ArrayList<String>();
			dadesreg.add(nomfilesactuals.get(0));
			ControlFichers.escriuredades(dadesreg, "regAgents.txt");

			dadesreg = new ArrayList<String>();
			dadesreg.add(nomfilesactuals.get(1));
			ControlFichers.escriuredades(dadesreg, "regPlanificacions.txt");

			dadesreg = new ArrayList<String>();
			dadesreg.add(nomfilesactuals.get(2));
			ControlFichers.escriuredades(dadesreg, "regMapes.txt");
			}
		catch (Exception e){
			System.err.println("El fichero .txt no existe.");

		}

	}

	public ArrayList<String> llegeixllistaarchpoblemes() {

		try {
			ArrayList<String> llistaprob = new ArrayList<String>();
			llistaprob = ControlFichers.cargardades("regproblemes.txt");
			return llistaprob;
		} catch (Exception e) {
			System.err.println("El fichero .txt no existe.");
			return null;
		}

	}

	/**
	 * retorna la llista dels files de cada classe
	 * p = 1 Agents
	 * 2 Planificacions
	 * 3 Mapes
	 */

	public ArrayList<String> carregaProblema(String filename) {
		try {
			ArrayList<String> llistaprob = new ArrayList<String>();
			llistaprob = ControlFichers.cargardades("regproblemes.txt");
			int aux = 0;
			for (int i = 0; i < llistaprob.size(); i++) {
				if (llistaprob.get(i).equals(filename)){
					aux = i;
					break;
				}
			}
			afegiexfileadomini(1, llistaprob.get(aux + 1));
			afegiexfileadomini(2, llistaprob.get(aux + 2));
			afegiexfileadomini(3, llistaprob.get(aux + 3));

			return llistaprob;
		}

		catch (Exception e) {
			System.err.println("El fichero .txt no existe.");
			return null;
		}
	}


	/**
	 * retorna la llista dels files de cada classe
	 * p = 1 Agents
	 * 		2 Planificacions
	 * 		3 Mapes
	 */

	public ArrayList<String> llegeixllistafilesdeclasse(int p){

		try {
			ArrayList<String> llistaprob = new ArrayList<String>();
			if(p == 1) {
				llistaprob = ControlFichers.cargardades("/Agents/regAgents.txt");
				return llistaprob;
			}
			else if(p==2){
				llistaprob = ControlFichers.cargardades("/Planificacions/regPlanificacions.txt");
				return llistaprob;
			}
			else{
				llistaprob = ControlFichers.cargardades("/Mapes/regMapes.txt");
				return llistaprob;
			}

		}
		catch (Exception e){
			System.err.println("El fichero .txt no existe.");
			return null;
		}

	}

	public ArrayList<String> afegiexfileadomini(int tipus, String nomfile){
		try {

			if (tipus == 1) {
				return afegirConjAgents("/Agents/" + nomfile);

			} else if (tipus == 2) {
				return afegirPlaning("/Planificacions/" + nomfile);
			}
			else return afegirMapa("/Mapes/" + nomfile);
		}
		catch (Exception e){
			System.err.println("El fichero .txt no existe.");
			return null;
		}
	}

	public int segonOrigen (String nom2){

		if(getVertex(nom2) != null	) {

			Vertex vaux = getVertex(nom2);

			planing.setOrigen2(vaux);
			planing.setNumOrigens(2);
			Vertex vauxin = new Vertex();
			vauxin.setNom("nodeaux");

			getGraf().afegirVertex(vauxin);

			int numaux = (int)(agents.getNumAgents() / 2);
			int numaux1 = (int)(agents.getNumAgents() % 2);

			afegirAresta(0, numaux, vauxin.getId(), planing.getOrigen().getId() );
			afegirAresta(0, numaux + numaux1, vauxin.getId(), vaux.getId() );

			getGraf().setInici(vauxin.getId());
			return 0;
		}
		else return -1;
	}

	//resets de les dades

	public void resetDomini(){
		agents = new cjtAgents();
		planing = new Planificacio();
		ResetGraf();
		sol1 = new Solucio();
	}

	public void resetejar_mostres(){

		Grafics Graux = new Grafics();
		datos_grafs.set(0, Graux);
		datos_grafs.set(1, Graux);
	}




	//Lectura de dades de archius, sense control de errors

	//lectura de les rutes posibles


	//Lectura dels Agents
   public ArrayList<String> afegirConjAgents(String filename)
	   throws FileNotFoundException {
	   agents = new cjtAgents();
	   int i = 0;
	   ArrayList<String> cnjagents = ControlFichers.cargardades(filename);

	   while (i < cnjagents.size()) {
		   agents.addAgent(Integer.parseInt(cnjagents.get(i)), cnjagents.get(i+1) );
		   i = i+2;
	   }
	   nomfilesactuals.set(0, filename);
	   return cnjagents;
   }

	//Lectura de una planificacio de un archiu
	public ArrayList<String> afegirPlaning(String filename)
			throws FileNotFoundException {
		planing = new Planificacio();
		int i = 0;
		ArrayList<String> plan = ControlFichers.cargardades(filename);
		String aux;
		if(filename.isEmpty()) return plan;

			planing.setOrigen(getVertex(plan.get(i)));
			aux = plan.get(i+1);
			if(!aux.equals( "-1")) planing.setOrigen2(getVertex(plan.get(i + 1)));

			planing.setAlgUsat(Integer.parseInt(plan.get(i+2)));
			planing.setCostTotal(Integer.parseInt(plan.get(i + 3)));
			planing.setcapTotal(Integer.parseInt(plan.get(i+4)));
			planing.setNumOrigens(Integer.parseInt(plan.get(i+5)));
			planing.setResolt(Integer.parseInt(plan.get(i+6)));

		int resolt = Integer.parseInt(plan.get(i+6));
		i = 6;
		if(resolt == 1) {

			while (i < plan.size()) {
				int control = Integer.parseInt(plan.get(i));
				Aresta araux;
				Ruta raux = null;

				while (control != -2) {
					araux = new Aresta(control, Integer.parseInt(plan.get(i + 1)), Integer.parseInt(plan.get(i + 2)), Integer.parseInt(plan.get(i + 3)));
					i += 4;
					control = Integer.parseInt(plan.get(i));
					raux.afegirAresta(araux);
				}
				i++;
				sol1.afegirRuta(raux);
			}
		}
		nomfilesactuals.set(1, filename);
		return plan;
	}

	//Lectura de una planificacio de memoria
	public ArrayList<String> llegeixplaning(){
		ArrayList<String> plan = new ArrayList<String>();

		plan.add(planing.getOrigen().getNom());
		if(planing.getNumOrigens() == 2) plan.add(planing.getOrigen2().getNom());
		else plan.add("-1");
		plan.add(Integer.toString(planing.getAlg()));
		plan.add(Integer.toString(planing.getcostTotal()));
		plan.add(Integer.toString(planing.getcapTotal()));
		plan.add(Integer.toString(planing.getNumOrigens()));
		plan.add(Integer.toString(planing.getResolt()));

		return plan;
	}

	//Lectura del Mapa

	public ArrayList<String> afegirMapa(String filename)
			throws FileNotFoundException {
		int i = 0;
		ArrayList<String> mapastring = ControlFichers.cargardades(filename);
		String control = new String();
		control = mapastring.get(i);

		while (! control.equals("fi")) {
			int id = afegirVertex(control);
			i++;
			control = mapastring.get(i);
		}
		i++;

		while(i < mapastring.size()){

			afegirAresta(Integer.parseInt(mapastring.get(i)), Integer.parseInt(mapastring.get(i + 1)), Integer.parseInt(mapastring.get(i + 2)), Integer.parseInt(mapastring.get(i + 3)));
			i = i+4;
		}
		nomfilesactuals.set(2, filename);
		return  mapastring;
	}


	//Des per enviar a presentacio format Array de Strings o per enviar a persistencia x guardar no es contemplen errors


	/**
	 * Pre: cnjAgents.size > 1
	 * Return: les dades dels agents id , nom de cnjagents, si null no hi han agents en memoria
	 */
	public ArrayList<String> llegeixConjAgents()
	{
		if(agents.getNumAgents() < 1) return null;

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
	 * Pre: Cal que les nombre de mostres de 1 i 2 Origens >= 1
	 * Return: les dades feta la mitjana dels grups amb 1 origen i de 2 origens
	 * 			null nombre de mostres de 1 o 2 inferiors a 1
	 * 			not null llista de dades feta la mitjana per cada grup, en format string
	 */

	public ArrayList<String> llegeix_comparacio_mostres(){
		Grafics GrauxO1 = datos_grafs.get(0);
		Grafics GrauxO2 = datos_grafs.get(1);

		ArrayList<String> vect_g = new ArrayList<>();
		int nmos1, nmos2;

		nmos1 = GrauxO1.getNM();
		nmos2 = GrauxO2.getNM();

		if(nmos1 > 0 && nmos2 > 0){

			vect_g.add( Integer.toString(GrauxO1.getcsT() / nmos1)) ; //mitj costos plans de 1 Origen
			vect_g.add( Integer.toString(GrauxO1.getcaT() / nmos1)); //mitj capacitat plans de 1 Origen
			vect_g.add( Integer.toString(GrauxO1.getNA() / nmos1)); //mitj numero agents plans de 1 Origen
			vect_g.add( Integer.toString(nmos1));					//nombre de mostres amb 1 origen

			vect_g.add(  Integer.toString( GrauxO2.getcsT() / nmos2)); // igual que adal pero de mostres de 2 Origens
			vect_g.add(  Integer.toString(GrauxO2.getcaT() / nmos2));
			vect_g.add(	 Integer.toString(GrauxO2.getNA() / nmos2));
			vect_g.add(  Integer.toString(nmos2));

			return vect_g;
		}
		return null;
	}

	/**
	 * Pre: Mapa valid
	 * Return: Les dades rellevants del mapa
	 */

	public ArrayList<String> llegeixMapa() {

		ArrayList<String> Mapa = new ArrayList<String>();

		ArrayList<Integer> alaux = getGraf().getVertexs();
		ArrayList<Integer> alarestes = new ArrayList<Integer>();
		Vertex vaux;

		for (int i = 0; i < alaux.size(); i++){
			vaux = getGraf().getVertex(alaux.get(i));
			Mapa.add(vaux.getNom());
			alarestes.addAll(getAdjacentsDestins(vaux.getId()));
		}
		Mapa.add("fi");
		Aresta aaux;
		for (int i = 0; i < alarestes.size(); i++){
			aaux = getGraf().getAresta(alarestes.get(i));
			Mapa.add(Integer.toString(aaux.getCost() ) );
			Mapa.add(Integer.toString(aaux.getCapacitat() ) );
			Mapa.add(Integer.toString(aaux.getId_vertex_original() ) );
			Mapa.add(Integer.toString(aaux.getId_vertex_adjunt() ) );
		}

		return Mapa;
	}

	/**
	 * Pre: Solucio valida
	 * Return: Les dades rellevants de solucio, si mod == 1 retorna les rutes assignades als agents posiscions paraleles del cnj agents i cnj rutes
	 * si mod == 0 retorna totes les rutes posibles que a trobat lalgorisme en el mapa
	 */

	public ArrayList<String> llegeixRutesposibles(int mod) {

		ArrayList<String> Rutesstring = new ArrayList<String>();
		ArrayList<Ruta> Rutes;
		ArrayList<Aresta> ArestesR;
		Rutes = sol1.getLlistaRutas();
		Ruta raux;
		Aresta aaux;
		int modaux;

		if(mod == 1) modaux = agents.getNumAgents();
		else modaux = Rutes.size();

		for (int i = 0; i < modaux; i++){
			raux = Rutes.get(i);
			ArestesR = raux.getListaArestas();
			for (int j = 0; j < ArestesR.size(); j++) {
				aaux = ArestesR.get(j);
				Rutesstring.add(Integer.toString(aaux.getCost()));
				Rutesstring.add(Integer.toString(aaux.getCapacitat()));
				Rutesstring.add(Integer.toString(aaux.getId_vertex_original()));
				Rutesstring.add(Integer.toString(aaux.getId_vertex_adjunt()));
			}
			Rutesstring.add("-2");

		}
		return Rutesstring;

	}


	//metodes per escriure dades correctes de les clases al seus files respectius

	public String guardarMapa(){

		ArrayList<String> dades = llegeixMapa();
		try {
			String nommap = new String();

			if(nomfilesactuals.get(2).equals("buit")) {
				nommap = ControlFichers.creanoufile(3, "Mapa", "/Mapes/");
			}
			else{
				nommap = nomfilesactuals.get(2);
			}

			ControlFichers.escriuredades(dades, nommap);
			nomfilesactuals.set(3, nommap);
			return nommap;
		}

		catch (Exception e){
			System.err.println("El fichero .txt no existe.");
			return "error fichero";
		}

	}

	public String guardarAgents(){

		ArrayList<String> dades = llegeixConjAgents();
		try {
			String nomAg = new String();

			if(nomfilesactuals.get(0).equals("buit")) {
				nomAg = ControlFichers.creanoufile(0, "Agents", "/Agents/");
			}
			else{
				nomAg = nomfilesactuals.get(0);
			}

			ControlFichers.escriuredades(dades, nomAg);
			nomfilesactuals.set(0, nomAg);
			return nomAg;
		}
		catch (Exception e){
			return null;
		}
	}

	public void guardardadesGrafics(String nomcas){

		ArrayList<String> dades = llegeix_comparacio_mostres();
		try {
			ControlFichers.escriuredades(dades, nomcas);
		}
		catch (Exception e){
		}
	}

	public String guardarPlanificacio(){

		ArrayList<String> dades = llegeixplaning();
		dades.addAll(llegeixRutesposibles(0));

		try {
			String nomAg = new String();
			if(nomfilesactuals.get(3).equals("buit")) {
				nomAg = ControlFichers.creanoufile(1, "Planificacio", "/Planificacions/");
			}
			else{
				nomAg = nomfilesactuals.get(3);
			}
			ControlFichers.escriuredades(dades, nomAg);
			nomfilesactuals.set(1, nomAg);
			return nomAg;
		}

		catch (Exception e){
			return null;
		}
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
     * @	Return: 1 no hi ha cap ruta valida
	 * 				0 hi ha alguna ruta valida
     */


	public int generarSolucio(){

		sol1 = new Solucio();
		Graf g1 = getGraf();

		MaxFlow m = new MaxFlow();
		ArrayList<HashMap> cjt_cami = new ArrayList<HashMap>();

		Graf residual = m.getResidual(g1, planing.getAlg(),cjt_cami);

		if(cjt_cami.size() == 0) return 1;

		for(int i=0;i<cjt_cami.size();++i){

			ArrayList<Aresta>arestas_ruta =  new ArrayList<Aresta>();
			Iterator it = cjt_cami.get(i).entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry ar = (Map.Entry)it.next();

				if(-2 != (int)ar.getValue()) arestas_ruta.add(getGraf().getAresta((int) ar.getValue(), (int) ar.getKey()));
			}
			Ruta r = new Ruta(arestas_ruta);
			sol1.afegirRuta(r);
		}
		return 0;

	}
    /**
     * Resolucio de planificacions
     * @Generem la solucio sol1, amb un o laltre funcio en funcio del nombre de Origens 1 o 2, calculem que cumpleix les pautes definides, si es que no returnem false
     * @SI es que si cumpleix returnem true, en el cas de true  rellenem el cost total del planing, resolt pasa a ser 1 i assignem per cada agent una Ruta començant per la primera (en dikstra aquesta sera de millo calitat mes a menys nan de dalt a baix, en 
     */

	public boolean generarPlanificacio(){
					//si generem planificacio  es reseteja la solucio sol1, i el hashmap de agents i rutes en el cas que hi hagues algo
		ArrayList<Ruta> cnjRt;
		int costt;

		int err = generarSolucio();
		if(err == 1) return false;

			cnjRt = sol1.getLlistaRutas();
			if(agents.getNumAgents() > cnjRt.size()) return false;	

			else{
				costt = 0;
				for(int i = 0; i < agents.getNumAgents(); i++){
					ArrayList<Aresta> alaux = cnjRt.get(i).getListaArestas();
					for(int j = 0; j < alaux.size(); j++){
						costt += alaux.get(j).getCost();
					}
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
	 *      Returns: 0 planing ja esta resolt
	 *               1 planificacio generada correctament amb 1 Origen
	 *               2 no es pot generar una PLanificacio ni amb 2 Origens
	 *               3 planificacio generada correctament usant 2 Origens
	 *               4 planificacio fallida amb 1 origen
	 *               -1 Algorisme no valid
	 *               -2 vertex Origen no existeix
	 *               -3 vertex Origen2 no existeix
     */
    public int crearPlanificacioiresoldre(int algorisme, String Origen, String Origen2, int norg){

		if(algorisme < 1 || algorisme > 3) return -1;
		if(getVertex(Origen) == null) return -2;
	//	if(getVertex(origen2) == null) return -3;

		getGraf().setInici(getVertex(Origen).getId());
		getGraf().setFi(getVertex("Berlin").getId());

		if(norg == 2){ segonOrigen(Origen2);}

		planing = new Planificacio(algorisme, getVertex(Origen));

		if(planing.getResolt() == 0){

			if(!generarPlanificacio()){

				if(planing.getNumOrigens() == 2) return 2;
				return 4;
				}
			 else {

				if(planing.getNumOrigens() == 2) return 3;

				return 1;
			}
		}
		else return 0;
    }

/*
    public void modificarPlanificacio(){ si volem modificar una planificacio simplemen en crearem un altre
*/
    /**
     * Funcio cridad quan no es posible generar una planificacio amb un unic origen i es necessari que l'usuari
     * especifiqui un segon origen. L'input i output seran serán subsituits per comunicació amb el controlador
     * grafic pertinent un cop aquest estigui definit.
	 * si return 0 tot ok, si return -1 el vertex no existeix
     */




    /**
     * Funcions per gestionar les dades per generar grafics
     */
	/**
	 * Afegeix el plan actual a les dades per fer grafics
	 * Return: 0 is ok, -1 el plan que es vol afegir no esta resolt
	 */

	public int afegir_plan_resolt(){
	
	if(planing.getResolt() == 1){

		int opt = 0;
		if(planing.getNumOrigens() == 1) opt = 0; 
		if(planing.getNumOrigens() == 2) opt = 1;

		datos_grafs.get(opt).setcaT(planing.getcapTotal() + datos_grafs.get(opt).getcaT());
		datos_grafs.get(opt).setcst(planing.getcostTotal() + datos_grafs.get(opt).getcsT());
		datos_grafs.get(opt).setNA(agents.getNumAgents() + datos_grafs.get(opt).getNA());
		datos_grafs.get(opt).setNM(datos_grafs.get(opt).getNM() + 1);

		return 0;
	}
		else return -1;
   }





//MODIficacio, eliminacio, lectura, creacio es fa via Controlador Graf






}
