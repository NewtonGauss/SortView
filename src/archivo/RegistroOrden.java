package archivo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import gui.VistaArreglo.Orden;
import ordenamientos.Estrategia;

public class RegistroOrden {
	private Estrategia ordenamiento;
	private Orden condicionInicial;
	private int largoArreglo;
	private long tardanzaEnMs;
	private char separador = ';';

	public RegistroOrden(Estrategia ordenamiento, Orden condicionInicial,
			int largoArreglo, long tardanzaEnMs) {
		this.ordenamiento = ordenamiento;
		this.condicionInicial = condicionInicial;
		this.largoArreglo = largoArreglo;
		this.tardanzaEnMs = tardanzaEnMs;
	}

	public void setSeparador(char separador) { this.separador = separador; }

	@Override
	public String toString() {
		DecimalFormat df = inicializarFormateador();
		StringBuilder registro = new StringBuilder(
				ordenamiento.getClass().getSimpleName());
		registro.append(separador);
		registro.append(condicionInicial);
		registro.append(separador);
		registro.append(largoArreglo);
		registro.append(separador);
		registro.append(df.format((double) tardanzaEnMs / 1000));
		return registro.toString();
	}

	private DecimalFormat inicializarFormateador() {
		DecimalFormat df = new DecimalFormat("#.00");
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');
		df.setDecimalFormatSymbols(simbolos);
		return df;
	}

}
