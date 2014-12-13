package DominiPKG.ControladorsPKG;

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

	public List<String> cargardades(String filename)
			throws FileNotFoundException{

		FileReader fr=new FileReader("../"+filename);
		Scanner scan=new Scanner(fr);

		List<String> rutes =new HashMap<String, String>();

		while(scan.hasNextLine()){
			rutes.add(new String(scan.nextLine()));
		}
		return rutes;}

}
