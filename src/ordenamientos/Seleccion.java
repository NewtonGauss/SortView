package ordenamientos;

import gui.VistaArreglo;

public class Seleccion implements Estrategia {

	@Override
	public void ordenar(VistaArreglo arreglo) {
		int i, j, indexMenor;
		int len = arreglo.arregloLength();
		
		for (i = 0; i < len - 1; i++) {
			indexMenor = i;
			for (j = i + 1; j < len; j++) {
				if (arreglo.comparar(j, indexMenor) < 0)
					indexMenor = j;
			}
				arreglo.intercambio(i, indexMenor);
				arreglo.updateOrdanados(0, i);
		}
		arreglo.updateOrdanados(0, len - 1);
	}

}
