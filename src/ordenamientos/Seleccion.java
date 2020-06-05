package ordenamientos;

import gui.VistaArreglo;

public class Seleccion implements Estrategia {

	@Override
	public void ordenar(VistaArreglo arreglo) {
		int i, j, indexMenor;
		
		for (i = 0; i < arreglo.arregloLength() - 1; i++) {
			indexMenor = i;
			for (j = i + 1; j < arreglo.arregloLength(); j++) {
				if (arreglo.comparar(j, indexMenor) < 0)
					indexMenor = j;
			}
				arreglo.intercambio(i, indexMenor);
		}
	}

}
