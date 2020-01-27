package game;

import java.io.*;

public class WorldGen {

	/**
	 * not used
	 * would load maps.
	 * TODO: remove
	 * @param fPath isn't used
	 * @throws IOException
	 */
	public void OpenMap(String fPath) throws IOException {
		
		File file = new File(fPath); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String st; 
		while ((st = br.readLine()) != null) {
			System.out.println(st);
			String[] stDataSplit = st.split("\\|");
			if (stDataSplit[0].equals("p1")) {
				
			} else if (stDataSplit[0].equals("p2")) {
				
			} else if (stDataSplit[0].equals("p3")) {
				
			} else if (stDataSplit[0].equals("p4")) {
				
			} else if (stDataSplit[0].equals("wall")) {
				
			} else if (stDataSplit[0].equals("hospital")) {
				
			} else if (stDataSplit[0].equals("artilary")) {
				
			} else if (stDataSplit[0].equals("artilaryTarget")) {
				
			} else if (stDataSplit[0].equals("tree")) {
				
			}
		}
		br.close();
	}

}
