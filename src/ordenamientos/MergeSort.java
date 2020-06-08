package ordenamientos;

import gui.Arreglo;

public class MergeSort implements Estrategia {

	@Override
	public void ordenar(Arreglo arreglo) {
		ordenar(arreglo, 0, arreglo.length() - 1);
		arreglo.setParteOrdenada(0, arreglo.length() - 1);
	}

	private void ordenar(Arreglo arreglo, int limiteIzq, int limiteDer) {
		if (limiteIzq < limiteDer) {
			int puntoMedio = (limiteIzq + limiteDer) / 2;

			ordenar(arreglo, limiteIzq, puntoMedio);
			ordenar(arreglo, puntoMedio + 1, limiteDer);

			merge(arreglo, limiteIzq, puntoMedio, limiteDer);
		}
	}

	private void merge(Arreglo arreglo, int limiteIzq, int puntoMedio,
			int limiteDer) {
		int n1 = puntoMedio - limiteIzq + 1;
		int n2 = limiteDer - puntoMedio;

		Integer[] arregloTemporalIzquierdo = new Integer[n1];
		Integer[] arregloTemporalDerecho = new Integer[n2];

		for (int i = 0; i < n1; ++i)
			arregloTemporalIzquierdo[i] = arreglo.getValor(limiteIzq + i);
		for (int j = 0; j < n2; ++j)
			arregloTemporalDerecho[j] = arreglo.getValor(puntoMedio + 1 + j);

		int i = 0, j = 0;

		int k = limiteIzq;
		while (i < n1 && j < n2) {
			Integer valIzq = arregloTemporalIzquierdo[i];
			Integer valDer = arregloTemporalDerecho[j];
			if (arreglo.compararEnteros(valIzq, valDer) < 0) {
				arreglo.setValor(k, valIzq);
				i++;
			} else {
				arreglo.setValor(k, valDer);
				j++;
			}
			k++;
			arreglo.setParteOrdenada(limiteIzq, k);
		}

		while (i < n1) {
			arreglo.setValor(k, arregloTemporalIzquierdo[i]);
			i++;
			k++;
			arreglo.setParteOrdenada(limiteIzq, k);
		}

		while (j < n2) {
			arreglo.setValor(k, arregloTemporalDerecho[j]);
			j++;
			k++;
			arreglo.setParteOrdenada(limiteIzq, k);
		}
	}

}
