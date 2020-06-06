package archivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class Archivo {
	public void grabarArchivoCsv(String path, String registro) {
		try {
			String encabezado = null;
			File file = new File(path);
			FileWriter fw;
			if (!file.exists()) {
				file.createNewFile();
				encabezado = "Algoritmo;Condición;Elementos;Tiempo en segundos";
				// Instanciar referencia de FileWriter
				fw = new FileWriter(file);
			}else
			{
				// Instanciar referencia de FileWriter (con append=true para que agregue al final).
				fw = new FileWriter(file,true);
			}
			BufferedWriter bw = new BufferedWriter(fw);
			if (encabezado != null) {
				bw.write(encabezado+"\n");
			}
			bw.append(registro+"\n");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
