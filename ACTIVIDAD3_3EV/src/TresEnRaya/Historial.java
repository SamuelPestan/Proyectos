package TresEnRaya;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

public class Historial extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JList<String> listHistorial;
	private JScrollPane scroll;
	private final String RUTA_ARCHIVO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame padre = new JFrame();
			Historial dialog = new Historial(padre);
			dialog.mostrarHistorial();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Historial(JFrame padre) {
		super(padre, true);
		RUTA_ARCHIVO = "src"+File.separator+"files"+File.separator+"Ej3_Historial";
	}
	
	public void mostrarHistorial() {
		setTitle("Historial");
		setBounds(100, 100, 450, 300);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		// Crear el modelo de lista
		DefaultListModel<String> modeloLista = new DefaultListModel<>();
		
		listHistorial = new JList<String>(modeloLista);
		listHistorial.setBounds(0, 11, 434, 250);
		
		try {
			// Apertura
			File archivo = new File(RUTA_ARCHIVO);
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea;
			// Lee la linea y la agrega al JList
			while((linea = lector.readLine()) != null) {
				modeloLista.addElement(linea);
			}
			// Cerrar el lector
			lector.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Panel de scroll para el JList
		scroll = new JScrollPane(listHistorial);
		scroll.setBounds(10, 11, 414, 239);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPanel.add(scroll);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
