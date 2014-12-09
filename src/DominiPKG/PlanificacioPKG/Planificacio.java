package DominiPKG.PlanificacioPKG;

import DominiPKG.AgentPKG.Agent;
import DominiPKG.SolucioPKG.Ruta;

import java.util.HashMap;

/**
 * Created by Mirshi on 09/12/14.
 * TODO
 */
public class Planificacio {
    private HashMap<Agent, Ruta> cjtRutes;
    private String Origen;
    private String Origen2;
    private int algUsat;
    private int costTotal;

    public Planificacio(int algorisme, String Origen){
        algUsat = algorisme;
        this.Origen = Origen;
        cjtRutes = new HashMap<>();
        Origen2 = "";
    }

    public boolean generarPlanificacio(){
        return true;
    }

    public void setOrigen2(String origen2){}

}
