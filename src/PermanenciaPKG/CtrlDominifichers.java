

import java.util.*

/**
 * 
 * 
 */

public class CtrlDominifichers(){

	public CtrlDominifichers(){}
	
	public HashMap<Integer,String> cargarAgentes(String filename){

		FileReader fr = new FileReader("./"+filename);
		Scanner scan = new Scanner(fr);
		int idage;

		HashMap<Integer,String> agentss = new HashMap<Integer,String>();

		while (scan.hasNextLine()){

			String idagent = new String(scan.nextLine());
			String nomagent = new String(scan.nextLine());
			idage =  Integer.parseInt(idagent);
	
			agentss.add(idage,nomagent);
			
		}

		return agentss;



	}




}
