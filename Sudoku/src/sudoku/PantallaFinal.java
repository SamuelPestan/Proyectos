package sudoku;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PantallaFinal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel txt;
	private JButton btnAceptar;
	private JButton btnCerrar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaFinal frame = new PantallaFinal();
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
	public PantallaFinal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Felicidades");
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Texto
		txt = new JLabel();
		txt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		txt.setForeground(new Color(0, 0, 0));
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		txt.setBounds(10, 48, 275, 52);
		txt.setText("¡Resolviste el sudoku!");
		contentPane.add(txt);
		
		//Boton1
		btnAceptar = new JButton();
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PaginaPrincipal pg = new PaginaPrincipal();
				pg.setVisible(true);
				
			}
		});
		btnAceptar.setBackground(SystemColor.activeCaption);
		btnAceptar.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btnAceptar.setText("Reiniciar");
		btnAceptar.setBounds(51, 182, 109, 27);
		btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnAceptar);
		
		//Boton2
		btnCerrar = new JButton();
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //Hace que el programa se cierre
				
			}
		});
		btnCerrar.setBackground(SystemColor.activeCaption);
		btnCerrar.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btnCerrar.setText("Cerrar");
		btnCerrar.setBounds(286, 182, 109, 27);
		btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnCerrar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 475, 283);
		contentPane.add(lblNewLabel);
		
		// Obtener la imagen original
        ImageIcon imagenInicial = new ImageIcon(PantallaFinal.class.getResource("/Img/samuraifeliz.jpg"));
        // Obtener las dimensiones del label
        int anchoLabel = lblNewLabel.getWidth();
        int altoLabel = lblNewLabel.getHeight();
        // Escalar la imagen para que quepa en el label
        Image imagenEscalada = imagenInicial.getImage().getScaledInstance(anchoLabel, altoLabel, Image.SCALE_SMOOTH);
        // Crear un nuevo ImageIcon con la imagen escalada
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        // Establecer el ImageIcon escalado en el JLabel
        lblNewLabel.setIcon(iconoEscalado);
		
	}

}
