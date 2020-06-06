package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import ordenamientos.Burbujeo;
import ordenamientos.Insercion;
import ordenamientos.MergeSort;
import ordenamientos.QuickSort;
import ordenamientos.Seleccion;
import ordenamientos.ShellSort;

@SuppressWarnings ("serial")
public class Ventana extends JFrame{
	
	private VistaArreglo panelArreglo;
	
	public Ventana() {
		panelArreglo = new VistaArreglo(new Seleccion(), 10, 2);
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
		panelArreglo.setMsIniciales(System.currentTimeMillis());
		panelArreglo.run();
	}
	
	@Override
	public Dimension getPreferredSize() { return new Dimension(800, 450); }
	
	public static void main(String[] args) {
		Ventana gui = new Ventana();
		gui.init();
		gui.run();
	}
	
}
