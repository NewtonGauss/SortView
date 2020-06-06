package ordenamientos;

import gui.VistaArreglo;

public class Burbujeo implements Estrategia {

	@Override
	public void ordenar(VistaArreglo arreglo) {
		boolean huboCambios = false;
		int len = arreglo.arregloLength();
    for (int i = 0; i < len - 1; i++) {
        for (int j = 0; j < len - i - 1; j++) {
            if (arreglo.comparar(j, j + 1) > 0) {
                arreglo.intercambio(j, j + 1);
                huboCambios = true;
            }
        }
        arreglo.updateOrdanados(len - 1 - i, len - 1);
        if (!huboCambios)
        	break;
    }
    arreglo.updateOrdanados(0, len - 1);
	}

}
