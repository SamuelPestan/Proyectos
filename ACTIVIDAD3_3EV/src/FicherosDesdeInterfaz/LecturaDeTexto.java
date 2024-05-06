package FicherosDesdeInterfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class LecturaDeTexto extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private JButton btnCerrar;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame padre = new JFrame();
			LecturaDeTexto dialog = new LecturaDeTexto(padre);
			dialog.mostrarInterfaz();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LecturaDeTexto(JFrame padre) {
		super(padre, true);
	}
	
	public void mostrarInterfaz() {
		setTitle("Texto ");
		setBounds(100, 100, 450, 300);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		
		textArea = new JTextArea();
		leerDatos("src"+File.separator+"files"+File.separator+"Ej2_TextoGuardado.txt");
		scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true); // Configurar para que el texto se envuelva automáticamente
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); // La barra de scroll solo saldrá si es necesario
        scrollPane.setBounds(0, 0, 434, 216); // Establecer los límites del JScrollPane en lugar del JTextArea
        contentPanel.add(scrollPane);
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 434, 190);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this);
		btnCerrar.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		btnCerrar.setBackground(Color.PINK);
		btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCerrar.setBounds(173, 227, 89, 23);
		contentPanel.add(btnCerrar);
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCerrar) {
			dispose();
		}
	}
	
	public void leerDatos(String nombreArchivo) {
		
		File archivo = new File(nombreArchivo);
		
		try {
			// Apertura
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			
			// Lectura
			String linea;
			StringBuilder texto = new StringBuilder();
			
			while((linea = lector.readLine()) != null) {
				 texto.append(linea).append("\n"); // Agregar el salto de línea al final de cada línea
			}
			textArea.setText(texto.toString());
			// Cierre
			lector.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
