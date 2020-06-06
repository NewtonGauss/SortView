package archivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Archivo {
	private static final char separador = ',';

	public void grabarArchivoCsv(String path, RegistroOrden registro)
			throws IOException {
		FileWriter fw = abrirArchivo(path);
		BufferedWriter bw = new BufferedWriter(fw);
		registro.setSeparador(separador);
		bw.append(registro + "\n");
		bw.close();
	}

	private FileWriter abrirArchivo(String path) throws IOException {
		File file = new File(path);
		FileWriter fw;
		if (!file.exists()) {
			file.createNewFile();
			fw = new FileWriter(file);
			fw.write("Algoritmo" + separador + "Condicion" + separador
					+ "Cantidad de elementos" + separador + "Tiempo en segundos\n");
		} else
			fw = new FileWriter(file, true);
		return fw;
	}
}
