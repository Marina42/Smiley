package deprecatedPKG;
import DominiPKG.PlanificacioPKG.Planificacio;

import java.util.Scanner;

/**
 * @author Marina Sauca
 * @version 0.1
 * @since 16.11.2014
 */
public class ControladorPlanificacio {
    Planificacio planing;
    public ControladorPlanificacio() {}

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