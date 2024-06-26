package logica;

import java.util.Set;

public class Auxiliares {
	
	public static boolean esClique(GrafoConPesosEnVertices grafo, Set<Integer> conjunto) {
		for (Integer i : conjunto) {
			for (Integer j : conjunto) {
				if (i != j && grafo.existeArista(i, j) == false) {
					return false;
				}
			}
		}
		return true;
	}
}
