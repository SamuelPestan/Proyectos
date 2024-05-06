package sudoku;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class Sudoku extends JFrame 
							implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField[][] tablero;
	private int[][] sudokuResuelto = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
							  {6, 7, 2, 1, 9, 5, 3, 4, 8},
							  {1, 9, 8, 3, 4, 2, 5, 6, 7},
							  {8, 5, 9, 7, 6, 1, 4, 2, 3},
							  {4, 2, 6, 8, 5, 3, 7, 9, 1},
							  {7, 1, 3, 9, 2, 4, 8, 5, 6},
							  {9, 6, 1, 5, 3, 7, 2, 8, 4},
							  {2, 8, 7, 4, 1, 9, 6, 3, 5},
							  {3, 4, 5, 2, 8, 6, 1, 7, 9}};
	
	int numHuecos;
	
	boolean[][] valoresCorrectos;
	
	public int getNumHuecos() {
		return numHuecos;
	}

	public void setNumHuecos(int numHuecos) {
		this.numHuecos = numHuecos;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku frame = new Sudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	class SudokuActionListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			comprobarVictoria();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			
		}
		
	}
	public Sudoku() {
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 3, 2, 2));
		
		JPanel[][] regiones = new JPanel[3][3]; 

	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            regiones[i][j] = new JPanel(new GridLayout(3, 3)); // Cada región es un panel con GridLayout de 3x3
	            regiones[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 3)); 
	            contentPane.add(regiones[i][j]); 
	        }
	    }
		
		tablero = new JTextField[9][9];
		
		for(int i=0; i<9;i++) {
			for(int j=0; j<9;j++) {
			
				tablero[i][j]= new JTextField();
				tablero[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				tablero[i][j].setBackground(Color.BLACK);
				tablero[i][j].setForeground(Color.WHITE);
				contentPane.add(tablero[i][j]);
				tablero[i][j].setText(String.valueOf(sudokuResuelto[i][j]));
				tablero[i][j].setEditable(false);
				tablero[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				// Determinar la región a la que pertenece la celda y agregarla al panel correspondiente
	            int regionFila = i / 3;
	            int regionCol = j / 3;
	            regiones[regionFila][regionCol].add(tablero[i][j]);
	            tablero[i][j].getDocument().addDocumentListener(new SudokuActionListener()); 
	            // Listener para verificar el contenido	    
                tablero[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char caracter = e.getKeyChar();
                        if (!(Character.isDigit(caracter) || caracter == KeyEvent.VK_DELETE
                        		|| (caracter >=1 && caracter <=9))) {
                            /**
                             *Character.isDigit(caracter) = es un digito?
                             *caracter == KeyEvent.VK_DELETE = es el boton de borrar?
                             *Sino no será válido */
                        	e.consume();
                        }
                    }
                });
				
			}
			
		}
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public void vaciarHuecos(Object dificultad) {
		Random rnd = new Random();
		
		if(dificultad=="Fácil") {
			setNumHuecos(30);
		}else if(dificultad=="Medio") {
			setNumHuecos(40);
		}else if(dificultad=="Difícil") {
			setNumHuecos(50);
		}else if(dificultad=="Muy Fácil") {
			setNumHuecos(0);
		}
		int cont=0;
		while(cont<=getNumHuecos()) {
			int fila = rnd.nextInt(9);
			int columna = rnd.nextInt(9);
			
			if(tablero[fila][columna].getText() !="") {
				tablero[fila][columna].setText("");
				tablero[fila][columna].setForeground(Color.black);
				tablero[fila][columna].setBackground(Color.WHITE);
				tablero[fila][columna].setEditable(true);
				cont++;
			}
		}
	}
	
	/**Metodo que deberá comprobar si un mismo número no se repite en:
	 * - Una fila 
	 * - Una columna
	 * - Una región*/
	
	public void comprobarVictoria() {
		valoresCorrectos = new boolean[9][9];
	
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				valoresCorrectos[i][j]=true;
				try {
					int numActual = Integer.parseInt(tablero[i][j].getText());
					
					//Revisar las filas
					for(int k=0; k<9; k++) {
						//Asegurarse de no comparar una celda consigo misma
						if(k!=j) {
							int numFila = Integer.parseInt(tablero[i][k].getText());
							if(numActual == numFila) {
								valoresCorrectos[i][j]=false;
								break;
							}
						}
					}
					
					//Revisar las columnas
					for(int k=0; k<9; k++) {
						if(k!=i) {
							int numColumna = Integer.parseInt(tablero[k][j].getText());
							if(numActual == numColumna) {
								valoresCorrectos[i][j]=false;
								break;
							}
						}
					}
					
					//Revisar las regiones
					int regionFilas = i / 3 * 3;
					int regionColumnas = j / 3 * 3;
					for(int k=regionFilas; k < regionFilas + 3; k++) {
						for(int l=regionColumnas; l < regionColumnas + 3; l++) {
							//Asegurarse de no comparar una casilla que este en la misma fila y la misma columna
							if(k!=i || l!=j) {
								int numRegion = Integer.parseInt(tablero[k][l].getText());
								if(numActual == numRegion) {
									valoresCorrectos[i][j]=false;
									break;
								}
							}
						}
					}
					
				}catch(Exception e) {
					valoresCorrectos[i][j]=false;
				}
			}
		}
		
		if(sudokuCorrecto()) {
			PantallaFinal pf = new PantallaFinal();
			dispose();
			pf.setVisible(true);
		}
	
	}
	
	public boolean sudokuCorrecto() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(!valoresCorrectos[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	

	
}
