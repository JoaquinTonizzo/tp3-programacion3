package logica;

import java.util.HashSet;
import java.util.Set;

public class Solucion {
	private Set<Integer> clique;
	private double peso;

	public Solucion() {
		clique = new HashSet<Integer>();
	}

	public void agregarVertice(int vertice, double peso) {
		clique.add(vertice);
		this.peso += peso;
	}
	
	public Set<Integer> obtenerClique() {
		return new HashSet<>(clique);
	}

	public double obtenerPeso() {
		return peso;
	}

}
