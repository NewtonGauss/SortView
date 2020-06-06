package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import gui.VistaArreglo.Orden;
import ordenamientos.MergeSort;

@SuppressWarnings ("serial")
public class Ventana extends JFrame {

	private VistaArreglo panelArreglo;

	public Ventana() {
		panelArreglo = new VistaArreglo(new MergeSort(), 10, Orden.ALEATORIO);
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

	public void run() { panelArreglo.run(); }

	@Override
	public Dimension getPreferredSize() { return new Dimension(800, 450); }

}
