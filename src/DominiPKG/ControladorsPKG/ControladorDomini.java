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
public class ControladorDomini extends ControladorGraf  {
    private cjtAgents agents;
    private Planificacio planing;

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
     * Aquesta funcio crea una planificacio, els System.out.print serán subsituits per comunicació amb
     * el controlador grafic pertinent un cop aquest estigui definit.
     * @param algorisme identificador del algorisme (0 bfs, 1 dfs, 2 dijkstra)
     * @param Origen nom de la ciutat origen
     */
    public void crearPlanificacio(int algorisme, String Origen){
        planing = new Planificacio(algorisme, Origen);
        if(!planing.generarPlanificacio()){
            this.segonOrigen();
            if(!planing.generarPlanificacio()) System.out.println("no es pot generar una PLanificacio");
            else System.out.println("planificacio generada");
        }
        else System.out.println("planificacio generada");

    }

    /**
     * Funcio cridad quan no es posible generar una planificacio amb un unic origen i es necessari que l'usuari
     * especifiqui un segon origen. L'input i output seran serán subsituits per comunicació amb el controlador
     * grafic pertinent un cop aquest estigui definit.
     */
    private void segonOrigen (){
        Scanner input = new Scanner(System.in);
        System.out.println("please, input the name of the new Origin");
        planing.setOrigen2(input.next());
    }

    /**
     * retorna la Planificació per poderla representar graficament
     * @return planing Planificacio generada.
     */
    public Planificacio getPlanificacio(){
        return planing;
    }
}
