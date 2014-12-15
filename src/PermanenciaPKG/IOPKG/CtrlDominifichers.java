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

		//String pathabsl = "C:/Users/Enric/Documents/prov1/Smiley/src/Dades/";
		//String pathrel = filefolder;


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

		//String pathabsl = "C:/Users/Enric/Documents/prov1/Smiley/src/Dades/";
		//String pathrel = filefolder;

		//File tmp = new File("C:/Users/Enric/Documents/prov1/Smiley/src/Dades/" + filename);
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter("C:/Users/Enric/Documents/prov1/Smiley/src/Dades/" + filename);
			pw = new PrintWriter(fw);

			for (int i = 0; i < dades.size(); i++) {
				pw.println(dades.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fw)
					fw.close();
			}
			catch (Exception e1){
				e1.printStackTrace();
			}
		}

	}


}