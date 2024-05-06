package FicherosDesdeInterfaz;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ScrollPaneConstants;

public class Interfaz extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextArea txt;
	private JButton btnGuardar, btnTxtGuardado;
	private JScrollPane scrollPane;
	private JLabel lblFondo;
	
	private LecturaDeTexto lecTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		lecTxt = new LecturaDeTexto(this);
		this.mostrarInterfaz();
	}
	
	public void mostrarInterfaz() {
		
		setTitle("Texto");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt = new JTextArea();
		scrollPane = new JScrollPane(txt);
		txt.setLineWrap(true); // Configurar para que el texto se envuelva automáticamente
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); // La barra de scroll solo saldrá si es necesario
        scrollPane.setBounds(10, 10, 414, 166); // Establecer los límites del JScrollPane en lugar del JTextArea
        contentPane.add(scrollPane);
		
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnGuardar.setBackground(Color.PINK);
		btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGuardar.setBounds(62, 187, 136, 36);
		contentPane.add(btnGuardar);
		
		btnTxtGuardado = new JButton("Texto guardado");
		btnTxtGuardado.addActionListener(this);
		btnTxtGuardado.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnTxtGuardado.setBackground(Color.PINK);
		btnTxtGuardado.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnTxtGuardado.setBounds(251, 187, 136, 36);
		contentPane.add(btnTxtGuardado);
		
		// Fondo
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(Interfaz.class.getResource("/imgs/fondo.jpg")));
		lblFondo.setBounds(0, -1, 434, 262);
		contentPane.add(lblFondo);
	}
	
	// Este metodo estará escuchando y actuara acorde al botón que sea presionado
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnGuardar) {
			escritura("src"+File.separator+"files"+File.separator+"Ej2_TextoGuardado.txt",true); // Se le pone el valor true para que no sobreesciba
			
			txt.setText("");
		}else if(e.getSource()==btnTxtGuardado) {
			lecTxt.mostrarInterfaz();
		}
	}
	
	public void escritura(String nombreArchivo, boolean apend) {
		
		File archivo = new File(nombreArchivo);
		
		try {
			// Apertura 
			BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, apend));
			
			// Escritura
			escritor.write(txt.getText());
			escritor.newLine();
			
			// Cierre
			escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
