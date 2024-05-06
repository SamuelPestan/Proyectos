import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PantallaDerrota extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JButton btnCerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialog padre = new JDialog();
			PantallaDerrota dialog = new PantallaDerrota(padre);
			dialog.mostrarInterfaz();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PantallaDerrota(JDialog padre) {
		super(padre,true);
//		mostrarInterfaz();
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
		
		btnCerrar = new JButton("Cerrar");
		formatoBoton(btnCerrar);
		btnCerrar.setBounds(206, 329, 141, 49);
		contentPanel.add(btnCerrar);
		
		JLabel lblVictoria = new JLabel("Nunca escapaste de la \nmansión");
		lblVictoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblVictoria.setForeground(new Color(210, 180, 140));
		lblVictoria.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 32));
		lblVictoria.setBounds(51, 47, 464, 80);
		contentPanel.add(lblVictoria);
		
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

	private void formatoBoton(JButton btn) {
		btn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		btn.addActionListener(this);
		btn.setForeground(new Color(255, 255, 255));
		btn.setBackground(new Color(112, 128, 144));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCerrar) {
			dispose();
		}
	}
}
