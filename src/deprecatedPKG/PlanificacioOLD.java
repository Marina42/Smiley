package deprecatedPKG;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.SolucioPKG.Ruta;

import java.util.*;

/**
 * @author enric.fresco
 */
public class PlanificacioOLD {
    /*private int id;
    private int id_graf;
    private ArrayList<Integer> llista_agents_id;
    private HashMap rutes_assignades;
    private int tipus_cost_evaluat;
    private int algorisme;
    private int cost_temporal_total;
    private int cost_economic_total;
    private int flux_maxim;

    public Planificacio(){}

    public Planificacio(int id, int id_graf, ArrayList<Integer> llista_agents_id, int tipus_cost_evaluat, int algorisme){

        this.id = id;
        this.id_graf = id_graf;
        this.llista_agents_id = llista_agents_id;
        this.tipus_cost_evaluat = tipus_cost_evaluat;
        this.algorisme = algorisme;
        rutes_assignades = new HashMap();
    }

    public int getIdGraf(){
        return id_graf;
    }

    public int calcula_costT(ArrayList<Ruta> cng_ru){

        int costt = 0;

        for(int i = 0; i < cng_ru.size(); i++){
            costt += cng_ru.get(i).getcostT();
        }
        return costt;
    }

    public int calcula_costE(ArrayList<Ruta> cng_ru){

        int coste = 0;

        for(int i = 0; i < cng_ru.size(); i++){
            coste += cng_ru.get(i).getcostE();
        }
        return coste;
    }

    public HashMap assigna_rutes(ArrayList<Ruta> cng_ru, ArrayList<Integer> ll_ag){

        HashMap as_ru = new HashMap();

        for(int i = 0; i < ll_ag.size(); i++){
            as_ru.put(ll_ag.get(i), cng_ru.get(i).getId());
        }
        return as_ru;

    }

    public int genera_planificacio(Graf G, ArrayList<Ruta> cnj_rutes, int id_rut_o){

        int agents = llista_agents_id.size();

        ArrayList<Ruta> cnj_ru_aux = new ArrayList<Ruta>();

        if(algorisme == 0){

            Dikstra0n dk0 = new Dikstra0n();
            dk0.getResidual(G, cnj_ru_aux, tipus_cost_evaluat, id_rut_o);
        }

        else if(algorisme == 1){

            Dikstra dk = new Dikstra();
            dk.getResidual(G, cnj_ru_aux, tipus_cost_evaluat, id_rut_o);
        }


        if(cnj_ru_aux.size() >= agents){

            cost_temporal_total = calcula_costT(cnj_ru_aux);
            cost_economic_total = calcula_costE(cnj_ru_aux);
            flux_maxim = cnj_rutes.size();
            rutes_assignades = assigna_rutes(cnj_ru_aux, llista_agents_id);
            cnj_rutes = cnj_ru_aux;
        }


        else flux_maxim = -1;

        return flux_maxim;

    }

    public void escriptura_dades_pantalla(ArrayList<Ruta> cng_rut){

        if(flux_maxim == -1) System.out.print("Planificacio no viable \n");

        else{

            System.out.print("Dades de la planificacio: \n" +
                    "id planificacio: "+id+"\n"+
                    "id_graf: "+id_graf+ "\n" +
                    "tipus_cost_evaluat: "+tipus_cost_evaluat+"\n"+
                    "algorisme: "+algorisme+"\n"+
                    "cost_temporal_total: "+cost_temporal_total+"\n"+
                    "cost_economic_total: "+cost_economic_total+"\n"+
                    "flux_maxim: "+flux_maxim+"\n");

            Iterator<Integer> list_hash = rutes_assignades.keySet().iterator();
            while(list_hash.hasNext()){
                int auxit = list_hash.next();
                int idr = (Integer)rutes_assignades.get(auxit);

                System.out.print("Id Agent :"+auxit+ " ruta assignada a ell en la planificacio actual:\n");

                for(int i = 0; i < cng_rut.size(); i++)
                    if(cng_rut.get(i).getId() == idr)
                        cng_rut.get(i).escriu_ruta();

            }


        }

    }
*/
}
