package gui;

import java.util.Random;

public class Arreglo {
	private Integer[] arreglo;
	private int indexActual = 0;
	private int indexCompara = -1;
	private int indexPrimerOrdenado = -1;
	private int indexUltimoOrdenado = -1;
	private int cantidadComparaciones = 0;
	private int cantidadIntercambios = 0;
	private VistaOrdenamiento panel;

	public Arreglo(Integer[] arreglo) {
		super();
		this.arreglo = arreglo;
	}

	public int getIndexActual() {
		return indexActual;
	}

	public void setIndexActual(int indexActual) {
		this.indexActual = indexActual;
	}

	public int getIndexCompara() {
		return indexCompara;
	}

	public void setIndexCompara(int indexCompara) {
		this.indexCompara = indexCompara;
	}

	public Integer[] getArreglo() {
		return arreglo;
	}

	public int getIndexPrimerOrdenado() {
		return indexPrimerOrdenado;
	}

	public int getIndexUltimoOrdenado() {
		return indexUltimoOrdenado;
	}

	public int getCantidadComparaciones() {
		return cantidadComparaciones;
	}

	public int getCantidadIntercambios() {
		return cantidadIntercambios;
	}

	public enum Orden {
		INVERSO {
			public void inicializarArreglo(Arreglo arreglo) {
				arreglo.ordenInverso();
			}
		},
		ALEATORIO {
			public void inicializarArreglo(Arreglo arreglo) {
				arreglo.ordenInverso();
				arreglo.shuffle(arreglo.length());
			}
		},
		CASI_ORDENADO {
			public void inicializarArreglo(Arreglo arreglo) {
				arreglo.ordenAscedente();
				arreglo.shuffle(arreglo.length() / 3);
			}
		},
		ORDENADO {
			public void inicializarArreglo(Arreglo arreglo) {
				arreglo.ordenAscedente();
			}
		};

		public abstract void inicializarArreglo(Arreglo arreglo);
	}

	private void ordenAscedente() {
		for (int i = 0; i < arreglo.length; i++)
			arreglo[i] = i + 1;
	}

	private void ordenInverso() {
		for (int i = 0; i < arreglo.length; i++)
			arreglo[i] = arreglo.length - i;
	}

	private void shuffle(int cantIntercambios) {
		Random rand = new Random();
		for (int i = 0; i < cantIntercambios; i++) {
			int indexSwap = rand.nextInt(arreglo.length - 1);
			int indexSwap2 = rand.nextInt(arreglo.length - 1);
			intercambio(indexSwap, indexSwap2);
		}
		cantidadIntercambios = 0;
	}

	public int comparar(int indexActual, int indexCompara) {
		this.indexActual = indexActual;
		this.indexCompara = indexCompara;
		this.cantidadComparaciones++;
		panel.actualizarPanel();
		return arreglo[indexActual] - arreglo[indexCompara];
	}

	public int compararValor(int indexActual, Integer valor) {
		this.indexActual = indexActual;
		this.indexCompara = -1;
		this.cantidadComparaciones++;

		panel.actualizarPanel();
		return arreglo[indexActual] - valor;
	}

	public int compararEnteros(Integer valorA, Integer valorB) {
		this.cantidadComparaciones++;
		return valorA.compareTo(valorB);
	}

	public Integer getValor(int index) {
		return arreglo[index];
	}

	public void setParteOrdenada(int inicio, int fin) {
		indexPrimerOrdenado = inicio;
		indexUltimoOrdenado = fin;
	}

	public int length() {
		return arreglo.length;
	}

	public void intercambio(int indexActual, int indexIntercambio) {
		int temp = arreglo[indexActual];
		arreglo[indexActual] = arreglo[indexIntercambio];
		arreglo[indexIntercambio] = temp;

		this.indexActual = indexIntercambio;
		cantidadIntercambios++;
		panel.actualizarPanel();
	}

	public void insercion(int indexI, int indexA) {
		arreglo[indexI] = arreglo[indexA];

		indexActual = indexI;
		panel.actualizarPanel();
	}

	public void setValor(int index, int valor) {
		arreglo[index] = valor;

		indexActual = index;
		panel.actualizarPanel();
	}

	public void setPanel(VistaOrdenamiento panel) {
		this.panel = panel;
	}
}
