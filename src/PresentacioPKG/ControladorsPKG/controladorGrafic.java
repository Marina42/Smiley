package PresentacioPKG.ControladorsPKG;

import DominiPKG.ControladorsPKG.ControladorDomini;
import PermanenciaPKG.IOPKG.CtrlDominifichers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Marina Sauca
 */
public class ControladorGrafic {
    Scanner input;
    public ControladorGrafic(){
        input = new Scanner(System.in);
    }

    public boolean inputScene(ControladorDomini cDom){
        System.out.println("Please select if you want to build a new problem or load a pre-existent one\n"+
                "1- new\n"+
                "2- load\n");
        if (input.nextInt() == 2){
            try {
                cDom.guardaProblemaDomini(mostrarLlista(cDom.llegeixllistaarchpoblemes()));
            } catch (FileNotFoundException e) {
                System.out.println("no hi ha problemes a memoria, procedint a input manual");
                inputProblema(cDom);
            }
            System.out.println("loaded, do you want to resolve it?\n Y | N");
            if (input.next().equals("Y")||input.next().equals("y")) return true;
            else return false;
        }
        else inputProblema(cDom);
        System.out.println("input finished, do you want to resolve it?\n Y | N");
        if (input.next().equals("Y")||input.next().equals("y")) return true;
        else return false;
    }

    private void inputProblema(ControladorDomini cDom){
        System.out.println("Please, select if you want to input a new map or load one\n"+
                "1- new\n"+
                "2- load\n");
        if(input.nextInt() == 2){
            try{
                cDom.afegirMapa(mostrarLlista(cDom.llegeixllistafilesdeclasse(3)));
            } catch (FileNotFoundException e) {
                System.out.println("no hi ha mapes a memoria, procedint a input manual");
                inputMapa(cDom);
            }
        }
        else inputMapa(cDom);

        System.out.println("Please, select if you want to input a new set of Agents or load one\n"+
                "1- new\n"+
                "2- load\n");
        if(input.nextInt() == 2){
            try{
                cDom.afegirMapa(mostrarLlista(cDom.llegeixllistafilesdeclasse(1)));
            } catch (FileNotFoundException e) {
                System.out.println("no hi ha agents a memoria, procedint a input manual");
                inputAgents(cDom);
            }
        }
        else inputAgents(cDom);
    }

    private void inputMapa(ControladorDomini cDom){
        System.out.println("first, input how many cities will the system have:");
        int n = input.nextInt();
        System.out.println("now, input the names of the cities starting by the origin and ending by the destination");

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String aux;
        aux = input.next();
        map.put(aux, cDom.afegirVertex(aux));
        cDom.setInici(map.get(aux));

        for(int i =1; i < n-1; ++i){
            aux = input.next();
            map.put(aux, cDom.afegirVertex(aux));
        }
        aux = input.next();
        map.put(aux, cDom.afegirVertex(aux));
        cDom.setFi(map.get(aux));

        System.out.println("done, now input how many roads will the system have:");
        n = input.nextInt();
        System.out.println("and now input the roads between the cities in the following format:\n" +
                "startingCity endingCity cost");
        //(int cost, int capacitat, int id_vertex_inici, int id_vertex_final)
        String start, end;
        for(int i = 0; i < n; ++i){
            start = input.next();
            end = input.next();
            cDom.afegirAresta(input.nextInt(), 1, map.get(start), map.get(end));
        }
    }

    private void inputAgents(ControladorDomini cDom){
        System.out.println("How many agents will the system have?\n");
        int n = input.nextInt();
        System.out.println("please, input the Agents in the following format:\n" +
                "numericID name\n" +
                "while there is posible for two agents to have the same name, keep in mind the id must not repeat");
        HashMap<Integer, Integer> agents = new HashMap<>();
        int idAux;
        for (int i = 0; i < n; ++i){
            idAux = input.nextInt();
            if (!agents.containsKey(idAux)) {
                agents.put(idAux, 1);
                cDom.afegirAgent(idAux, input.next());
            }
            else {
                System.out.println("this agent is already on the system, enter a diferent id");
                --i;
            }
        }
    }

    public String segonOrigen() {
        System.out.println("Planificaction with only one meeting was impossible.\n" +
                "please enter the name of a second city to try ");
        return input.next();
    }

    public void noSolution() {
        System.out.println("There's no solution.");
    }

    public int introScene() {
        System.out.println("Please, select what you want to do:\n" +
                "1- input or load data and solve it\n"+
                "2- view existent planification.\n");
        return input.nextInt();
    }

    public void veurePlanificacio(ControladorDomini cDom, boolean memoria) {
        ArrayList<String> rutes;
        ArrayList<String> agents;
        ArrayList<String> planif;
        String planifFilename = "";
        if(memoria){
            try {
                planifFilename = mostrarLlista(cDom.llegeixllistafilesdeclasse(2))
                cDom.afegirPlaning(planifFilename);
            } catch (FileNotFoundException e) {
                System.out.println("no hi ha planificacions a memoria, abortant operació.");
                return;
            }
            cDom.afergirAgents(planifFilename);
        }
        agents = cDom.llegeixConjAgents();
        rutes = cDom.llegeixRutesposibles(1);
        planif = cDom.llegeixplaning();
        System.out.println("info de la planificacio:\n" +
                "ciutat origen: "+planif.get(0));
        if(!planif.get(1).equals("-1")){
            System.out.println("segon origen: "+planif.get(1));
        }
        System.out.println("algorisme usat: "+planif.get(2)+"\n" +
                "cost total: "+planif.get(3));
        int j = 0;
        for(int i = 0; i < agents.size(); ++i){
            System.out.println("id del agent: " + agents.get(i) + "\n" +
                    "nom del agent: " + agents.get(i++) + "\n" +
                    "**************ROUTE************");
            while(!rutes.get(j).equals("-2")){
                System.out.println("From city "+rutes.get(j+2)+" to city "+ rutes.get(j+3)+
                        " with cost "+rutes.get(j));
                j+=4;
            }
            System.out.println("" +
                    "***********END ROUTE***********");
        }

        if (!memoria)
    }

    public String mostrarLlista(ArrayList<String> list){
        System.out.println("Please, input the number of the file youu want to load:");
        for (int i=0, n=list.size(); i < n; i++)
            System.out.println(i +" - "+list.get(i));
        return list.get(input.nextInt());
    }

}
