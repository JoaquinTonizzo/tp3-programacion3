package logica;

import org.junit.Test;

import logica.GrafoConPesosEnVertices.Vertice;

import static org.junit.Assert.*;

import java.util.ArrayList;


public class GrafoConPesosEnVerticesTest {

	@Test(expected = IllegalArgumentException.class)
	public void construirConVerticesNegativos() {
		new GrafoConPesosEnVertices(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaPrimerVerticeNegativoTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(-1, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaPrimerVerticeExcedidoTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaSegundoVerticeNegativoTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaSegundoVerticeExcedidoTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, 2);
	}

	@Test
	public void aristaExistenteTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, 3);
		assertTrue(GrafoConPesosEnVertices.existeArista(2, 3));
	}

	@Test
	public void aristaOpuestaTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, 3);
		assertTrue(GrafoConPesosEnVertices.existeArista(3, 2));
	}

	@Test
	public void aristaInexistenteTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, 3);
		assertFalse(GrafoConPesosEnVertices.existeArista(1, 4));
	}

	@Test
	public void agregarAristaDosVecesTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, 3);
		GrafoConPesosEnVertices.agregarArista(2, 3);
		assertTrue(GrafoConPesosEnVertices.existeArista(2, 3));
	}

	@Test
	public void eliminarAristaExistenteTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, 4);
		GrafoConPesosEnVertices.eliminarArista(2, 4);
		assertFalse(GrafoConPesosEnVertices.existeArista(2, 4));
	}

	@Test
	public void eliminarAristaInexistenteTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.eliminarArista(2, 4);
		assertFalse(GrafoConPesosEnVertices.existeArista(2, 4));
	}

	@Test
	public void eliminarAristaDosVecesTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(2, 4);
		GrafoConPesosEnVertices.eliminarArista(2, 4);
		GrafoConPesosEnVertices.eliminarArista(2, 4);
		assertFalse(GrafoConPesosEnVertices.existeArista(2, 4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void obtenerVecinosVerticeNegativoTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void obtenerVecinosVerticeExcedidoTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.vecinos(5);
	}

	@Test
	public void obtenerVecinosTodosAisladosTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		assertEquals(0, GrafoConPesosEnVertices.vecinos(2).size());
	}

	@Test
	public void obtenerVecinosVerticeUniversalTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(4);
		GrafoConPesosEnVertices.agregarArista(1, 0);
		GrafoConPesosEnVertices.agregarArista(1, 2);
		GrafoConPesosEnVertices.agregarArista(1, 3);

		int[] esperado = {0, 2, 3};
		Assert.iguales(esperado, GrafoConPesosEnVertices.vecinos(1));
	}

	@Test
	public void obtenerVecinosVerticeNormalTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(1, 3);
		GrafoConPesosEnVertices.agregarArista(2, 3);
		GrafoConPesosEnVertices.agregarArista(2, 4);

		int[] esperados = {1, 2};
		Assert.iguales(esperados, GrafoConPesosEnVertices.vecinos(3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void asignarPesoVerticeNegativoTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		grafo.asignarPesoVertice(-1, 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void asignarPesoVerticeExcedidoTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		grafo.asignarPesoVertice(5, 10);
	}

	@Test
	public void asignarPesoVerticeCorrectoTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		grafo.asignarPesoVertice(0, 10);
		assertEquals(10, grafo.obtenerPesoVertice(0), 0.001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoVerticeNegativoTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		grafo.obtenerPesoVertice(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoVerticeExcedidoTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		grafo.obtenerPesoVertice(5);
	}

	@Test
	public void obtenerPesoVerticeCorrectoTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		grafo.asignarPesoVertice(4, 10);
		assertEquals(10, grafo.obtenerPesoVertice(4), 0.001);
	}
	
	@Test
	public void obtenerIndiceVerticeNullTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		assertEquals(-1, grafo.obtenerIndice(null));
	}
	
	@Test
	public void obtenerIndiceVerticeInexistenteTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		Vertice vertice = grafo.new Vertice(5);
		assertEquals(-1, grafo.obtenerIndice(vertice));
	}

	@Test
	public void obtenerIndiceVerticeCorrectoTest() {
        GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
        Vertice v = grafo.obtenerVertices().get(4);
        assertEquals(4, grafo.obtenerIndice(v));
    }
	
	@Test
    public void testObtenerVerticesVacio() {
        GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(0);

        ArrayList<Vertice> vertices = grafo.obtenerVertices();
        assertEquals(0, vertices.size());
    }
	
	@Test
    public void testObtenerVerticesSinPesos() {
        GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(3);

        ArrayList<Vertice> vertices = grafo.obtenerVertices();
        assertEquals(3, vertices.size());
        assertEquals(0, vertices.get(0).obtenerPeso(), 0.001);
        assertEquals(0, vertices.get(1).obtenerPeso(), 0.001);
        assertEquals(0, vertices.get(2).obtenerPeso(), 0.001);
    }
    @Test
    public void testObtenerVertices() {
        GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(3);
        grafo.asignarPesoVertice(0, 5);
        grafo.asignarPesoVertice(1, 10);
        grafo.asignarPesoVertice(2, 15);

        ArrayList<Vertice> vertices = grafo.obtenerVertices();
        assertEquals(3, vertices.size());
        assertEquals(5, vertices.get(0).obtenerPeso(), 0.001);
        assertEquals(10, vertices.get(1).obtenerPeso(), 0.001);
        assertEquals(15, vertices.get(2).obtenerPeso(), 0.001);
    }

	@Test
	public void obtenerTamanoTest() {
		GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
		assertEquals(5, grafo.tamano());
	}
	
	@Test
	public void obtenerCantidadVecinosTodosAisladosTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		assertEquals(0, GrafoConPesosEnVertices.obtenerVertices().get(0).obtenerCantidadVecinos());
	}

	@Test
	public void obtenerCantidadVecinosVerticeUniversalTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(4);
		GrafoConPesosEnVertices.agregarArista(1, 0);
		GrafoConPesosEnVertices.agregarArista(1, 2);
		GrafoConPesosEnVertices.agregarArista(1, 3);

		assertEquals(3, GrafoConPesosEnVertices.obtenerVertices().get(1).obtenerCantidadVecinos());
	}

	@Test
	public void obtenerCantidadVecinosVerticeNormalTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(1, 3);
		GrafoConPesosEnVertices.agregarArista(2, 3);
		GrafoConPesosEnVertices.agregarArista(2, 4);

		assertEquals(2, GrafoConPesosEnVertices.obtenerVertices().get(3).obtenerCantidadVecinos());
	}

	@Test
	public void obtenerPuntajeVerticeAisladoTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.asignarPesoVertice(0, 5);
		
		assertEquals(5, GrafoConPesosEnVertices.obtenerVertices().get(0).obtenerPuntaje(), 0.001);
	}

	@Test
	public void obtenerPuntajeVerticeUniversalTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(4);
		GrafoConPesosEnVertices.agregarArista(1, 0);
		GrafoConPesosEnVertices.agregarArista(1, 2);
		GrafoConPesosEnVertices.agregarArista(1, 3);
		GrafoConPesosEnVertices.asignarPesoVertice(1, 10);
		
		assertEquals(30, GrafoConPesosEnVertices.obtenerVertices().get(1).obtenerPuntaje(), 0.001);
	}

	@Test
	public void obtenerPuntajeVerticeNormalTest() {
		GrafoConPesosEnVertices GrafoConPesosEnVertices = new GrafoConPesosEnVertices(5);
		GrafoConPesosEnVertices.agregarArista(1, 3);
		GrafoConPesosEnVertices.agregarArista(2, 3);
		GrafoConPesosEnVertices.agregarArista(2, 4);
		GrafoConPesosEnVertices.asignarPesoVertice(3, 2);
		
		assertEquals(4, GrafoConPesosEnVertices.obtenerVertices().get(3).obtenerPuntaje(), 0.001);
	}
}
