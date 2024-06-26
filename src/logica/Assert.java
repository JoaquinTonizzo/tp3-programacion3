package logica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Set;

public class Assert {
	// Verifica que sean iguales como conjuntos
	public static void iguales(int[] esperado, Set<Integer> obtenido) {
		assertEquals(esperado.length, obtenido.size());

		for (int i = 0; i < esperado.length; ++i)
			assertTrue(obtenido.contains(esperado[i]));
	}
	
	// Verifica que sean grafos iguales
	public static void iguales(GrafoConPesosEnVertices esperado, GrafoConPesosEnVertices obtenido) {
		assertEquals(esperado.tamano(), obtenido.tamano());
		
		for (int i = 0; i < esperado.tamano(); ++i) {
			for (int j = 0; j < esperado.tamano(); ++j) {
				if (i != j) {
					assertTrue(esperado.existeArista(i, j) == obtenido.existeArista(i, j));
				}
			}
		}
		
		for (int i = 0; i < esperado.tamano(); ++i) {
			assertTrue(esperado.obtenerPesoVertice(i) == obtenido.obtenerPesoVertice(i));
		}
	}
}
