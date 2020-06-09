package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import gui.Arreglo.Orden;
import ordenamientos.QuickSort;

@SuppressWarnings ("serial")
public class Ventana extends JFrame {

	private VistaOrdenamiento panelArreglo;

	public Ventana(VistaOrdenamiento panel) {
		panelArreglo = panel;
	}

	public static void main(String[] args) {
		OpcionesParser parser = new OpcionesParser(args);
		Arreglo arreglo = new Arreglo(new Integer[parser.getCantEl()]);
		VistaOrdenamiento panel = new VistaOrdenamiento(arreglo,
				parser.getEstrategia(), parser.getSleep(),
				parser.getCondicionInicial());
		Ventana gui = new Ventana(panel);
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
