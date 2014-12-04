package DominiPKG.AgentPKG;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Marina Sauca
 * @version 0.1
 * @since 4.12.2014
 */
public class cjtAgents {
    ArrayList<Agent> agents;
    public cjtAgents(){
        agents = new ArrayList<Agent>();
    }

    public void addAgent(int id, String nom) {
        agents.add(new Agent(id,nom));
    }

    public void deleteAgent(int id) {
        if (!agents.isEmpty()) {
            Iterator<Agent> it = agents.iterator();
            while (it.hasNext())
                if (id == it.next().getId()) {
                    it.remove();
                }
        }
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    public int getNumAgents() {
        return agents.size();
    }

    public Agent getAgent(int id) {
        Agent a = new Agent();
        for (Agent b : agents) {
            if (a.getId() == id){
                return b;
            }
        }
        return a;
    }

}
