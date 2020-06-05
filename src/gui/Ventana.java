package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings ("serial")
public class Ventana extends JFrame{
	
	VistaArreglo panelArreglo = new VistaArreglo();

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
	public Dimension getPreferredSize() { return new Dimension(800, 450); }
	
	public static void main(String[] args) {
		Ventana gui = new Ventana();
		gui.init();
		gui.run();
	}
	
}
