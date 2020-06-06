package ordenamientos;

import gui.VistaArreglo;

public class MergeSort implements Estrategia {

	@Override
	public void ordenar(VistaArreglo arreglo) {
		ordenar(arreglo, 0, arreglo.arregloLength() - 1);
		arreglo.updateOrdanados(0, arreglo.arregloLength() - 1);
	}

	private void ordenar(VistaArreglo arreglo, int limiteIzq, int limiteDer) {
		if (limiteIzq < limiteDer) {
			int puntoMedio = (limiteIzq + limiteDer) / 2;

			ordenar(arreglo, limiteIzq, puntoMedio);
			ordenar(arreglo, puntoMedio + 1, limiteDer);

			merge(arreglo, limiteIzq, puntoMedio, limiteDer);
		}
	}

	private void merge(VistaArreglo arreglo, int limiteIzq, int puntoMedio,
			int limiteDer) {
		int n1 = puntoMedio - limiteIzq + 1;
		int n2 = limiteDer - puntoMedio;

		Integer[] arregloTemporalIzquierdo = new Integer[n1];
		Integer[] arregloTemporalDerecho= new Integer[n2];

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
				arreglo.setValorInsercion(k, valIzq);
				i++;
			} else {
				arreglo.setValorInsercion(k, valDer);
				j++;
			}
			k++;
			arreglo.updateOrdanados(limiteIzq, k);
		}

		while (i < n1) {
			arreglo.setValorInsercion(k, arregloTemporalIzquierdo[i]);
			i++;
			k++;
			arreglo.updateOrdanados(limiteIzq, k);
		}

		while (j < n2) {
			arreglo.setValorInsercion(k, arregloTemporalDerecho[j]);
			j++;
			k++;
			arreglo.updateOrdanados(limiteIzq, k);
		}
	}

}
