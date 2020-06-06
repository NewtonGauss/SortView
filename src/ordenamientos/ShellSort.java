package ordenamientos;

import gui.VistaArreglo;

public class ShellSort implements Estrategia {

	@Override
	public void ordenar(VistaArreglo arreglo) {
		int n = arreglo.arregloLength();

		for (int espacio = n / 2; espacio > 0; espacio /= 2) {
			for (int i = espacio; i < n; i += 1) {
				Integer temporal = arreglo.getValor(i);
				int j;
				for (j = i; j >= espacio && arreglo.compararValor(j - espacio, temporal) > 0; j -= espacio)
					arreglo.insercion(j, j - espacio);
				arreglo.setValorInsercion(j, temporal);
			}
		}
	}

}
