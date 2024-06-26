package logica;

import java.util.Comparator;

import logica.GrafoConPesosEnVertices.Vertice;

public class ComparadorPorCantidadVecinos implements Comparator<Vertice> {

	@Override
	public int compare(Vertice uno, Vertice otro) {
		int ret = otro.obtenerCantidadVecinos() - uno.obtenerCantidadVecinos();
		if (ret == 0) {
			ret = (int) (otro.obtenerPeso() - uno.obtenerPeso());
		}
		return ret;
	}

}
