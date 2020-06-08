package ordenamientos;

import gui.Arreglo;

public class Insercion implements Estrategia {

	@Override
	public void ordenar(Arreglo arreglo) {

		for (int i = 1; i < arreglo.length(); i++) {
			int valorActual = arreglo.getValor(i);
			int j = i - 1;
			while (j >= 0 && arreglo.compararValor(j, valorActual) > 0) {
				arreglo.insercion(j + 1, j);
				j = j - 1;
			}
			arreglo.setValor(j + 1, valorActual);
			arreglo.setParteOrdenada(0, i + 1);
		}

	}
}
