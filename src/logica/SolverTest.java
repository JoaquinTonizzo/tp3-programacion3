package logica;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class SolverTest {
	
	@Test
	public void grafoVacio() {
		Solver solver = new Solver(new GrafoConPesosEnVertices(0), new ComparadorPorPeso());
		Solucion sol = solver.resolver();

		int[] esperado = {};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(0, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void grafoUnVertice() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(1);
		Solver solver = new Solver(grafo, new ComparadorPorPeso());
		grafo.asignarPesoVertice(0, 5);
		Solucion sol = solver.resolver();

		int[] esperado = {0};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(5, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void grafoDosVerticesAislados() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(2);
		Solver solver = new Solver(grafo, new ComparadorPorPeso());
		grafo.asignarPesoVertice(0, 5);
		grafo.asignarPesoVertice(1, 2);
		Solucion sol = solver.resolver();

		int[] esperado = {0};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(5, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void grafoDosVerticesConexo() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(2);
		Solver solver = new Solver(grafo, new ComparadorPorPeso());
		grafo.asignarPesoVertice(0, 5);
		grafo.asignarPesoVertice(1, 2);
		grafo.agregarArista(0, 1);
		Solucion sol = solver.resolver();

		int[] esperado = {0 ,1};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(7, sol.obtenerPeso(), 0.001);
	}

	@Test
	public void porPesoTest() {
		Solver solver = new Solver(grafoConsigna(), new ComparadorPorPeso());
		Solucion sol = solver.resolver();

		int[] esperado = {0,1,3};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(23.5, sol.obtenerPeso(), 0.001);
	}

	@Test
	public void porCantidadDeVecinosTest() {
		Solver solver = new Solver(grafoConsigna(), new ComparadorPorCantidadVecinos());
		Solucion sol = solver.resolver();

		int[] esperado = {1,3,4,5};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(18.5, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void porPuntajeTest() {
		Solver solver = new Solver(grafoConsigna(), new ComparadorPorPuntaje());
		Solucion sol = solver.resolver();

		int[] esperado = {0,1,3};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(23.5, sol.obtenerPeso(), 0.001);
	}

	private GrafoConPesosEnVertices grafoConsigna() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(6);
		grafo.asignarPesoVertice(0, 11.0);
		grafo.asignarPesoVertice(1, 5.5);
		grafo.asignarPesoVertice(2, 1.1);
		grafo.asignarPesoVertice(3, 7.0);
		grafo.asignarPesoVertice(4, 2.5);
		grafo.asignarPesoVertice(5, 3.5);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 3);
		grafo.agregarArista(1, 4);
		grafo.agregarArista(1, 5);
		grafo.agregarArista(3, 4);
		grafo.agregarArista(3, 5);
		grafo.agregarArista(4, 5);
		grafo.agregarArista(2, 4);
		return grafo;
	}

}
