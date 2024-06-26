package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import logica.GrafoConPesosEnVertices.Vertice;

public class HeuristicaMasOptima {
    private GrafoConPesosEnVertices grafo;

    public HeuristicaMasOptima(GrafoConPesosEnVertices grafoResolver) {
        this.grafo = grafoResolver;
    }
    
    public Solucion resolver() {
        List<Solucion> soluciones = new ArrayList<>();
        soluciones.add(obtenerSolucionPorComparator(new ComparadorPorPeso()));
        soluciones.add(obtenerSolucionPorComparator(new ComparadorPorCantidadVecinos()));
        soluciones.add(obtenerSolucionPorComparator(new ComparadorPorPuntaje()));

        // Ordenamos las soluciones por peso
        Collections.sort(soluciones, new Comparator<Solucion>() {
            @Override
            public int compare(Solucion una, Solucion otra) {
                return Double.compare(una.obtenerPeso(), otra.obtenerPeso());
            }
        });

        // Retornamos la solución con el mayor peso
        return soluciones.get(soluciones.size() - 1);
    }

    private Solucion obtenerSolucionPorComparator(Comparator<Vertice> comparator) {
        Solver solver = new Solver(grafo, comparator);
        return solver.resolver();
    }
}
