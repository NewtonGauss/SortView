package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JPanel;

import archivo.Archivo;
import archivo.RegistroOrden;
import gui.Arreglo.Orden;
import ordenamientos.Estrategia;

@SuppressWarnings ("serial")
public class VistaOrdenamiento extends JPanel {
	Arreglo arreglo;
	private long msIniciales;
	private long msAlMomento;
	private int tiempoSleep;
	private Estrategia estrategia;
	private Orden condicionInicial;
	private static  int anchoRect = 7;
	private static  int multAltoRect = 4;

	public VistaOrdenamiento(Arreglo arreglo, Estrategia ordenamiento, int sleep,
			Orden orden) {
		this.arreglo = arreglo;
		this.arreglo.setPanel(this);
		setBackground(Color.DARK_GRAY);
		Dimension tamVentana = getSize();
		anchoRect = tamVentana.width/arreglo.length();
		orden.inicializarArreglo(arreglo);
		condicionInicial = orden;
		tiempoSleep = sleep;
		estrategia = ordenamiento;
	}

	public void run() {
		paintImmediately(getBounds());
		msIniciales = System.currentTimeMillis();
		ordenarArreglo();
		grabarArchivo();
	}

	private void ordenarArreglo() {
		estrategia.ordenar(arreglo);
		arreglo.setIndexActual(-1);
		arreglo.setIndexCompara(-1);
		actualizarPanel();
	}

	private void grabarArchivo() {
		RegistroOrden registro = new RegistroOrden(estrategia, condicionInicial,
				arreglo.length(), msAlMomento);
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
		graphics.drawString(arreglo.getCantidadComparaciones() + " comparaciones",
				10, 15);
		graphics.drawString(arreglo.getCantidadIntercambios() + " intercambios", 10,
				35);
		msAlMomento = System.currentTimeMillis() - msIniciales;
		graphics.drawString("Tiempo: " + String.format("%6s", msAlMomento) + " ms",
				10, 55);
	}

	private void pintarArreglo(Graphics2D graphics) {
		Dimension currentDimension = getRootPane().getSize();
		anchoRect = (int) ((int) (currentDimension.width/(arreglo.length())));
		multAltoRect = (int) ((currentDimension.height)/(arreglo.length()));
		for (int i = 0; i < arreglo.length(); i++) {
			int alto = arreglo.getValor(i) * multAltoRect;
			int xPos = (i + 2) * anchoRect;
			int yPos = (int) (currentDimension.getHeight()) - alto;
			setColorElemento(graphics, i);
			graphics.fillRect(xPos, yPos, anchoRect, alto);
			graphics.setColor(Color.GRAY);
			graphics.drawRect(xPos, yPos, anchoRect, alto);
		}
	}

	private void setColorElemento(Graphics2D graphics, int indexElemento) {
		if (indexElemento == arreglo.getIndexActual())
			graphics.setColor(Color.RED);
		else if (indexElemento == arreglo.getIndexCompara())
			graphics.setColor(Color.GREEN);
		else if (indexElemento >= arreglo.getIndexPrimerOrdenado()
				&& indexElemento <= arreglo.getIndexUltimoOrdenado())
			graphics.setColor(Color.BLUE);
		else
			graphics.setColor(Color.WHITE);
	}

	public void actualizarPanel() {
		paintImmediately(getBounds());
		try {
			Thread.sleep(tiempoSleep);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}
