package DominiPKG.AlgorismePKG;

import DominiPKG.GrafPKG.Aresta;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.GrafPKG.Vertex;

import java.util.*;  //para usar Array List, pila i Vector

/**
 * @author enric.fresco
 */

public class Dikstra extends Algorisme{

    public Dikstra(){}

    public int tip_cost(Aresta a1, int tip){

        if(tip == 1) return a1.getcostT();
        else return a1.getcostE();
    }

    public int trobaCami(Graf G, HashMap P, int tipus){

        int dim = G.getDimensio();
        int[] Capacitats = new int[dim];
        int[] costos = new int[dim];
        for(int i = 0; i< G.getDimensio(); i++){
            Capacitats[i] = -1 ;
            costos[i] = 0;
        }
        int Flux, Cap, Ida, Idv, Idvi, Control, vcont, contador, auxcost;
        contador = 0;
        Idv = 0;
        Ida = 2;
        Aresta a = new Aresta();
        Vertex aux = new Vertex();
        aux = G.getInici();
        Cap = 0;


        ArrayList<Integer> al_aresta_cos = new ArrayList<Integer>();
        al_aresta_cos.addAll(aux.getDestins());

        while(contador < al_aresta_cos.size()){

            Ida = al_aresta_cos.get(contador);
            a = G.getAresta(Ida);
            Cap =  a.getCapacitat();
            Idv = a.getId_vertex_adjunt();
            Idvi = a.getId_vertex_original();
            aux = G.getVertex(Idv);

            if(Idvi == G.getInici().getId()) Flux = a.getCapacitat();

            else Flux = Capacitats[Idvi];

            auxcost = costos[Idvi] + tip_cost(a, tipus);


            if (Cap >=  Flux && Capacitats[Idv]  <= Flux ) {

                if(Capacitats[Idv] == Flux && costos[Idv] > auxcost) costos[Idv] = auxcost;
                else if(Capacitats[Idv] != Flux) costos[Idv] = auxcost;

                Capacitats[Idv] = Flux;
                al_aresta_cos.addAll(aux.getDestins());
            }
            else if(Cap <  Flux && Capacitats[Idv]  <= Cap){

                if(Capacitats[Idv] == Cap && costos[Idv] > auxcost) costos[Idv] = auxcost;
                else if(Capacitats[Idv] != Cap) costos[Idv] = auxcost;

                Capacitats[Idv] = Cap;
                al_aresta_cos.addAll(aux.getDestins());
            }
            contador++;
        }

        Control = 0;

        aux = G.getInici();
        vcont = aux.getId();

        aux = G.getFi();
        Cap = Capacitats[aux.getId()];

        Idvi = aux.getId();

        Capacitats[vcont] = Cap;


        if (Cap > 0){									//si cap < 0 no definim el Path

            contador = 0;
            al_aresta_cos.clear();
            al_aresta_cos.addAll(aux.getOrigens());

            P.put(vcont, costos[Idvi] );

            while (vcont != aux.getId()){		//mentre l'ultim vertex visitat no sigui el d'inici do while

                while ( Control == 0 ){
                    //mentre no aguem trobat el millor cami (vertex desti millor) seguim miran les arestes

                    a = G.getAresta(al_aresta_cos.get(contador));

                    if ( a.getCapacitat() >= Cap && Capacitats[a.getId_vertex_original()] >= Cap){

                        if(costos[a.getId_vertex_original()] == costos[a.getId_vertex_adjunt()] - tip_cost(a, tipus))
                        {
                            Idv = a.getId_vertex_original();
                            P.put(Idvi, Idv);
                            Idvi = Idv;
                            Control = 1;
                        }
                    }
                    contador++;


                }

                al_aresta_cos.clear();
                contador = 0;
                aux = G.getVertex(Idv);
                al_aresta_cos.addAll(aux.getOrigens());//agafem el conjunt d'arestes del vertex seguent def anteriorment
                Control = 0;
            }

        }

        return Cap;									//retornem la Cap del cam escollit
    }

}





