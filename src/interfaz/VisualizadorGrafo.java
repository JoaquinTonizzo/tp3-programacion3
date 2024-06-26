package interfaz;

import java.awt.Color;
import java.util.Set;

import javax.swing.JFrame;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import logica.GrafoConPesosEnVertices;
import logica.HeuristicaMasOptima;
import logica.Solucion;

public class VisualizadorGrafo {
	// Variables de instancia
	private JFrame frame;
	private GrafoConPesosEnVertices grafo;
	private mxGraph graph;
	private Object parent;
	// Constantes de configuracion
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final Color BACKGROUND_COLOR = new Color(30, 30, 30);

	public VisualizadorGrafo(GrafoConPesosEnVertices grafo) {
		this.grafo = grafo;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Visualización del Grafo");
		configurarFrame();

		graph = new mxGraph();
		parent = graph.getDefaultParent();

		configurarVertices();
		configurarAristas();
		mxGraphModel model = configurarModelo();

		agregarComponentes(model);
		organizarComponentes();
	}

	private void configurarFrame() {
		frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void configurarVertices() {
		var vertexStyle = graph.getStylesheet().getDefaultVertexStyle();
		vertexStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		vertexStyle.put(mxConstants.STYLE_FILLCOLOR, "#4682B4");
		vertexStyle.put(mxConstants.STYLE_FONTCOLOR, "FFFFFF");
		vertexStyle.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		graph.setCellsMovable(true);
		graph.setCellsResizable(false);
		graph.setCellsEditable(false);
		graph.setCellsDisconnectable(false);
	}

	private void configurarAristas() {
		var edgeStyle = graph.getStylesheet().getDefaultEdgeStyle();
		edgeStyle.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_CLASSIC);
		edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
		graph.setAllowDanglingEdges(false);
		graph.setSplitEnabled(false);
	}

	private mxGraphModel configurarModelo() {
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.getViewport().setOpaque(true);
		graphComponent.getViewport().setBackground(BACKGROUND_COLOR);
		graphComponent.setConnectable(false);
		frame.getContentPane().add(graphComponent);
		mxGraphModel model = (mxGraphModel) graphComponent.getGraph().getModel();
		model.beginUpdate();
		return model;
	}

	private void agregarComponentes(mxGraphModel model) {
		Object[] vertices = new Object[grafo.tamano()];
		for (int i = 0; i < grafo.tamano(); i++) {
			vertices[i] = graph.insertVertex(parent, null, "Index: " + i + "\nPeso: " + grafo.obtenerPesoVertice(i), 0,
					0, 60, 60);
		}

		Solucion solucion = new HeuristicaMasOptima(grafo).resolver();
		Set<Integer> clique = solucion.obtenerClique();
		for (int i = 0; i < grafo.tamano(); i++) {
			for (int j = i + 1; j < grafo.tamano(); j++) {
				if (grafo.existeArista(i, j)) {
					if (clique.contains(i) && clique.contains(j)) {
						graph.insertEdge(parent, null, "", vertices[i], vertices[j], "strokeColor=#DC143C");
					} else {
						graph.insertEdge(parent, null, "", vertices[i], vertices[j], "strokeColor=#3CB371");
					}
				}
			}
		}
		model.endUpdate();
	}

	private void organizarComponentes() {
		mxCircleLayout layout = new mxCircleLayout(graph);
		layout.execute(parent);
	}
}
