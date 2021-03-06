package ordenamientos;

import gui.Arreglo;

public class ShellSort implements Estrategia {

	@Override
	public void ordenar(Arreglo arreglo) {
		int n = arreglo.length();

		for (int espacio = n / 2; espacio > 0; espacio /= 2) {
			for (int i = espacio; i < n; i += 1) {
				Integer temporal = arreglo.getValor(i);
				int j;
				for (
						j = i; j >= espacio && arreglo.compararValor(j - espacio,
								temporal) > 0; j -= espacio
				)
					arreglo.insercion(j, j - espacio);
				arreglo.setValor(j, temporal);
				if (espacio / 2 <= 0)
					arreglo.setParteOrdenada(0, i);
			}
		}
	}

}
