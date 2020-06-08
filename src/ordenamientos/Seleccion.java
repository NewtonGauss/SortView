package ordenamientos;

import gui.Arreglo;

public class Seleccion implements Estrategia {

	@Override
	public void ordenar(Arreglo arreglo) {
		int i, j, indexMenor;
		int len = arreglo.length();

		for (i = 0; i < len - 1; i++) {
			indexMenor = i;
			for (j = i + 1; j < len; j++) {
				if (arreglo.comparar(j, indexMenor) < 0)
					indexMenor = j;
			}
			arreglo.intercambio(i, indexMenor);
			arreglo.setParteOrdenada(0, i);
		}
		arreglo.setParteOrdenada(0, len - 1);
	}

}
