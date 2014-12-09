package deprecatedPKG;

import DominiPKG.AgentPKG.Agent;
import DominiPKG.AgentPKG.cjtAgents;

/**
 * @author Marina Sauca
 * @version 0.1
 * @since 13.11.2014
 */

public class ControladorAgents {
    private cjtAgents agents;

    /**
     * Constructora de la clase
     */
    public ControladorAgents(){
        agents = new cjtAgents();
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
}