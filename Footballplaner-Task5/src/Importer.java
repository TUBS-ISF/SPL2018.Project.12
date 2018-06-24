import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.IOException; 

import com.google.gson.Gson; 


public  class  Importer {
	
public static Gameplaner importfromJson() {
		
		String json=null;
		FileReader fR=null;
		Gameplaner gp =null;
		try {
			fR = new FileReader("export.json");
		}catch(FileNotFoundException fE) {
			System.err.println("File not found");
		}
		BufferedReader bR = new BufferedReader(fR);
		try {
			StringBuilder sB = new StringBuilder();
			String line = bR.readLine();
			
			while(line!=null) {
				sB.append(line);
				sB.append(System.lineSeparator());
				line =bR.readLine();
			}
			json = sB.toString();

			bR.close();
		}catch(IOException e) {
			System.err.println("Error reading File");
		}
		if(json!=null) {
		Gson g = new Gson();
		//java.lang.reflect.Type gameplanerType= new TypeToken<Gameplaner>() {}.getType();
		gp=g.fromJson(json, Gameplaner.class);
		}else {
			return null;
		}
		return gp;
	}


}
