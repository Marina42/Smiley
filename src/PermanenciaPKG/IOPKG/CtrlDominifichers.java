package PermanenciaPKG.IOPKG;

import java.io.FileWriter;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;


public class CtrlDominifichers {

	private static CtrlDominifichers singletonObject;

	public static CtrlDominifichers getInstance() {
		if (singletonObject == null)
			singletonObject = new CtrlDominifichers() {
			};
		return singletonObject;
	}

	private CtrlDominifichers() {
	}

	public ArrayList<String> cargardades(String filename)
			throws FileNotFoundException {

		FileReader fr = new FileReader("C:/Users/Enric/Documents/prov1/Smiley/src/Dades/" + filename);
		Scanner scan = new Scanner(fr);

		ArrayList<String> rutes = new ArrayList<String>();

		while (scan.hasNextLine()) {
			rutes.add(new String(scan.nextLine()));
		}
		return rutes;
	}


	public void escriuredades(ArrayList<String> dades, String filename)
			throws FileNotFoundException {

		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter("C:/Users/Enric/Documents/prov1/Smiley/src/Dades/" + filename);

			if(filename.equals("registrafiles") || filename.equals("regAgents") || filename.equals("regPlanificacions") || filename.equals("regMapes")) fw = new FileWriter("C:/Users/Enric/Documents/prov1/Smiley/src/Dades/" + filename, true);

			pw = new PrintWriter(fw);
			for (int i = 0; i < dades.size(); i++) {
				pw.println(dades.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != fw)
					fw.close();
			}
			catch (Exception e1){
				e1.printStackTrace();
			}
		}

	}


/*
* Genera un nou file, dacord amb el tipus, usan un contador en un archiu i retorna el seu nom
* tipus = 0 Agents
* 		1 cnjRutes
* 		2 DadesGrafics
		3 Mapes
		4 Planificacions


* */

	public String creanoufile(int tipus, String nomtipus, String carpeta)
			throws FileNotFoundException {

		try {
			ArrayList<String> dadesreg = cargardades("registracontadors");
			int contador = Integer.parseInt(dadesreg.get(tipus));
			File tmp = new File("C:/Users/Enric/Documents/prov1/Smiley/src/Dades/" + carpeta + nomtipus + contador );

			String nomret = new String(carpeta + nomtipus + Integer.toString(contador));

			contador++;
			dadesreg.set(tipus, Integer.toString(contador));
			escriuredades(dadesreg, "registracontadors");
			return nomret;
		}

		catch (Exception e){
			System.out.println("no hi ha problemes a memoria, procedint a input manual");
			return null;
		}
	}

}