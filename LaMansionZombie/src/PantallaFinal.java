import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * Clase que muestra una pantalla de victoria o derrota dependiendo el resultado.
 * 
 * @author Samuel A. Moreno Pestana
 * @version 1.8
 */
public class PantallaFinal extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JButton btnCerrar;
	
	private EstadoPartida estado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialog padre = new JDialog();
			PantallaFinal dialog = new PantallaFinal(padre,EstadoPartida.VICTORIA);
			dialog.mostrarInterfaz();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PantallaFinal(JDialog dialog, EstadoPartida estado) {
		super(dialog, true);
		this.estado = estado;
//		mostrarInterfaz();
	}
	
	/**
	 * Método que contiene la interfaz visual de la clase Pantalla Final
	 */
	public void mostrarInterfaz() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Imgs/icono.jpg")));

		setTitle("Juego ");
		setBounds(100, 100, 575, 425);
		setResizable(false);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		btnCerrar = new JButton("Cerrar");
		formatoBoton(btnCerrar);
		btnCerrar.setBounds(206, 329, 141, 49);
		contentPanel.add(btnCerrar);
		
		JLabel lblResultado = new JLabel();
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setForeground(new Color(210, 180, 140));
		lblResultado.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 32));
		
		// Asignar el texto dependiendo del resultado.
		if(estado == EstadoPartida.VICTORIA) {
			lblResultado.setText("Has escapado de la mansión");
			lblResultado.setBounds(69, 79, 405, 35);
		}else if(estado == EstadoPartida.DERROTA) {
			lblResultado.setText("Nunca escapaste de la mansión");
			lblResultado.setBounds(51, 47, 464, 80);
		}
		
		contentPanel.add(lblResultado);
		
		// Asignar el fondo
		JLabel lblFondo = new JLabel("");
		ImageIcon icon = new ImageIcon(MenuCombate.class.getResource("/Imgs/victoria.jpg"));
		lblFondo.setBounds(0, 0, 571, 400);
		Image img = icon.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_SMOOTH);
		lblFondo.setIcon(new ImageIcon(img));
		contentPanel.add(lblFondo);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Método que se encarga de asignarle un formato al botón.
	 * 
	 * @param btn Botón que recibe el formato.
	 */
	private void formatoBoton(JButton btn) {
		btn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btn.addActionListener(this);
		btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(112, 128, 144));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	/**
	 * Método heredado que se encarga de gestionar las acciones del botón.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCerrar) {
			dispose();
		}
	}
}
