package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logica.ComparadorPorPeso;
import logica.ComparadorPorCantidadVecinos;
import logica.ComparadorPorPuntaje;
import logica.GeneradorGrafoJSON;
import logica.HeuristicaMasOptima;
import logica.Solucion;
import logica.Solver;

import java.io.File;

public class Menu {
	// Variables de instancia
	private JFrame frame;
	private GeneradorGrafoJSON grafoJSON;
	private JTextArea textAreaResult;
	// Constantes de configuracion
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final Color BACKGROUND_COLOR = new Color(30, 30, 30);
	private static final Color TEXT_COLOR = new Color(255, 255, 255);
	private static final Font TITLE_FONT = new Font("Tahoma", Font.BOLD, 20);
	private static final Font BUTTON_FONT = new Font("Tahoma", Font.PLAIN, 16);
	private static final Font TEXTAREA_FONT = new Font("Tahoma", Font.PLAIN, 16);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		configurarFrame();

		JPanel contentPane = new JPanel();
		configurarPanel(contentPane);
		frame.setContentPane(contentPane);

		JLabel lblTitle = new JLabel("Algoritmo Goloso para Clique de Peso Máximo");
		configurarTitulo(lblTitle);
		contentPane.add(lblTitle);

		createButton(contentPane, "Cargar Archivo", new Color(70, 130, 180), TEXT_COLOR, BUTTON_FONT, 300, 180, 200, 40,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarArchivo();
					}
				});
		createButton(contentPane, "Ejecutar Algoritmo", new Color(60, 179, 113), TEXT_COLOR, BUTTON_FONT, 300, 240, 200,
				40, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ejecutarAlgoritmo();
					}
				});
		createButton(contentPane, "Ver Grafo", new Color(220, 20, 60), TEXT_COLOR, BUTTON_FONT, 300, 300, 200, 40,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						verGrafo();
					}
				});
		createButton(contentPane, "Salir", new Color(255, 69, 0), TEXT_COLOR, BUTTON_FONT, 300, 360, 200, 40,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salir();
					}
				});

		textAreaResult = new JTextArea();
		configurarTextoResultado();
		contentPane.add(textAreaResult);
	}

	private void configurarFrame() {
		frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Algoritmo Goloso para Clique de Peso Máximo");
		frame.setResizable(false);
		frame.setVisible(true);
	}

	private void configurarPanel(JPanel contentPane) {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setLayout(null);
	}

	private void configurarTitulo(JLabel lblTitle) {
		lblTitle.setBounds(150, 60, 500, 30);
		lblTitle.setForeground(TEXT_COLOR);
		lblTitle.setFont(TITLE_FONT);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
	}

	private void createButton(JPanel panel, String text, Color background, Color foreground, Font font, int x, int y, int width, int height, ActionListener actionListener) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setBackground(background);
		button.setForeground(foreground);
		button.setFont(font);
		button.addActionListener(actionListener);
		panel.add(button);
	}

	private void configurarTextoResultado() {
		textAreaResult.setBounds(100, 420, 600, 120);
		textAreaResult.setForeground(TEXT_COLOR);
		textAreaResult.setBackground(BACKGROUND_COLOR);
		textAreaResult.setFont(TEXTAREA_FONT);
		textAreaResult.setLineWrap(true);
		textAreaResult.setWrapStyleWord(true);
		textAreaResult.setEditable(false);
	}

	private void cargarArchivo() {
		textAreaResult.setText("");
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String fileName = selectedFile.getName().toLowerCase().trim();
			if (!fileName.endsWith(".json")) { 
				grafoJSON = null;
				JOptionPane.showMessageDialog(frame, "Por favor, seleccione un archivo JSON.");
			} else {
				JOptionPane.showMessageDialog(frame, "Archivo cargado exitosamente.");
				try {
					grafoJSON = new GeneradorGrafoJSON(selectedFile);
				} catch (IllegalArgumentException ex) {
					grafoJSON = null;
					JOptionPane.showMessageDialog(frame, "El archivo no contiene un grafo en el formato esperado.");
				} catch (RuntimeException ex) {
					grafoJSON = null;
					JOptionPane.showMessageDialog(frame, "Ocurrio un error durante la lectura del archivo.");
				}
			}
		}
	}

	private void ejecutarAlgoritmo() {
		if (grafoJSON != null) {
			StringBuilder ret = new StringBuilder();
			Solucion porPeso = new Solver(grafoJSON.obtenerGrafo(), new ComparadorPorPeso()).resolver();
			ret.append("Resultado Heuristica por Peso: ")
					.append(porPeso.obtenerClique())
					.append(" con un peso de: ")
					.append(porPeso.obtenerPeso()).append("\n");

			Solucion porCantVecinos = new Solver(grafoJSON.obtenerGrafo(), new ComparadorPorCantidadVecinos()).resolver();
			ret.append("Resultado Heuristica por Cantidad de vecinos: ")
					.append(porCantVecinos.obtenerClique())
					.append(" con un peso de: ")
					.append(porCantVecinos.obtenerPeso()).append("\n");

			Solucion porPuntaje = new Solver(grafoJSON.obtenerGrafo(), new ComparadorPorPuntaje()).resolver();
			ret.append("Resultado Heuristica por Puntaje (Peso x Cantidad de vecinos): ")
					.append(porPuntaje.obtenerClique())
					.append(" con un peso de: ")
					.append(porPuntaje.obtenerPeso()).append("\n");

			Solucion masOptima = new HeuristicaMasOptima(grafoJSON.obtenerGrafo()).resolver();
			ret.append("Resultado más óptimo entre los obtenidos: ")
					.append(masOptima.obtenerClique())
					.append(" con un peso de: ")
					.append(masOptima.obtenerPeso());

			textAreaResult.setText(ret.toString());
		} else {
			JOptionPane.showMessageDialog(frame, "Primero cargue un grafo.");
		}
	}

	private void verGrafo() {
		if (grafoJSON != null) {
			new VisualizadorGrafo(grafoJSON.obtenerGrafo());
		} else {
			JOptionPane.showMessageDialog(frame, "No se cargó ningún grafo.");
		}
	}

	private void salir() {
		System.exit(0);
	}
}
