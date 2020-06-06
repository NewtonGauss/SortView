package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;

import archivo.Archivo;
import archivo.RegistroOrden;
import ordenamientos.Estrategia;

@SuppressWarnings ("serial")
public class VistaArreglo extends JPanel {
	private Integer[] arreglo = new Integer[100];
	private int indexActual = 0;
	private int indexCompara = -1;
	private int indexPrimerOrdenado = -1;
	private int indexUltimoOrdenado = -1;
	private int cantidadComparaciones = 0;
	private int cantidadIntercambios = 0;
	private long msIniciales;
	private long msAlMomento;
	private int tiempoSleep;
	private Estrategia estrategia;
	private Orden condicionInicial;
	private static final int anchoRect = 7;
	private static final int multAltoRect = 4;

	public VistaArreglo(Estrategia ordenamiento, int sleep, Orden orden) {
		setBackground(Color.DARK_GRAY);
		orden.inicializarArreglo(this);
		condicionInicial = orden;
		tiempoSleep = sleep;
		estrategia = ordenamiento;
	}

	public enum Orden {
		INVERSO {
			public void inicializarArreglo(VistaArreglo arreglo) {
				arreglo.ordenInverso();
			}
		},
		ALEATORIO {
			public void inicializarArreglo(VistaArreglo arreglo) {
				arreglo.ordenInverso();
				arreglo.shuffle(arreglo.arregloLength());
			}
		},
		CASI_ORDENADO {
			public void inicializarArreglo(VistaArreglo arreglo) {
				arreglo.ordenAscedente();
				arreglo.shuffle(arreglo.arregloLength() / 3);
			}
		},
		ORDENADO {
			public void inicializarArreglo(VistaArreglo arreglo) {
				arreglo.ordenAscedente();
			}
		};

		public abstract void inicializarArreglo(VistaArreglo arreglo);
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

	public void run() {
		paintImmediately(getBounds());
		msIniciales = System.currentTimeMillis();
		ordenarArreglo();
		grabarArchivo();
	}

	private void ordenarArreglo() {
		estrategia.ordenar(this);
		indexActual = -1;
		indexCompara = -1;
		actualizarPanel();
	}

	private void grabarArchivo() {
		RegistroOrden registro = new RegistroOrden(estrategia, condicionInicial,
				arregloLength(), msAlMomento);
		try {
			new Archivo().grabarArchivoCsv("resultado.csv", registro);
		} catch (IOException e) {
			System.err.println("No se pudo guardar el registro.");
		}
	}

	@Override
	protected void paintComponent(Graphics graphicsGenerico) {
		super.paintComponent(graphicsGenerico);
		Graphics2D graphics = (Graphics2D) graphicsGenerico;
		escribirDetalles(graphics);
		pintarArreglo(graphics);
	}

	private void escribirDetalles(Graphics2D graphics) {
		graphics.setFont(new Font("Dialog", Font.BOLD, 14));
		graphics.setColor(Color.yellow);
		graphics.drawString(cantidadComparaciones + " comparaciones", 10, 15);
		graphics.drawString(cantidadIntercambios + " intercambios", 10, 35);
		msAlMomento = System.currentTimeMillis() - msIniciales;
		graphics.drawString("Tiempo: " + String.format("%6s", msAlMomento) + " ms",
				10, 55);
	}

	private void pintarArreglo(Graphics2D graphics) {
		Dimension currentDimension = getRootPane().getSize();
		for (int i = 0; i < arreglo.length; i++) {
			int alto = arreglo[i] * multAltoRect;
			int xPos = (i + 8) * anchoRect;
			int yPos = (int) (currentDimension.getHeight()) - alto;
			setColorElemento(graphics, i);
			graphics.fillRect(xPos, yPos, anchoRect, alto);
			graphics.setColor(Color.GRAY);
			graphics.drawRect(xPos, yPos, anchoRect, alto);
		}
	}

	private void setColorElemento(Graphics2D graphics, int indexElemento) {
		if (indexElemento == indexActual)
			graphics.setColor(Color.RED);
		else if (indexElemento == indexCompara)
			graphics.setColor(Color.GREEN);
		else if (indexElemento >= indexPrimerOrdenado
				&& indexElemento <= indexUltimoOrdenado)
			graphics.setColor(Color.BLUE);
		else
			graphics.setColor(Color.WHITE);
	}

	public int arregloLength() { return arreglo.length; }

	public void intercambio(int indexActual, int indexIntercambio) {
		int temp = arreglo[indexActual];
		arreglo[indexActual] = arreglo[indexIntercambio];
		arreglo[indexIntercambio] = temp;
		
		this.indexActual = indexIntercambio;
		cantidadIntercambios++;
		actualizarPanel();
	}

	public void insercion(int indexI, int indexA) {
		arreglo[indexI] = arreglo[indexA];
		
		indexActual = indexI;
		actualizarPanel();
	}

	public void setValor(int index, int valor) {
		arreglo[index] = valor;
		
		indexActual = index;
		actualizarPanel();
	}

	private void actualizarPanel() {
		paintImmediately(getBounds());
		try {
			Thread.sleep(tiempoSleep);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public int comparar(int indexActual, int indexCompara) {
		this.indexActual = indexActual;
		this.indexCompara = indexCompara;
		this.cantidadComparaciones++;
		actualizarPanel();
		return arreglo[indexActual] - arreglo[indexCompara];
	}

	public int compararValor(int indexActual, Integer valor) {
		this.indexActual = indexActual;
		this.indexCompara = -1;
		this.cantidadComparaciones++;
		
		actualizarPanel();
		return arreglo[indexActual] - valor;
	}

	public int compararEnteros(Integer valorA, Integer valorB) {
		this.cantidadComparaciones++;
		return valorA.compareTo(valorB);
	}

	public Integer getValor(int index) { return arreglo[index]; }

	public void setParteOrdenada(int inicio, int fin) {
		indexPrimerOrdenado = inicio;
		indexUltimoOrdenado = fin;
	}

}
