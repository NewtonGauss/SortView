package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

import ordenamientos.Estrategia;

import ordenamientos.Insercion;
import ordenamientos.QuickSort;
import ordenamientos.Seleccion;
import archivo.Archivo;
import ordenamientos.ShellSort;

import javax.swing.Timer;
@SuppressWarnings ("serial")
public class VistaArreglo extends JPanel{
	private Integer[] arreglo = new Integer[100];
	private int indexActual = 0;
	private int indexCompara = -1;
	private int cantidadComparaciones=0;
	private int cantidadIntercambios=0;
	private long msIniciales;
	private int indexPrimerOrdenado = -1;
	private int indexUltimoOrdenado = -1;
	private long msAlMomento;
	

	private static final int anchoRect = 7;
	private static final int multAltoRect = 4;
	private int tiempoSleep;
	private Estrategia estrategia;

	public VistaArreglo(Estrategia ordenamiento, int sleep, int orden) {
		setBackground(Color.DARK_GRAY);
		switch (orden) {
		case 1:
			ordenInverso();
			break;
		case 2:
			ordenInverso();
			shuffle();
			break;
		case 3:
		default:
			ordenAscedente();
			break;
		}
		tiempoSleep = sleep;
		estrategia = ordenamiento;
	}

	private void ordenAscedente() {
		for (int i = 0; i < arreglo.length; i++)
			arreglo[i] = i + 1;
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
    cantidadIntercambios = 0;
}
	
	public void run() {
		paintImmediately(getBounds());
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
		graphics.setFont(new Font("Dialog", Font.BOLD, 14));
		graphics.setColor(Color.yellow);
		graphics.drawString(cantidadComparaciones+ " comparaciones", 10, 15);
		graphics.drawString(cantidadIntercambios+ " intercambios", 10, 35);
		msAlMomento=System.currentTimeMillis()- msIniciales;
		graphics.drawString("Tiempo: "+String.format("%6s",msAlMomento)+" ms", 10, 55);//
		for (int i = 0; i < arreglo.length; i++) {
			int alto = arreglo[i] * multAltoRect;
			int xPos = (i + 8) * anchoRect;
			int yPos = (int) (currentDimension.getHeight()) - alto;
			if (i == indexActual)
				graphics.setColor(Color.RED);
			else if (i == indexCompara)
				graphics.setColor(Color.GREEN);
			else if (i >= indexPrimerOrdenado && i <= indexUltimoOrdenado)
				graphics.setColor(Color.BLUE);
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
		cantidadIntercambios++;
		update();
	}
	
	public void setMsIniciales(long msIniciales) {
		this.msIniciales = msIniciales;
	}
	
	public void insercion(int indexI, int indexA) {
		arreglo[indexI] = arreglo[indexA];
		indexActual = indexI;
		update();
	}
	
	public void setValorInsercion(int index, int valor) {
		arreglo[index] = valor;
		indexActual = index;
		update();
	}
	
	private void update() {
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
		update();
		return arreglo[indexActual] - arreglo[indexCompara];
	}
	
	public int compararValor(int indexActual, Integer valor) {
		this.indexActual = indexActual;
		this.indexCompara = -1;
		this.cantidadComparaciones++;
		update();
		return arreglo[indexActual] - valor;
	}
	
	public int compararEnteros(Integer valorA, Integer valorB) {
		this.cantidadComparaciones++;
		return valorA.compareTo(valorB);
	}
	
	public Integer getValor(int index) {
		return arreglo[index];
	}
	
	public void updateOrdanados(int inicio, int fin) {
		indexPrimerOrdenado = inicio;
		indexUltimoOrdenado = fin;
	}

}
