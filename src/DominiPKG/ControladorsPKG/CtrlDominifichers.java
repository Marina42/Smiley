package DominiPKG.ControladorsPKG;

import java.util.*;


public class CtrlDominifichers{

	private static CtrlDominifichers singletonObject;

	public static CtrlDominifichers getInstance() {
			if (singletonObject == null)
			singletonObject = new CtrlAgentsfichers() {
			};
			return singletonObject;
		}

	private CtrlDominifichers(){}

	public HashMap<String, String> cargarAgentes(String filename)
		throws FileNotFoundException{

			FileReader fr=new FileReader("../"+filename);
			Scanner scan=new Scanner(fr);

			HashMap<String, String>agentss=new HashMap<String, String>();

			while(scan.hasNextLine()){

				String idagent=new String(scan.nextLine());
				String nomagent=new String(scan.nextLine());
				agentss.add(new Entry (idagent,nomagent));
			}
			return agentss;}

	public List<String> cargarcjtRutes(String filename)
			throws FileNotFoundException{

		FileReader fr=new FileReader("../"+filename);
		Scanner scan=new Scanner(fr);

		List<String> rutes =new HashMap<String, String>();

		while(scan.hasNextLine()){
			rutes.add(new String(scan.nextLine()));
		}
		return rutes;}

}
