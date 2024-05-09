import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Juego extends  JDialog implements ActionListener, WindowListener, Serializable  {

	private static final long serialVersionUID = 1L;
	
    // Este es el Objeto referente al jugador
    Superviviente jugador;
    
	// Variables del juego
	private int numeroHabitacionTotal;
    private int intentosBusqueda;
    private int habitacionActual;
    private int numeroZombiesActivos;
    private EstadoPartida estadoPartida;
    private String dificultad;
    
    // Variables visuales
	private JPanel contentPanel = new JPanel();
	private JLabel lblFondoActual;
	private JButton btnGuardar;
    private JButton btnLuchar;
    private JButton btnCurarse;
    private JButton btnBuscar;
    private JButton btnAvanzar;
    private JTextField textVida;
    private JTextField textProc;
    private JTextField textArmas;
    private JTextField textBoti;
    private JTextField textIntentos;
    private JTextField textHabitacion;
    private JTextField textZombies;
    
    /* Esta variable no es serializable para evitar que de error basta con escribir el
     * parametro "transient" no obstante en este caso se guardaran los datos de otra forma. */
    private Clip clip;
    private JButton btnMusica;
    
    private final String RUTA_FICHERO = "src"+File.separator+"Files"+File.separator+"datos";
    private final String RUTA_HISTORIAL = "src"+File.separator+"Files"+File.separator+"historial";

	public int getNumeroHabitacionTotal() {
		return numeroHabitacionTotal;
	}

	public void setNumeroHabitacionTotal(int numeroHabitacionTotal) {
		this.numeroHabitacionTotal = numeroHabitacionTotal;
	}

	public int getIntentosBusqueda() {
		return intentosBusqueda;
	}

	public void setIntentosBusqueda(int intentosBusqueda) {
		this.intentosBusqueda = intentosBusqueda;
	}

	public int getHabitacionActual() {
		return habitacionActual;
	}

	public void setHabitacionActual(int habitacionActual) {
		this.habitacionActual = habitacionActual;
	}

	public int getNumeroZombiesActivos() {
		return numeroZombiesActivos;
	}

	public void setNumeroZombiesActivos(int numeroZombiesActivos) {
		this.numeroZombiesActivos = numeroZombiesActivos;
	}

	public EstadoPartida getEstadoPartida() {
		return estadoPartida;
	}

	public void setEstadoPartida(EstadoPartida estadoPartida) {
		this.estadoPartida = estadoPartida;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame padre = new JFrame();
			Superviviente jugador = new Superviviente();
			Juego dialog = new Juego(5,3,1,1,EstadoPartida.EN_PARTIDA,jugador,"Fácil",padre);
			dialog.mostrarInterfaz();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public Juego(int numeroHabitacionTotal, int intentosBusqueda, int habitacionActual, 
			int numeroZombiesActivos, EstadoPartida estadoPartida, Superviviente jugador,
			String dificultad,JFrame padre) {
		super(padre, true);
		this.numeroHabitacionTotal = numeroHabitacionTotal;
		this.intentosBusqueda = intentosBusqueda;
		this.habitacionActual = habitacionActual;
		this.numeroZombiesActivos = numeroZombiesActivos;
		this.estadoPartida = estadoPartida;
		this.jugador=jugador;
		this.dificultad=dificultad;
//		this.mostrarInterfaz();
	}

	public void mostrarInterfaz() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Imgs/icono.jpg")));

		setTitle("Juego ");
		setBounds(100, 100, 575, 425);
		setResizable(false);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblVida = new JLabel("Puntos de vida:");
		lblVida.setBounds(10, 30, 104, 14);
		formatoTexto(lblVida);
		contentPanel.add(lblVida);
		
		JLabel lblProct = new JLabel("Cantidad protecciones:");
		lblProct.setBounds(10, 70, 194, 14);
		formatoTexto(lblProct);
		contentPanel.add(lblProct);
		
		JLabel lblArmas = new JLabel("Cantidad Armas:");
		lblArmas.setBounds(10, 110, 120, 14);
		formatoTexto(lblArmas);
		contentPanel.add(lblArmas);
		
		JLabel lblBotiquin = new JLabel("¿Botiquín?");
		lblBotiquin.setBounds(10, 150, 104, 14);
		formatoTexto(lblBotiquin);
		contentPanel.add(lblBotiquin);
		
		JLabel lblIntentos = new JLabel("Intentos:");
		lblIntentos.setBounds(10, 190, 86, 14);
		formatoTexto(lblIntentos);
		contentPanel.add(lblIntentos);
		
		JLabel lblHabitacion = new JLabel("Habitación:");
		lblHabitacion.setBounds(10, 230, 104, 14);
		formatoTexto(lblHabitacion);
		contentPanel.add(lblHabitacion);
		
		JLabel lblZombies = new JLabel("Zombies:");
		lblZombies.setBounds(10, 270, 104, 14);
		formatoTexto(lblZombies);
		contentPanel.add(lblZombies);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 322, 155, 53);
		formatoBoton(btnGuardar);
		contentPanel.add(btnGuardar);
		
		btnAvanzar = new JButton("Avanzar");
		btnAvanzar.setBounds(417, 266, 89, 23);
		btnAvanzar.setEnabled(numeroZombiesActivos > 0 ? false : true);
		formatoBoton(btnAvanzar);
		contentPanel.add(btnAvanzar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(416, 226, 89, 23);
		btnBuscar.setEnabled((numeroZombiesActivos > 0 || intentosBusqueda <= 0) ? false : true);
		formatoBoton(btnBuscar);
		contentPanel.add(btnBuscar);
		
		btnCurarse = new JButton("Curarse");
		btnCurarse.setBounds(416, 186, 89, 23);
		formatoBoton(btnCurarse);
		btnCurarse.setEnabled((numeroZombiesActivos > 0 || !jugador.getTieneBotiquin()) ? false : true);
		contentPanel.add(btnCurarse);
		
		btnLuchar = new JButton("Luchar");
		btnLuchar.setBounds(417, 146, 89, 23);
		btnLuchar.setEnabled(numeroZombiesActivos <= 0 ? false : true);
		formatoBoton(btnLuchar);
		contentPanel.add(btnLuchar);
		
		textVida = new JTextField(String.valueOf(jugador.getVidaActual()));
		textVida.setBounds(190, 28, 40, 20);
		formatoTextField(textVida);
		contentPanel.add(textVida);
		textVida.setColumns(10);
		
		textProc = new JTextField(String.valueOf(jugador.getCantidadProteccion()));
		textProc.setBounds(190, 67, 40, 20);
		formatoTextField(textProc);
		contentPanel.add(textProc);
		textProc.setColumns(10);
		
		textArmas = new JTextField(String.valueOf(jugador.getCantidadArmas()));
		textArmas.setBounds(190, 107, 40, 20);
		formatoTextField(textArmas);
		contentPanel.add(textArmas);
		textArmas.setColumns(10);
		
		textBoti = new JTextField();
		textBoti.setText(jugador.getTieneBotiquin() ? "Si" : "No");
		formatoTextField(textBoti);
		textBoti.setBounds(190, 147, 40, 20);
		contentPanel.add(textBoti);
		textBoti.setColumns(10);
		
		textIntentos = new JTextField(String.valueOf(intentosBusqueda));
		textIntentos.setBounds(190, 187, 40, 20);
		formatoTextField(textIntentos);
		contentPanel.add(textIntentos);
		textIntentos.setColumns(10);
		
		textHabitacion = new JTextField(String.valueOf(habitacionActual));
		textHabitacion.setBounds(190, 227, 40, 20);
		formatoTextField(textHabitacion);
		contentPanel.add(textHabitacion);
		textHabitacion.setColumns(10);
		
		textZombies = new JTextField(String.valueOf(numeroZombiesActivos));
		textZombies.setBounds(190, 267, 40, 20);
		formatoTextField(textZombies);
		// Asigna el color del texto a rojo, lo añado aquí para que se aplique sobre el color verde que viene con el formato.
		textZombies.setForeground(Color.RED);
		contentPanel.add(textZombies);
		textZombies.setColumns(10);
		
		btnMusica = new JButton("Musica");
		btnMusica.setBounds(466, 10, 85, 21);
		formatoBoton(btnMusica);
		contentPanel.add(btnMusica);
		
		
		lblFondoActual = new JLabel("");
		lblFondoActual.setBounds(0, 0, 575, 425);
		actualizarImagen();
		contentPanel.add(lblFondoActual);
		
		// Gestionar la reproducción de música según el estado de la partida
        gestionarMusica(getEstadoPartida());
        
        //	Con esto se tendrá en cuenta el estado de la ventana
        addWindowListener(this);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/*	METODOS VISUALES	*/
	
	private void formatoTextField(JTextField textField) {
        // Establece la fuente y el tamaño del texto
        textField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        
        // Establece el color del texto y del fondo
        textField.setBackground(Color.black);
        textField.setForeground(Color.GREEN);
        
        // Hacer que no sea editable
        textField.setEditable(false);
        
        // Centrar el texto dentro del JTextField
        DefaultCaret caret = (DefaultCaret) textField.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE); // Evitar que el caret (cursor) parpadee
        textField.setHorizontalAlignment(JTextField.CENTER); // Centrar el texto horizontalmente
    }
	
	private void formatoBoton(JButton btn) {
		btn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(112, 128, 144));
		btn.addActionListener(this);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	private void formatoTexto(JLabel lbl) {
		lbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lbl.setForeground(Color.WHITE);
	}
	
	public void actualizarImagen() {
        String rutaImagen = imagenAleatoria();
        ImageIcon icon = new ImageIcon(getClass().getResource(rutaImagen));
        Image img = icon.getImage().getScaledInstance(lblFondoActual.getWidth(), lblFondoActual.getHeight(), Image.SCALE_SMOOTH);
        lblFondoActual.setIcon(new ImageIcon(img));
    }
	
	/**
	 * Metodo que devuelve una imagen dependiendo de la habitación en la que el jugador se encuentre.
	 * 
	 * @return Imagen que se asignara como fondo.
	 */
	private String imagenAleatoria() {
        
		switch(getHabitacionActual()) {
		case 1:
			return "/Imgs/Habitacion1.jpg";
		case 2:
			return "/Imgs/Habitacion2.png";
		case 3:
			return "/Imgs/Habitacion3.jpg";
		case 4:
			return "/Imgs/Habitacion4.jpg";
		case 5: 
			return "/Imgs/Habitacion5.jpg";
		case 6: 
			return "/Imgs/Habitacion6.jpg";
		case 7: 
			return "/Imgs/Habitacion7.jpg";
		case 8: 
			return "/Imgs/Habitacion8.jpg";
		case 9: 
			return "/Imgs/Habitacion9.jpg";
		case 10: 
			return "/Imgs/Habitacion10.jpg";
			default:
			return "/Imgs/Habitacion1.jpg";
		}
	}
	
	// Método para cargar y reproducir la música
    private void reproducirMusica() {
        try {
            // Cargar el archivo de música desde el sistema de archivos
            File file = new File("src"+File.separator+"Music"+File.separator+"sound.wav"); // Ruta del archivo de música
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
    
    // Método para detener la reproducción de música
    private void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    
    // Método para gestionar la reproducción de música según el estado de la partida
    private void gestionarMusica(EstadoPartida estado) {
        if (estado == EstadoPartida.EN_PARTIDA) {
            reproducirMusica(); // Reproducir música cuando la partida está en curso
        } else {
            detenerMusica(); // Detener música en cualquier otro estado
        }
    }
	
	/*	METODOS REFERENTES A LA FUNCIONALIDAD DEL CÓDIGO	*/

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCurarse) {
			jugador.curarse();
			textBoti.setText(jugador.getTieneBotiquin() ? "Si" : "No");
			textVida.setText(String.valueOf(jugador.getVidaActual()));
			actualizarBotones();
		}
		if(e.getSource() == btnLuchar) {
			combate();
		}
		
		if(e.getSource()==btnAvanzar) {
			avanzar();	
		}
		
		if(e.getSource()==btnBuscar) {
			buscar();
		}
		
		if(e.getSource() == btnMusica) {
			if (clip != null && clip.isRunning()) {
	            detenerMusica();
	        } else {
	            reproducirMusica();
	        }
		}
		
		if(e.getSource() == btnGuardar) {
			guardarPartida(RUTA_FICHERO);
		}
	}
	
	public void combate() {
		if(getNumeroZombiesActivos() > 0) {
			Zombie zombie = new Zombie(getHabitacionActual());
			MenuCombate combate = new MenuCombate(this, jugador, zombie);
			combate.mostrarInterfaz(jugador, zombie);
			// Verificar el estado de la partida después del combate
            EstadoCombate estado = combate.EstadoActual(jugador, zombie);
            if (estado == EstadoCombate.VICTORIA) {
            	textVida.setText(String.valueOf(jugador.getVidaActual()));
        		setNumeroZombiesActivos(getNumeroZombiesActivos() - 1); // Reducir el número de zombies activos
                textZombies.setText(String.valueOf(getNumeroZombiesActivos())); // Actualizar el campo visual
                actualizarBotones();
            }else if(estado == EstadoCombate.DERROTA) {
            	this.estadoPartida = EstadoPartida.DERROTA;
            	guardarHistorial(RUTA_HISTORIAL,true);
            	PantallaDerrota pDerrota = new PantallaDerrota(this);
            	pDerrota.mostrarInterfaz();
            	dispose();
            }
		}
	}
	
	private void actualizarBotones() {
		// Habilitar o deshabilitar el botón de búsqueda
	    btnBuscar.setEnabled(numeroZombiesActivos == 0 && intentosBusqueda > 0);

	    // Habilitar o deshabilitar el botón de curarse
	    btnCurarse.setEnabled(numeroZombiesActivos == 0 && jugador.getTieneBotiquin());

	    // Habilitar o deshabilitar el botón de luchar
	    btnLuchar.setEnabled(numeroZombiesActivos > 0);

	    // Habilitar o deshabilitar el botón de avanzar
	    btnAvanzar.setEnabled(numeroZombiesActivos == 0);
	}
	
	public void avanzar() {
		if(habitacionActual < numeroHabitacionTotal) {
			// Avanzar a la siguiente habitación
			habitacionActual++;
			numeroZombiesActivos++;
			intentosBusqueda=3;
			
			// Actualizar botones
			textZombies.setText(String.valueOf(numeroZombiesActivos));
			textHabitacion.setText(String.valueOf(habitacionActual));
			actualizarBotones();
			
			// Actualizar fondo
			actualizarImagen();
			
			// Actualizar texto
			btnAvanzar.setText(habitacionActual == numeroHabitacionTotal ? "Salir" : "Avanzar");
		}else {
			this.estadoPartida = EstadoPartida.VICTORIA;
			guardarHistorial(RUTA_HISTORIAL,true);
			// Imagen de victoria
			PantallaVictoria pVictoria = new PantallaVictoria(this);
			pVictoria.mostrarInterfaz();
			dispose();
		}
		
	}
	
	public void buscar() {
    	Dado rnd = new Dado(100);
    	int lanzarDado = rnd.lanzarDado();
    	String text = "";
    	if (lanzarDado > 1 && lanzarDado < 75) {
    		// Haces ruido
    			if(lanzarDado > 1 && lanzarDado < 40){
    				// No encuentras nada
    				text = "No encuentras nada";
    				intentosBusqueda--;
    				textIntentos.setText(String.valueOf(intentosBusqueda));
    				actualizarBotones();
    			}else if (lanzarDado > 41 && lanzarDado < 80) {
    				// Entra un Zombie
    				text = "Entra un zombie a la habitación";
    				numeroZombiesActivos = 1;
    				intentosBusqueda--;
    				textIntentos.setText(String.valueOf(intentosBusqueda));
    				textZombies.setText(String.valueOf(numeroZombiesActivos));
    				actualizarBotones();
    			}else if(lanzarDado > 81 && lanzarDado < 100){
    				// Entran dos Zombies.
    				text = "Entran dos zombies a la habitación";
    				numeroZombiesActivos = 2;
    				intentosBusqueda--;
    				textIntentos.setText(String.valueOf(intentosBusqueda));
    				textZombies.setText(String.valueOf(numeroZombiesActivos));
    				actualizarBotones();
    			}
    			JOptionPane.showMessageDialog(this, text, "Haces ruido",JOptionPane.INFORMATION_MESSAGE);
    			
	    }else if (lanzarDado > 76 && lanzarDado < 90) {
	    	//	Encuentras botiquín
	    	text = "Has encontrado un botiquin";
	    	jugador.setTieneBotiquin(true);
	    	intentosBusqueda--;
	    	textIntentos.setText(String.valueOf(intentosBusqueda));
	    	actualizarBotones();
	    	textBoti.setText(jugador.getTieneBotiquin() ? "Si" : "No");
	    	JOptionPane.showMessageDialog(this, text, "Felicidades",JOptionPane.INFORMATION_MESSAGE);
	    		
	    }else if (lanzarDado > 91 && lanzarDado < 95) {
	    	// Encuentras protección
	    	text = "Has encontrado una protección";
	    	jugador.setCantidadProteccion(jugador.getCantidadProteccion() + 1);
	    	intentosBusqueda--;
	    	textIntentos.setText(String.valueOf(intentosBusqueda));
	    	textProc.setText(String.valueOf(jugador.getCantidadProteccion()));
	    	actualizarBotones();
	    	JOptionPane.showMessageDialog(this, text, "Felicidades",JOptionPane.INFORMATION_MESSAGE);
	    		
	    }else if (lanzarDado > 95 && lanzarDado < 100) {
	    	// Encuentras un arma
	    	text = "Has encontrado un arma";
	    	jugador.setCantidadArmas(jugador.getCantidadArmas() + 1);
	    	intentosBusqueda--;
	    	textIntentos.setText(String.valueOf(intentosBusqueda));
	    	textArmas.setText(String.valueOf(jugador.getCantidadArmas()));
	    	actualizarBotones();
	    	JOptionPane.showMessageDialog(this, text, "Felicidades",JOptionPane.INFORMATION_MESSAGE);
    	}
    }
	
	public void guardarPartida(String rutaFichero) {
		
		File fichero = new File(rutaFichero);
		
		try {
			if(!fichero.exists())
				fichero.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(fichero);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			// Escribir
			oos.writeObject(jugador);
			
			// Escribir solo las variables que se van a serializar
			oos.writeInt(numeroHabitacionTotal);
			oos.writeInt(intentosBusqueda);
			oos.writeInt(habitacionActual);
			oos.writeInt(numeroZombiesActivos);
			oos.writeObject(estadoPartida);
			oos.writeObject(dificultad);
			
			// Cerrar
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarHistorial(String rutaHistorial, boolean apend) {
		File archivo = new File(rutaHistorial);
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, apend));
			String tieneBotiquin = jugador.getTieneBotiquin() ? "Si" : "No";
			
			// Escribir
			escritor.write(estadoPartida + "#" + dificultad + "#" + habitacionActual + "#" + jugador.getVidaActual() + "#" +
					tieneBotiquin + "#" + jugador.getCantidadArmas() + "#" + jugador.getCantidadProteccion());
			escritor.newLine();
			
			
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*Método para que se escuche el estado de la ventana*/

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		detenerMusica();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		detenerMusica();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
}
