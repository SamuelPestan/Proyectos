package sudoku;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class PaginaPrincipal extends JFrame
									implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel titulo;
	private JLabel titulo_1;
	JButton comenzar;
	JComboBox<String> dificultad;
	BufferedImage imagenFondo;
	PantallaFinal pFinal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaPrincipal frame = new PaginaPrincipal();
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
	public PaginaPrincipal() {
		setTitle("Menú prinicipal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Titulo del sudoku
		titulo = new JLabel();
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setText("Sudoku");
		titulo.setBounds(151, 45, 133, 30);
		titulo.setForeground(Color.RED);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		contentPane.add(titulo);
		
		//Botón
		comenzar = new JButton();
		comenzar.setBackground(SystemColor.activeCaption);
		comenzar.setSize(100, 20);
		comenzar.setLocation(172, 115);
		comenzar.setText("Empezar");
		comenzar.addActionListener(this);
		comenzar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(comenzar);
		
		//Texto
		titulo_1 = new JLabel();
		titulo_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		titulo_1.setText("Elige la dificultad:");
		titulo_1.setBounds(66, 155, 127, 20);
		
		contentPane.add(titulo_1);
		
		//Selector de dificultad
		dificultad = new JComboBox<String>();
		dificultad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dificultad.setBackground(UIManager.getColor("Button.background"));
		dificultad.setModel(new DefaultComboBoxModel(new String[] {"Fácil", "Medio", "Difícil", "Muy Fácil"}));
		dificultad.setBounds(203, 155, 133, 20);

		
		contentPane.add(dificultad);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PaginaPrincipal.class.getResource("/Img/samurai1.jpg")));
		label.setBounds(-14, 0, 469, 284);
		contentPane.add(label);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		pFinal= new PantallaFinal();
		Sudoku sudoku = new Sudoku();
		sudoku.vaciarHuecos(dificultad.getSelectedItem());
		sudoku.setVisible(true);
		
		sudoku.comprobarVictoria();
	}
}
