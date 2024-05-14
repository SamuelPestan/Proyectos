import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Clase que despliega el combate contra los Zombies en la mansión.
 * 
 * @author Samuel A. Moreno Pestana
 * @version 1.8
 */
public class MenuCombate extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JTextField textVidaJugador;
	private JTextField textAtkJugador;
	private JTextField textVidaZombie;
	private JTextField textAtkZombie;
	private JButton btnLuchar;
	private JTextArea textPaneCombate;
	
	private int turno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialog padre = new JDialog();
			Superviviente jugador = new Superviviente();
			Zombie zombie = new Zombie(1);
			MenuCombate dialog = new MenuCombate(padre,jugador,zombie);
			
			dialog.mostrarInterfaz(jugador, zombie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MenuCombate(JDialog dialog, Superviviente jugador, Zombie zombie) {
		super(dialog, true);
		turno=1;
//		this.mostrarInterfaz(jugador, zombie);
	}
	
	/**
	 * Método que despliega el apartado visual del menú de combate.
	 * 
	 * @param jugador Objeto superviviente que representa al jugador.
	 * @param zombie Objeto que representa al zombie.
	 */
	public void mostrarInterfaz(Superviviente jugador, Zombie zombie) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Imgs/icono.jpg")));

		setTitle("Juego ");
		setBounds(100, 100, 575, 425);
		setResizable(false);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblVidaJugador = new JLabel("Jugador PV:");
		lblVidaJugador.setBounds(32, 35, 78, 13);
		formatoTexto(lblVidaJugador);
		contentPanel.add(lblVidaJugador);
		
		JLabel lblJugadorAtaque = new JLabel("Jugador ATK:");
		lblJugadorAtaque.setBounds(32, 83, 78, 13);
		formatoTexto(lblJugadorAtaque);
		contentPanel.add(lblJugadorAtaque);
		
		JLabel lblZombiePV = new JLabel("Zombie PV:");
		lblZombiePV.setBounds(183, 36, 72, 13);
		formatoTexto(lblZombiePV);
		contentPanel.add(lblZombiePV);
		
		JLabel lblZombieATK = new JLabel("Zombie ATK:");
		lblZombieATK.setBounds(183, 83, 88, 13);
		formatoTexto(lblZombieATK);
		contentPanel.add(lblZombieATK);
		
		textVidaJugador = new JTextField(String.valueOf(jugador.getVidaActual()));
		textVidaJugador.setBounds(117, 33, 40, 20);
		formatoTextField(textVidaJugador);
		contentPanel.add(textVidaJugador);
		textVidaJugador.setColumns(10);
		
		textAtkJugador = new JTextField(String.valueOf(jugador.getPuntosAtaques()));
		textAtkJugador.setBounds(117, 80, 40, 20);
		formatoTextField(textAtkJugador);
		contentPanel.add(textAtkJugador);
		textAtkJugador.setColumns(10);
		
		textVidaZombie = new JTextField(String.valueOf(zombie.getVidaZombie()));
		textVidaZombie.setColumns(10);
		formatoTextField(textVidaZombie);
		textVidaZombie.setForeground(Color.RED);
		textVidaZombie.setBounds(262, 33, 40, 20);
		contentPanel.add(textVidaZombie);
		
		textAtkZombie = new JTextField(String.valueOf(zombie.getAtaqueZombie()));
		textAtkZombie.setColumns(10);
		formatoTextField(textAtkZombie);
		textAtkZombie.setForeground(Color.RED);
		textAtkZombie.setBounds(262, 80, 40, 20);
		contentPanel.add(textAtkZombie);
		
		btnLuchar = new JButton("Luchar");
		btnLuchar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combate(jugador,zombie);
			}
		});
		btnLuchar.setBounds(375, 32, 122, 64);
		formatoBoton(btnLuchar);
		contentPanel.add(btnLuchar);
		
		textPaneCombate = new JTextArea();
		textPaneCombate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textPaneCombate.setBounds(32, 149, 270, 213);
		textPaneCombate.setLineWrap(true);
		textPaneCombate.setEditable(false);
		textPaneCombate.setBackground(Color.black);
		textPaneCombate.setForeground(Color.white);
		
		
		JScrollPane panelScroll = new JScrollPane(textPaneCombate);
		panelScroll.setBounds(32, 149, 270, 213); 
		panelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		contentPanel.add(panelScroll);
		
		// Asignar el fondo
		JLabel lblFondo = new JLabel("");
		ImageIcon icon = new ImageIcon(MenuCombate.class.getResource("/Imgs/zombies.jpg"));
		lblFondo.setBounds(0, 0, 571, 400);
        Image img = icon.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH);
        lblFondo.setIcon(new ImageIcon(img));
		contentPanel.add(lblFondo);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/*	Metodos visuales	*/
	
	/**
	 * Método que se encarga de definir el formato de los campo de texto en la clase MenuCombate.
	 * 
	 * @param lbl JLabel que recibe el formato.
	 */
	private void formatoTexto(JLabel lbl) {
		lbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lbl.setForeground(Color.WHITE);
	}
	
	/**
	 * Método que se encarga de definir el formato de los campo de texto en la clase MenuCombate.
	 * 
	 * @param textField JTextField que recibe el formato.
	 */
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
	
	/**
	 * Método que se encarga de definir el formato del botón en la clase MenuCombate.
	 * 
	 * @param btn Botón que recibe el formato.
	 */
	private void formatoBoton(JButton btn) {
		btn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(112, 128, 144));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	/*	Metodos referentes a la funcionalidad de código	*/
	
	/**
	 * Método que se encarga de gestionar el combate de la clase MenuCombate.
	 * 
	 * @param jugador Objeto que representa al jugador.
	 * @param zombie Objeto que representa al Zombie.
	 */
	public void combate(Superviviente jugador, Zombie zombie) {

		if(jugador.getVidaActual() > 0 && zombie.getVidaZombie() > 0) {
			textPaneCombate.append("Turno " + turno +"\n");
			int dañoJugador = jugador.ataqueSuperviviente();
			zombie.recibirDanoZombie(dañoJugador);
			textPaneCombate.append("El jugador ataca: " + dañoJugador +"\n");
			if(zombie.getVidaZombie() > 0) {
				int dañoZombie = zombie.ataqueZombie();
				jugador.recibirDanoJugador(dañoZombie);
				textPaneCombate.append("El zombie ataca: " + dañoZombie +"\n");
			}
			actualizarInterfaz(jugador,zombie);
			turno++;
		}
	}
	
	/**
	 * Método que actualiza los campos de la interfaz actualizandolos según su estado actual.
	 * 
	 * @param jugador Objeto que representa al jugador.
	 * @param zombie Objeto que representa al Zombie.
	 */
	public void actualizarInterfaz(Superviviente jugador, Zombie zombie) {
	    // Actualizar el campo de texto de la vida del jugador
	    textVidaJugador.setText(String.valueOf(jugador.getVidaActual()));

	    // Actualizar el campo de texto de la vida del zombie
	    textVidaZombie.setText(String.valueOf(zombie.getVidaZombie()));

	    // Verificar si el zombie está muerto (vida <= 0) para mostrar un mensaje
	    if (zombie.getVidaZombie() <= 0) {
	        textPaneCombate.append("El zombie ha sido derrotado.\n");
	        btnLuchar.setEnabled(false); // Deshabilitar el botón de combate si el zombie está muerto
	    }

	    // Verificar si el jugador está muerto (vida <= 0) para mostrar un mensaje
	    if (jugador.getVidaActual() <= 0) {
	        textPaneCombate.append("El jugador ha sido derrotado.\n");
	        btnLuchar.setEnabled(false);
	        dispose();
	    }
	}
	
	/**
	 * Método que devuelve el resultado del combate.
	 * 
	 * @param jugador Objeto que representa al jugador.
	 * @param zombie Objeto que representa al Zombie.
	 * @return Resultado del combate.
	 */
	public EstadoCombate EstadoActual(Superviviente jugador, Zombie zombie) {
		if (zombie.getVidaZombie() <= 0) {
			return EstadoCombate.VICTORIA;
		}else if (jugador.getVidaActual() <= 0) {
			return EstadoCombate.DERROTA;
		}else {
			return EstadoCombate.EN_COMBATE;
		}
	}
}
