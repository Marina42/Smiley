package DominiPKG.AlgorismePKG;

import DominiPKG.GrafPKG.Aresta;
import DominiPKG.GrafPKG.Graf;
import DominiPKG.GrafPKG.Vertex;
/**
 *
 * @author Enric Fresco
 */

import java.util.*;  //para usar Array List, pila i Vector


public class Dijkstra implements Algorisme {

    public Dijkstra(){}

    public int trobaCami(Graf G, HashMap P){

        int dim = G.getDimensio();
        int[] Capacitats = new int[dim];
        int[] costos = new int[dim];
        ArrayList<Integer> l_vertex = new ArrayList<Integer>();
        l_vertex.addAll(G.getVertexs());

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

            else Flux = Capacitats[l_vertex.indexOf(Idvi)];

            auxcost = costos[l_vertex.indexOf(Idvi)] + a.getCost();


            if (Cap >=  Flux && Capacitats[l_vertex.indexOf(Idv)]  <= Flux ) {

                if(Capacitats[l_vertex.indexOf(Idv)] == Flux && costos[l_vertex.indexOf(Idv)] > auxcost) costos[l_vertex.indexOf(Idv)] = auxcost;
                else if(Capacitats[l_vertex.indexOf(Idv)] != Flux) costos[l_vertex.indexOf(Idv)] = auxcost;

                Capacitats[l_vertex.indexOf(Idv)] = Flux;
                al_aresta_cos.addAll(aux.getDestins());
            }
            else if(Cap <  Flux && Capacitats[l_vertex.indexOf(Idv)]  <= Cap){

                if(Capacitats[l_vertex.indexOf(Idv)] == Cap && costos[l_vertex.indexOf(Idv)] > auxcost) costos[l_vertex.indexOf(Idv)] = auxcost;
                else if(Capacitats[l_vertex.indexOf(Idv)] != Cap) costos[l_vertex.indexOf(Idv)] = auxcost;

                Capacitats[l_vertex.indexOf(Idv)] = Cap;
                al_aresta_cos.addAll(aux.getDestins());
            }
            contador++;
        }

        Control = 0;

        aux = G.getInici();
        vcont = aux.getId();

        aux = G.getFi();
        Cap = Capacitats[l_vertex.indexOf(aux.getId())];

        Idvi = aux.getId();

        Capacitats[l_vertex.indexOf(vcont)] = Cap;


        if (Cap > 0){									//si cap < 0 no definim el Path

            contador = 0;
            al_aresta_cos.clear();
            al_aresta_cos.addAll(aux.getOrigens());

            P.put(vcont, costos[l_vertex.indexOf(Idvi)] );

            while (vcont != aux.getId()){		//mentre l'ultim vertex visitat no sigui el d'inici do while

                while ( Control == 0 ){
                    //mentre no aguem trobat el millor cami (vertex desti millor) seguim miran les arestes

                    a = G.getAresta(al_aresta_cos.get(contador));

                    if ( a.getCapacitat() >= Cap && Capacitats[l_vertex.indexOf(a.getId_vertex_original())] >= Cap){

                        if(costos[l_vertex.indexOf(a.getId_vertex_original())] == costos[l_vertex.indexOf(a.getId_vertex_adjunt())] - a.getCost())
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
