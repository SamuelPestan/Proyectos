package TresEnRaya;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Juego extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();

	JButton[][] tablero;
	private int turno=0;
	private final String RUTA_ARCHIVO;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame padre = new JFrame();
			Juego dialog = new Juego(padre);
			dialog.Interfaz();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Juego(JFrame padre) {
		super(padre, true);
		RUTA_ARCHIVO = "src"+File.separator+"files"+File.separator+"Ej3_Historial";
	}
	
	public void Interfaz() {
		setTitle("Texto ");
		setBounds(100, 100, 450, 300);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		// Tamaño del tablero
		tablero = new JButton[3][3]; 
		
		// Bucle para definir los botones que formaran el tres en raya
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				tablero[i][j] = new JButton();
				tablero[i][j].addActionListener(this);
				tablero[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				contentPanel.setLayout(new GridLayout(0, 3, 0, 0));
				contentPanel.add(tablero[i][j]);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton botonPulsado = (JButton) e.getSource();
		// Este operador ternario define de quien es el turno mediante un algoritmo de par impar
		botonPulsado.setText(turno % 2 == 0 ? "X" : "O");
		if(turno%2==0) {
			botonPulsado.setBackground(Color.BLUE);
		}else {
			botonPulsado.setBackground(Color.RED);
		}
		//Una vez se haya presionado el botón este no podrá volver a presionarse
		botonPulsado.setEnabled(false);
		turno++;
		resultadoPartida();
		
	}
	
	public void resultadoPartida() {
		boolean empate = true;
		String jugador = null;
		for(int i=0; i<3; i++) {
			
			//Comprobar filas y columnas
			if (tablero[i][0].getText() == "X" && tablero[i][1].getText() == "X" && tablero[i][2].getText() == "X") {
				jugador = "X";
				empate = false;
	        }else if(tablero[i][0].getText() == "O" && tablero[i][1].getText() == "O" && tablero[i][2].getText() == "O"){
	        	jugador = "O";
	        	empate = false;
	        }
			
	        if (tablero[0][i].getText() == "X" && tablero[1][i].getText() == "X" && tablero[2][i].getText() == "X") {
	        	jugador = "X";
	        	empate = false;
	        }else if(tablero[0][i].getText() == "O" && tablero[1][i].getText() == "O" && tablero[2][i].getText() == "O") {
	        	jugador = "O";
	        	empate = false;
	        }
		}
		
		// Comprobar diagonales
	    if (tablero[0][0].getText() == "X" && tablero[1][1].getText() == "X" && tablero[2][2].getText() == "X") {
	    	jugador = "X";
	    	empate = false;
	    }else if(tablero[0][0].getText() == "O" && tablero[1][1].getText() == "O" && tablero[2][2].getText() == "O") {
	    	jugador = "O";
	    	empate = false;
	    }
	    
	    if (tablero[0][2].getText() == "X" && tablero[1][1].getText() == "X" && tablero[2][0].getText() == "X") {
	    	jugador = "X";
	    	empate = false;
	    }else if(tablero[0][2].getText() == "O" && tablero[1][1].getText() == "O" && tablero[2][0].getText() == "O") {
	    	jugador = "O";
	    	empate = false;

	    }
	    
	    //	Si hay el boolean empate sigue siendo true cuando se haya llegado al limite de puntos el resultado será empate
	    if(empate && turno>8) {
	    	JOptionPane.showMessageDialog(null, "Empate", "No hay ganador", JOptionPane.INFORMATION_MESSAGE);
	    	escribirResultado(RUTA_ARCHIVO, true, "Nadie");
	    }
	    
	    // If que mostrará el jugador que haya ganado
	    if(jugador != null) {
	    	JOptionPane.showMessageDialog(null, jugador, "Ganador", JOptionPane.INFORMATION_MESSAGE);
	    	escribirResultado(RUTA_ARCHIVO, true, jugador);
	    }
	}
	
	public void escribirResultado(String nombreArchivo, boolean apend, String jugador) {
		// Apertura
		File archivo = new File(nombreArchivo);
		
		// Metodos de fecha y hora
		LocalDateTime ahora = LocalDateTime.now();
		
		// Cambiar el formato de fecha y hora
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String tiempoFormateado = formato.format(ahora);
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, apend));
			escritor.write("Gana " + jugador + " | " + "fecha: " + tiempoFormateado);
			escritor.newLine();
			// Cerrar la ventana después de escribir
			dispose();
			// Cerrar
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
