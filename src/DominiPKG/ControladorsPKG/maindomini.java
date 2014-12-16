package DominiPKG.ControladorsPKG;
import DominiPKG.ControladorsPKG.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Enric on 14/12/2014.
 */
public class maindomini {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        ControladorDomini Prv2 = new ControladorDomini();
        try {
            Prv2.afegirConjAgents("/Agents/AgentsC0.txt");

        }
        catch (FileNotFoundException e) {
            System.err.println("El fichero .txt no existe.");
            return;
        }

        try {
            Prv2.afegirMapa("/Mapes/MapaC2.txt");
        }
        catch (FileNotFoundException e) {
            System.err.println("El fichero .txt no existe.");
            return;
        }



       int erra =  Prv2.crearPlanificacioiresoldre(3,"ciutat1", "ciutat2", 1 );


        ArrayList<String> alaux;
        alaux = Prv2.llegeixRutesposibles(0);

        System.out.println("prova "+alaux.size()+"  "+erra+"  \n");

        for(int i = 0; i < alaux.size(); i = i+4) {
            if(alaux.get(i).equals("-2")){
                System.out.println("\n"); i++ ;
            }
            if(i+4 < alaux.size()) System.out.println("Ruta: " + alaux.get(i) + " " + alaux.get(i + 1) + " "+alaux.get(i + 2) + " "+alaux.get(i + 3)+"\n");
        }

        alaux = new ArrayList<String>();

        alaux =  Prv2.llegeixMapa();

        System.out.println("Prova resetGraf "+Prv2.getGraf().getVertexs().size()+"\n");

        int j = 0;
        while(! alaux.get(j).equals("fi")){
            System.out.println("Vertexs: "+alaux.get(j)+"\n");
            j++;
        }
        for(int i = j+1; i < alaux.size(); i = i+4) {
            System.out.println("Arestes: " + alaux.get(i) + " " + alaux.get(i + 1) + " "+alaux.get(i + 2) + " "+alaux.get(i + 3)+"\n");
        }

       // Prv2.guardarMapa("Mapap1.txt");
       // Prv2.guardarRutes("Rutes.txt");


        alaux = new ArrayList<String>();
        alaux = Prv2.llegeixllistaarchpoblemes();
        for(int i = 0; i < alaux.size(); i = i+3) {
            System.out.println("Pack : " + alaux.get(i) + "  " + alaux.get(i + 1) +"  "+ alaux.get(i + 2)+ "\n");
        }
        alaux = new ArrayList<String>();
        alaux = Prv2.llegeixllistafilesdeclasse(3);
        for(int i = 0; i < alaux.size();i++) {
            System.out.println("LLista files classe paramaetre pasat : " + alaux.get(i) +"\n");
        }

        Prv2.afegir_plan_resolt();

        Prv2.afegirMapa("/Mapes/MapaC2.txt");



        System.out.println("prova "+alaux.size()+"  "+Prv2.getGraf().getVertexs().size()+"  \n");

        erra =  Prv2.crearPlanificacioiresoldre(3,"ciutat1", "ciutat2", 2 );

        alaux =  Prv2.llegeixMapa();

        j = 0;
        while(! alaux.get(j).equals("fi")){
            System.out.println("Vertexs: "+alaux.get(j)+"\n");
            j++;
        }
        for(int i = j+1; i < alaux.size(); i = i+4) {
            System.out.println("Arestes: " + alaux.get(i) + " " + alaux.get(i + 1) + " " + alaux.get(i + 2) + " " + alaux.get(i + 3) + "\n");
        }


        Prv2.afegir_plan_resolt();

        alaux = Prv2.llegeix_comparacio_mostres();

        for(int i = 0; i < 2; i++) {
            System.out.println("Dades comparacio Grafics : " + alaux.get(i) + "  " + alaux.get(i + 1) +"  "+ alaux.get(i + 2) + alaux.get(i + 3)+"\n");
        }


    }

}
