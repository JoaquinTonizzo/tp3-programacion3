package logica;

import static org.junit.Assert.assertEquals;

import java.io.File;


import org.junit.Test;

public class HeuristicaMasOptimaTest {
	
	@Test
	public void grafoVacioTest() {
		HeuristicaMasOptima masOptima = new HeuristicaMasOptima(new GrafoConPesosEnVertices(0));
		Solucion sol = masOptima.resolver();

		int[] esperado = {};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(0, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void grafoUnVertice() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(1);
		HeuristicaMasOptima masOptima = new HeuristicaMasOptima(grafo);
		grafo.asignarPesoVertice(0, 5);
		Solucion sol = masOptima.resolver();

		int[] esperado = {0};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(5, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void grafoDosVerticesAislados() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(2);
		HeuristicaMasOptima masOptima = new HeuristicaMasOptima(grafo);
		grafo.asignarPesoVertice(0, 5);
		grafo.asignarPesoVertice(1, 2);
		Solucion sol = masOptima.resolver();

		int[] esperado = {0};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(5, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void grafoDosVerticesConexo() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(2);
		HeuristicaMasOptima masOptima = new HeuristicaMasOptima(grafo);
		grafo.asignarPesoVertice(0, 5);
		grafo.asignarPesoVertice(1, 2);
		grafo.agregarArista(0, 1);
		Solucion sol = masOptima.resolver();

		int[] esperado = {0 ,1};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(7, sol.obtenerPeso(), 0.001);
	}

	@Test
	public void grafoConsignaTest() {
		HeuristicaMasOptima masOptima = new HeuristicaMasOptima(new GeneradorGrafoJSON(new File("src/logica/grafo1.json")).obtenerGrafo());
		Solucion sol = masOptima.resolver();

		int[] esperado = {0, 1, 3};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(23.5, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void grafo2Test() {
		HeuristicaMasOptima masOptima = new HeuristicaMasOptima(new GeneradorGrafoJSON(new File("src/logica/grafo2.json")).obtenerGrafo());
		Solucion sol = masOptima.resolver();

		int[] esperado = {0, 1, 2};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(17, sol.obtenerPeso(), 0.001);
	}
	
	@Test
	public void grafo3Test() {
		HeuristicaMasOptima masOptima = new HeuristicaMasOptima(new GeneradorGrafoJSON(new File("src/logica/grafo3.json")).obtenerGrafo());
		Solucion sol = masOptima.resolver();

		int[] esperado = {3, 4};
		Assert.iguales(esperado, sol.obtenerClique());
		assertEquals(26, sol.obtenerPeso(), 0.001);
	}

}
