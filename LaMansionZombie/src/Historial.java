import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;

public class Historial extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scroll;
	private JComboBox<String> filtro;
	
	private final String RUTA_FICHERO = "src"+File.separator+"Files"+File.separator+"historial";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame padre = new JFrame();
			Historial dialog = new Historial(padre);
			dialog.mostrarInterfaz();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Historial(JFrame padre) {
		super(padre, true);
//		this.mostrarInterfaz();
	}

	public void mostrarInterfaz() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Imgs/icono.jpg")));

		setTitle("Historico");
		setBounds(100, 100, 575, 425);
		setResizable(false);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(SystemColor.windowBorder);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		filtro = new JComboBox<>(new String[]{"Todos", "Victoria", "Derrota"});
		filtro.setBounds(93, 16, 120, 25);
		filtro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		filtro.setForeground(SystemColor.windowText);
		filtro.setBackground(SystemColor.inactiveCaption);
		
		filtro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leerHistorial(RUTA_FICHERO);
            }
        });
        contentPanel.add(filtro);
		
		table = new JTable();
		table.setBounds(10, 70, 539, 305);
		leerHistorial(RUTA_FICHERO);
		
		scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10, 70, 539, 305);
		contentPanel.add(scroll);
		
		JLabel lblFiltrado = new JLabel("Filtrar por:");
		lblFiltrado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltrado.setForeground(SystemColor.textHighlightText);
		lblFiltrado.setBounds(10, 16, 72, 20);
		contentPanel.add(lblFiltrado);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void leerHistorial(String rutaFichero) {
		// Se añaden las columnas a la tabla
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Resultado");
		model.addColumn("Dificultad");
		model.addColumn("Habitación");
		model.addColumn("PV Restantes");
		model.addColumn("¿Botiquín?");
		model.addColumn("Armas");
		model.addColumn("Protecciones");
		
		try {
			BufferedReader lector = new BufferedReader(new FileReader(rutaFichero));
			
			String linea;
			while((linea = lector.readLine()) != null) {
				String[] datos = linea.split("#");
				String filtroSeleccionado = (String) filtro.getSelectedItem();
				if(filtroSeleccionado.equalsIgnoreCase("Victoria")) {
					if(datos[0].equalsIgnoreCase("VICTORIA")) {
						model.addRow(datos);
					}
				}else if(filtroSeleccionado.equalsIgnoreCase("Derrota")) {
					if(datos[0].equalsIgnoreCase("DERROTA")) {
						model.addRow(datos);
					}
				}else {
					model.addRow(datos);
				}
				
			}
			// Cerrar
			lector.close();
			// Añadir la información a la tabla
			table.setModel(model);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
