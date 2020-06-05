package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;

import ordenamientos.Burbujeo;
import ordenamientos.Estrategia;
import ordenamientos.Seleccion;
import javax.swing.Timer;
@SuppressWarnings ("serial")
public class VistaArreglo extends JPanel{
	private Integer[] arreglo = new Integer[100];
	private int indexActual = 0;
	private int indexCompara = 0;
	private int cantidadComparaciones=0;
	private int cantidadIntercambios=0;
	private int msTotales=0;
	private static final int anchoRect = 7;
	private static final int multAltoRect = 4;

	public VistaArreglo() {
		setBackground(Color.DARK_GRAY);
		ordenInverso();
		shuffle();
		cantidadIntercambios=0;
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
		Estrategia estrategia = new Seleccion();
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
		graphics.drawString("Tiempo: "+String.format("%6s", msTotales/1000)+" ms", 10, 55);//no estoy orgulloso de esto. Voy a buscar una mejor forma. Dani. 
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
		cantidadIntercambios++;
		update();
	}

	private void update() {
		int tiempoSleep=30;
		paintImmediately(getBounds());
		try {
			Thread.sleep(tiempoSleep);
			msTotales+=tiempoSleep;
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
	
	public Integer getValor(int index) {
		return arreglo[index];
	}

}
