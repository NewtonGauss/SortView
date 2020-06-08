package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import gui.Arreglo.Orden;
import ordenamientos.QuickSort;

@SuppressWarnings ("serial")
public class Ventana extends JFrame {

	private VistaOrdenamiento panelArreglo;

	public Ventana() {
		Integer[] arregloInteger = new Integer[100];
		Arreglo arreglo = new Arreglo(arregloInteger);
		panelArreglo = new VistaOrdenamiento(arreglo, new QuickSort(), 10,
				Orden.ALEATORIO);
	}

	public static void main(String[] args) {
		Ventana gui = new Ventana();
		gui.init();
		gui.run();
	}

	public void init() {
		add(panelArreglo);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();
	}

	public void run() {
		panelArreglo.run();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 450);
	}

}
