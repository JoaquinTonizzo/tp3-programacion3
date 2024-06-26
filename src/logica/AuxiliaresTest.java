package logica;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class AuxiliaresTest {
	@Test
	public void todosAisladosTest() {
		int[] C = { 2, 3, 4 };
		GrafoConPesosEnVertices aislado = new GrafoConPesosEnVertices(5);

		assertFalse(Auxiliares.esClique(aislado, toSet(C)));
	}

	@Test
	public void vacioTest() {
		int[] C = {};
		GrafoConPesosEnVertices aislado = new GrafoConPesosEnVertices(5);

		assertTrue(Auxiliares.esClique(aislado, toSet(C)));
	}

	@Test
	public void trianguloTest() {
		int[] C = { 0, 1, 2 };
		GrafoConPesosEnVertices grafo = trianguloConAntena();

		assertTrue(Auxiliares.esClique(grafo, toSet(C)));
	}

	@Test
	public void noTrianguloTest() {
		int[] C = { 0, 1, 3 };
		GrafoConPesosEnVertices grafo = trianguloConAntena();

		assertFalse(Auxiliares.esClique(grafo, toSet(C)));
	}

	@Test
	public void aristaTest() {
		int[] C = { 1, 3 };
		GrafoConPesosEnVertices grafo = trianguloConAntena();

		assertTrue(Auxiliares.esClique(grafo, toSet(C)));
	}

	private GrafoConPesosEnVertices trianguloConAntena() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(4);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(3, 1);

		return grafo;
	}

	private Set<Integer> toSet(int[] valores) {
		Set<Integer> ret = new HashSet<Integer>();
		for (Integer v : valores)
			ret.add(v);

		return ret;
	}
}
