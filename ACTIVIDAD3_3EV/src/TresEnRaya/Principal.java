package TresEnRaya;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;

public class Principal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnJuego;
	private JButton btnHistorial;
	private JLabel lblFondo;

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
		this.Interfaz();
	}
	
	public void Interfaz() {
		setTitle("Tres en Raya");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnJuego = new JButton("Jugar");
		btnJuego.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnJuego.setForeground(Color.WHITE);
		btnJuego.setBackground(new Color(102, 0, 0));
		btnJuego.addActionListener(this);	// Implementar Action Listener
		btnJuego.setBounds(134, 59, 162, 65);
		contentPane.add(btnJuego);
		
		btnHistorial = new JButton("Historial");
		btnHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHistorial.setForeground(Color.WHITE);
		btnHistorial.setBackground(new Color(102, 0, 0));
		btnHistorial.addActionListener(this);	// Implementar Action Listener
		btnHistorial.setBounds(134, 135, 162, 65);
		contentPane.add(btnHistorial);
		
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 436, 261);
		contentPane.add(lblFondo);
		
		// Fondo
		ImageIcon imageIcon = new ImageIcon(Principal.class.getResource("/Imgs/ter1.jpg"));
        Image image = imageIcon.getImage(); // Transforma ImageIcon a Image
        // Ajustar la imagen
        Image newImage = image.getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), java.awt.Image.SCALE_SMOOTH);
        lblFondo.setIcon(new ImageIcon(newImage));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnJuego) {
			Juego juego = new Juego(this);
			juego.Interfaz();
		}else if(e.getSource() == btnHistorial) {
			Historial partidas = new Historial(this);
			partidas.mostrarHistorial();
		}
	}
}
