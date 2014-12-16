package DominiPKG;

import DominiPKG.ControladorsPKG.ControladorDomini;
import PermanenciaPKG.IOPKG.CtrlDominifichers;
import PresentacioPKG.ControladorsPKG.ControladorGrafic;

import java.io.FileNotFoundException;

/**
 * @author Marina Sauca
 */
public class main {
    static ControladorGrafic cGraf;
    static ControladorDomini cDom;

    public main (){
        cGraf = new ControladorGrafic();
        cDom = new ControladorDomini();
    }

    public static void main(String[] args) throws FileNotFoundException {
        int option = 0;
        while (option != 42){
            option = cGraf.introScene(); //ok
            switch (option){
                case 1: //input & solve
                    cDom = new ControladorDomini();
                    if(cGraf.inputScene(cDom)){ //ok
                        if(!cDom.generarPlanificacio()){
                            cDom.segonOrigen(cGraf.segonOrigen());//ok
                            if(!cDom.generarPlanificacio()){
                                cGraf.noSolution(); //ok
                                break;
                            }
                            else cGraf.veurePlanificacio(cDom, false);
                        }
                        else cGraf.veurePlanificacio(cDom, false);
                    }
                    break;
                case 2: //view existent
                    cGraf.veurePlanificacio(cDom, true);
                    //save planif? Y|N
                    break;
                case 3: //close problem
                    //save actual? Y|N
                    break;
                case 4: //exit


            }
        }

    }
}
