package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GrafoConPesosEnVertices {
	private ArrayList<Vertice> vertices;
	private int[][] matrizAdyacencia;

	public GrafoConPesosEnVertices(int cantVertices) {
		if (cantVertices < 0) {
			throw new IllegalArgumentException("El número de vértices no puede ser negativo");
		}
		matrizAdyacencia = new int[cantVertices][cantVertices];
		vertices = new ArrayList<>();
		for (int i = 0; i < cantVertices; i++) {
			vertices.add(new Vertice(0));
		}
	}

	public void agregarArista(int i, int j) {
		if (!existeArista(i, j)) {
			matrizAdyacencia[i][j] = 1;
			matrizAdyacencia[j][i] = 1;
			vertices.get(i).agregarVecino();
			vertices.get(j).agregarVecino();
		}
	}

	public void eliminarArista(int i, int j) {
		if (existeArista(i, j)) {
			matrizAdyacencia[i][j] = 0;
			matrizAdyacencia[j][i] = 0;
			vertices.get(i).eliminarVecino();
			vertices.get(j).eliminarVecino();
		}
	}

	public boolean existeArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		return matrizAdyacencia[i][j] == 1;
	}

	public void asignarPesoVertice(int vertice, double peso) {
		verificarVertice(vertice);
		vertices.get(vertice).asignarPeso(peso);
	}

	public double obtenerPesoVertice(int vertice) {
		verificarVertice(vertice);
		return vertices.get(vertice).obtenerPeso();
	}

	public int obtenerIndice(Vertice vertice) {
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).equals(vertice)) {
				return i;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Vertice> obtenerVertices() {
		return (ArrayList<Vertice>) vertices.clone();
	}

	public int tamano() {
		return matrizAdyacencia.length;
	}

	public Set<Integer> vecinos(int i) {
		verificarVertice(i);
		Set<Integer> ret = new HashSet<>();
		for (int j = 0; j < this.tamano(); ++j) {
			if (i != j && matrizAdyacencia[i][j] == 1) {
				ret.add(j);
			}
		}
		return ret;
	}

	private void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("El vértice no puede ser negativo: " + i);

		if (i >= matrizAdyacencia.length)
			throw new IllegalArgumentException("Los vértices deben estar entre 0 y |V|-1: " + i);
	}

	private void verificarDistintos(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
	
	public class Vertice {
		private double peso;
		private int cantidadVecinos;

		public Vertice(double peso) {
			this.peso = peso;
			this.cantidadVecinos = 0;
		}

		public double obtenerPeso() {
			return this.peso;
		}
		
		public int obtenerCantidadVecinos() {
			return cantidadVecinos;
		}
		
		public double obtenerPuntaje() {
			if (obtenerCantidadVecinos() != 0)
				return obtenerPeso() * obtenerCantidadVecinos();
			return obtenerPeso();
		}

		private void asignarPeso(double peso) {
			this.peso = peso;
		}

		private void agregarVecino() {
			this.cantidadVecinos++;
		}

		private void eliminarVecino() {
			this.cantidadVecinos--;
		}
		
	}
}


