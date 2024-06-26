package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import logica.GrafoConPesosEnVertices.Vertice;

public class Solver {
	private GrafoConPesosEnVertices grafo;
	private Comparator<Vertice> comparador;

	public Solver(GrafoConPesosEnVertices instancia, Comparator<Vertice> comparador) {
		this.grafo = instancia;
		this.comparador = comparador;
	}

	public Solucion resolver() {
		Solucion solucion = new Solucion();
		
		for (int indiceVertice : obtenerCliquePesoMaximo()) {
	        solucion.agregarVertice(indiceVertice, grafo.obtenerPesoVertice(indiceVertice));
		}
		return solucion;
	}
	
	private Set<Integer> obtenerCliquePesoMaximo() {
		Set<Integer> ret = new HashSet<>();
		
		for (Vertice vertice : verticesOrdenados()) {
			ret.add(grafo.obtenerIndice(vertice));
	        if (!Auxiliares.esClique(grafo, ret)) {
	            ret.remove(grafo.obtenerIndice(vertice));
	        }
		}
		return ret;
	}
	
	private ArrayList<Vertice> verticesOrdenados() {
		ArrayList<Vertice> ret = grafo.obtenerVertices();
		Collections.sort(ret, comparador);
		return ret;
	}

}
