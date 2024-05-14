import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Toolkit;

/**
 * Método principal que permite la navegación entre las diversas funcionalidades de La Mansión Zombie.
 * 
 * @author Samuel A. Moreno Pestana
 * @version 1.8
 */
public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFondoMansion;
	private JButton btnJugar, btnCargarPartida, btnHistorial;
	private JComboBox<String> cmbDificultad;
	
	private Clip clip;
	
    private final String RUTA_FICHERO = "src"+File.separator+"Files"+File.separator+"datos";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		interfaz();
		
	}

	/**
	 * Método que contienen el apartado visual de la clase Principal.
	 */
	public void interfaz() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Imgs/icono.jpg")));
		setTitle("Mansión zombie");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnJugar = new JButton("Jugar");
		formatoBoton(btnJugar);
		btnJugar.setBounds(106, 46, 145, 36);
		contentPane.add(btnJugar);
		
		btnCargarPartida = new JButton("Cargar partida");
		formatoBoton(btnCargarPartida);
		btnCargarPartida.setBounds(106, 146, 145, 36);
		contentPane.add(btnCargarPartida);
		
		btnHistorial = new JButton("Ver histórico");
		formatoBoton(btnHistorial);
		btnHistorial.setBounds(106, 246, 145, 36);
		contentPane.add(btnHistorial);
		
		cmbDificultad = new JComboBox<String>();
		cmbDificultad.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbDificultad.setForeground(new Color(255, 255, 255));
		cmbDificultad.setBackground(SystemColor.inactiveCaption);
		cmbDificultad.setBounds(347, 46, 145, 36);
		
		 // Añadir las opciones de dificultad al JComboBox
	    cmbDificultad.addItem("Fácil");
	    cmbDificultad.addItem("Media");
	    cmbDificultad.addItem("Difícil");
		contentPane.add(cmbDificultad);
		
		lblFondoMansion = new JLabel("");
		ImageIcon icon = new ImageIcon(getClass().getResource("/Imgs/Mansion.jpg"));
		lblFondoMansion.setBounds(0, 0, 559, 386);
        Image img = icon.getImage().getScaledInstance(lblFondoMansion.getWidth(), lblFondoMansion.getHeight(), Image.SCALE_SMOOTH);
        lblFondoMansion.setIcon(new ImageIcon(img));
		
		contentPane.add(lblFondoMansion);
		
		reproducirMusica();
	}
	
	/**
	 * Método que se encarga de asignarle un formato predefinido a un botón.
	 * 
	 * @param btn Botón que recibe el formato.
	 */
	private void formatoBoton(JButton btn) {
		btn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(112, 128, 144));
		btn.addActionListener(this);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	/**
	 * Método heredado que se encarga de gestionar las acciones de los botones.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnJugar) {
			String dificultadSeleccionada = (String) cmbDificultad.getSelectedItem();
			Superviviente jugador = new Superviviente();
			int numHabitaciones = 0;
			
			if(dificultadSeleccionada == "Fácil") {
				numHabitaciones = 5;
			}else if(dificultadSeleccionada == "Media") {
				numHabitaciones = 7;
			}else {
				numHabitaciones = 10;
			}
			detenerMusica();
			Juego juego = new Juego(numHabitaciones, 3, 1, 1, EstadoPartida.EN_PARTIDA, jugador,
					dificultadSeleccionada,this);
			juego.mostrarInterfaz();
			reproducirMusica();
		}
		
		if(e.getSource()==btnCargarPartida) {
			cargarPartida(RUTA_FICHERO);
		}
		
		if(e.getSource() == btnHistorial) {
			Historial historico = new Historial(this);
			historico.mostrarInterfaz();
		}
	}
	
	/**
	 * Método que lee la información de un fichero binario y la usa para iniciar una partida en un 
	 * punto que el jugador haya guardado.
	 * 
	 * @param rutaFichero Ruta del fichero que se lee.
	 */
	private void cargarPartida(String rutaFichero) {
		
		File fichero = new File(rutaFichero);
		
		try {
			if(!fichero.exists())
				fichero.createNewFile();
			
			if (fichero.length() == 0) {
	            JOptionPane.showMessageDialog(this, "No hay información para cargar.",
	                    "Archivo vacío", JOptionPane.WARNING_MESSAGE);
	            return; // Salir del método sin intentar cargar datos
	        }
			
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Object objetoCargado = ois.readObject();
			
			// Cargar los datos exclusivamente del jugador
			Superviviente jugador = null;
			if(objetoCargado instanceof Superviviente) {
				jugador = (Superviviente) objetoCargado;
			}
			
			// Después, cargar los datos de la partida
	        int numeroHabitacionTotal = ois.readInt();
	        int intentosBusqueda = ois.readInt();
	        int habitacionActual = ois.readInt();
	        int numeroZombiesActivos = ois.readInt();
	        EstadoPartida estadoPartida = (EstadoPartida) ois.readObject();
	        String dificultad = (String) ois.readObject();
	        
	        Juego juego = new Juego(numeroHabitacionTotal, intentosBusqueda, habitacionActual,
                    numeroZombiesActivos, estadoPartida,jugador, dificultad, this);
	        
	        juego.mostrarInterfaz();
			
			ois.close();
		}catch (EOFException e) {
			// Esta excepción captura el fin de fichero
		
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método utilizado para reproducir una suave y relajante música en el menú.
	 */
	private void reproducirMusica() {
        try {
            // Cargar el archivo de música desde el sistema de archivos
            File file = new File("src"+File.separator+"Music"+File.separator+"entrada.wav"); // Ruta del archivo de música
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

            // Obtener el formato de audio
            AudioFormat format = audioInputStream.getFormat();

            // Crear un Clip para reproducir el audio
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);

            // Abrir el flujo de audio y cargar los datos de audio en el Clip
            clip.open(audioInputStream);

            // Reproducir la música de forma continua
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Método para detener la música.
	 */
	private void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
