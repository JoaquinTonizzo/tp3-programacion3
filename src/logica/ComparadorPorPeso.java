package logica;

import java.util.Comparator;

import logica.GrafoConPesosEnVertices.Vertice;

public class ComparadorPorPeso implements Comparator<Vertice> {

	@Override
	public int compare(Vertice uno, Vertice otro) {
		int ret = (int) (otro.obtenerPeso() - uno.obtenerPeso());
		if (ret == 0) {
			ret = otro.obtenerCantidadVecinos() - uno.obtenerCantidadVecinos();
		}
		return ret;
	}

}
