package logica;

import java.io.File;

import org.junit.Test;

public class GeneradorGrafoJSONTest {
	
	@Test(expected = RuntimeException.class)
	public void rutaInexistenteTetst() {
	    File file = new File("src/logica/grafoInventado.json");
	    new GeneradorGrafoJSON(file);
	}

	@Test
    public void grafo1Test() {
		File file = new File("src/logica/grafo1.json");
        GeneradorGrafoJSON generadorGrafo = new GeneradorGrafoJSON(file);
        GrafoConPesosEnVertices grafo1 = generadorGrafo.obtenerGrafo();
        Assert.iguales(grafo1(), grafo1);
    }

    @Test
    public void grafo2Test() {
    	File file = new File("src/logica/grafo2.json");
        GeneradorGrafoJSON generadorGrafo = new GeneradorGrafoJSON(file);
        GrafoConPesosEnVertices grafo2 = generadorGrafo.obtenerGrafo();
        Assert.iguales(grafo2(), grafo2);
    }

    @Test
    public void grafo3Test() {
    	File file = new File("src/logica/grafo3.json");
        GeneradorGrafoJSON generadorGrafo = new GeneradorGrafoJSON(file);
        GrafoConPesosEnVertices grafo3 = generadorGrafo.obtenerGrafo();
        Assert.iguales(grafo3(), grafo3);
    }
    
    private GrafoConPesosEnVertices grafo1() {
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

    private GrafoConPesosEnVertices grafo2() {
        GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
        grafo.asignarPesoVertice(0, 5);
        grafo.asignarPesoVertice(1, 3);
        grafo.asignarPesoVertice(2, 9);
        grafo.asignarPesoVertice(3, 4);
        grafo.asignarPesoVertice(4, 6);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 3);
        grafo.agregarArista(3, 4);
        return grafo;
    }
    
    
    private GrafoConPesosEnVertices grafo3() {
        GrafoConPesosEnVertices grafo = new GrafoConPesosEnVertices(5);
        grafo.asignarPesoVertice(0, 7);
        grafo.asignarPesoVertice(1, 2);
        grafo.asignarPesoVertice(2, 8);
        grafo.asignarPesoVertice(3, 6);
        grafo.asignarPesoVertice(4, 20);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(0, 3);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 3);
        grafo.agregarArista(3, 4);
        return grafo;
    }

}
