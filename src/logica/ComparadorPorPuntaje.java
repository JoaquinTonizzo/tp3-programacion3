package logica;

import java.util.Comparator;

import logica.GrafoConPesosEnVertices.Vertice;

public class ComparadorPorPuntaje implements Comparator<Vertice> {

	@Override
	public int compare(Vertice uno, Vertice otro) {
		return (int) (otro.obtenerPuntaje() - uno.obtenerPuntaje());
	}

}
