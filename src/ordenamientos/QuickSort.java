package ordenamientos;

import gui.Arreglo;

public class QuickSort implements Estrategia {

	@Override
	public void ordenar(Arreglo arreglo) {
		ordenar(arreglo, 0, arreglo.length() - 1);
		arreglo.setParteOrdenada(0, arreglo.length() - 1);
	}

	private void ordenar(Arreglo arreglo, int inferior, int superior) {
		if (inferior < superior) {
			int pivot = partition(arreglo, inferior, superior);

			ordenar(arreglo, inferior, pivot - 1);
			arreglo.setParteOrdenada(0, pivot);
			ordenar(arreglo, pivot + 1, superior);
		}
	}

	private int partition(Arreglo arreglo, int inferior, int superior) {
		int pivot = superior;
		int i = (inferior - 1);
		for (int j = inferior; j < superior; j++) {
			if (arreglo.comparar(j, pivot) < 0) {
				i++;
				arreglo.intercambio(i, j);
			}
		}
		arreglo.intercambio(i + 1, superior);

		return i + 1;
	}

}
