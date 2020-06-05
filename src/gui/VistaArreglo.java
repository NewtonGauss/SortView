package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

import ordenamientos.Burbujeo;
import ordenamientos.Estrategia;
import ordenamientos.Insercion;
import ordenamientos.QuickSort;
import ordenamientos.Seleccion;

@SuppressWarnings ("serial")
public class VistaArreglo extends JPanel{
	private Integer[] arreglo = new Integer[100];
//	private Integer[] arreglo = {3,1,2,5,9,4,6};
	private int indexActual = 0;
	private int indexCompara = 0;
	private static final int anchoRect = 7;
	private static final int multAltoRect = 4;

	public VistaArreglo() {
		setBackground(Color.DARK_GRAY);
		ordenInverso();
		shuffle();
	}

	private void ordenInverso() {
		for (int i = 0; i < arreglo.length; i++)
			arreglo[i] = arreglo.length - i;
	}
	
	public void shuffle() {
    Random rand = new Random();
    for (int i = 0; i < arreglo.length; i++) {
        int indexSwap = rand.nextInt(arreglo.length - 1);
        intercambio(i, indexSwap);
    }
}

	public void run() {
		paintImmediately(getBounds());
//		Estrategia estrategia = new Burbujeo();
//		Estrategia estrategia = new Seleccion();
		Estrategia estrategia = new Insercion();
//		Estrategia estrategia = new QuickSort();
		estrategia.ordenar(this);
		indexActual = -1;
		indexCompara = -1;
		update();
	}

	@Override
	protected void paintComponent(Graphics graphicsGenerico) {
		super.paintComponent(graphicsGenerico);
		Graphics2D graphics = (Graphics2D) graphicsGenerico;

		Dimension currentDimension = getRootPane().getSize();

		for (int i = 0; i < arreglo.length; i++) {
			int alto = arreglo[i] * multAltoRect;
			int xPos = (i + 8) * anchoRect;
			int yPos = (int) (currentDimension.getHeight()) - alto;
			if (i == indexActual)
				graphics.setColor(Color.RED);
			else if (i == indexCompara)
				graphics.setColor(Color.GREEN);
			else
				graphics.setColor(Color.WHITE);
			graphics.fillRect(xPos, yPos, anchoRect, alto);
			graphics.setColor(Color.GRAY);
			graphics.drawRect(xPos, yPos, anchoRect, alto);
		}

	}

	public int arregloLength() { return arreglo.length; }

	public void intercambio(int indexActual, int indexIntercambio) {
		int temp = arreglo[indexActual];
		arreglo[indexActual] = arreglo[indexIntercambio];
		arreglo[indexIntercambio] = temp;
		this.indexActual = indexIntercambio;
		update();
	}

	
	public void insercion(int indexI, int indexA) {
		arreglo[indexI] = arreglo[indexA];
		indexActual = indexA-1;
		update();
	}
	
	public void setValorInsercion(int index, int valor) {
		arreglo[index] = valor;
		update();
	}
	
	private void update() {
		paintImmediately(getBounds());
		try {
			Thread.sleep(5);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public int comparar(int indexActual, int indexCompara) {
		this.indexActual = indexActual;
		this.indexCompara = indexCompara;
		update();
		return arreglo[indexActual] - arreglo[indexCompara];
	}
	
	public Integer getValor(int index) {
		return arreglo[index];
	}

}
