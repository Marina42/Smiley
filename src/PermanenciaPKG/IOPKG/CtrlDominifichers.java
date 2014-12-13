package PermanenciaPKG.IOPKG;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class CtrlDominifichers{

	private static CtrlDominifichers singletonObject;

	public static CtrlDominifichers getInstance() {
			if (singletonObject == null)
			singletonObject = new CtrlDominifichers() {
			};
			return singletonObject;
		}

	private CtrlDominifichers(){}

	public ArrayList<String> cargardades(String filename)
			throws FileNotFoundException{

		FileReader fr=new FileReader("../"+filename);
		Scanner scan=new Scanner(fr);

		ArrayList<String> rutes = new ArrayList<String>();

		while(scan.hasNextLine()){
			rutes.add(new String(scan.nextLine()));
		}
		return rutes;}

}
