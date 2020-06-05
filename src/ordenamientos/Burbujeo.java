package ordenamientos;

import gui.VistaArreglo;

public class Burbujeo implements Estrategia {

	@Override
	public void ordenar(VistaArreglo arreglo) {
		int len = arreglo.arregloLength();
    for (int i = 0; i < len - 1; i++) {
        for (int j = 0; j < len - i - 1; j++) {
            if (arreglo.comparar(j, j + 1) > 0) {
                arreglo.intercambio(j, j + 1);
            }
        }
    }
	}

}
