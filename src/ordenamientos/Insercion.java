package ordenamientos;

import gui.VistaArreglo;

public class Insercion implements Estrategia {
	
	/*
	 * Por seguir implementando
	 * Solo hace una pasada.
	 */
	@Override
	public void ordenar(VistaArreglo arreglo) {
		
		for (int i = 1; i < arreglo.arregloLength(); i++) {
			int valorActual = arreglo.getValor(i);
			int j = i - 1;
			while (j >= 0 && arreglo.comparar(j, i) > 0) {
				arreglo.insercion(j+1, j);
			//	arreglo[j+1] = arreglo[j];

				j = j - 1;
			}
			arreglo.setValorInsercion(j+1, valorActual);
			//arreglo[j+1] = valorActual;

		}

	}
}