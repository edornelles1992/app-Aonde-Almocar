package interface_grafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.ListaRestaurante;

import classes.Restaurante;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.JButton;
/**
 * 
 * @author Eduardo Dornelles
 * Esta Classe é responsavel pela tela dos resultados.
 * Ela apresenta tanto os resultados parciais (antes 12:00)
 * como o resultado final após o horario de votação.
 *
 */
public class MenuResultado extends JFrame implements ActionListener {

	private static String restVotado;
	private JPanel contentPane;
	private static MenuResultado frame = new MenuResultado();

	private static ListaRestaurante restaurantes = new ListaRestaurante();
	private JButton btnLogoff = new JButton("Logoff");
	private static JTextArea lblDisplayVot;
	private JLabel lblResultadoFinal = new JLabel("Vota\u00E7\u00E3o Encerrada");
	private JLabel lblResultado = new JLabel("Resultado at\u00E9 o Momento:");

	public static void abreMenu(String rest) { // abre o menu

		try {

			restVotado = rest;
			
			if (rest.equals("fim")){
				int numVoto = 0;
				String vencedor = new String();
				for (Restaurante res: restaurantes.lista){
					if (res.getVotos()>numVoto){
						numVoto = res.getVotos();
						vencedor = res.getNome();
					}
				}
				restaurantes.gravarSemanaTxt(vencedor);
			}
			frame.setVisible(true);

			mostraResultados();

		} catch (Exception a) {
			a.printStackTrace();
		}

	}

	public MenuResultado() {

		setResizable(false);
		setTitle("Resultados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(24, 11, 214, 36);

		contentPane.add(lblResultado);

		lblDisplayVot = new JTextArea();
		lblDisplayVot.setEditable(false);
		lblDisplayVot.setBounds(24, 58, 214, 245);
		contentPane.add(lblDisplayVot);

		btnLogoff.setBounds(88, 314, 89, 23);
		contentPane.add(btnLogoff);

		lblResultadoFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultadoFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResultadoFinal.setBounds(24, 11, 214, 36);
		contentPane.add(lblResultadoFinal);
		verificaHorario();
		btnLogoff.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogoff) {
			restVotado = "";
			lblDisplayVot.setText("");
			this.setVisible(false);
			Login.abreLogin();

		}

	}

	private static void mostraResultados() {

		ListaRestaurante restaurantes = new ListaRestaurante();
		String aux = new String();
		for (Restaurante res : restaurantes.lista) {
			if (res.getVotos() > 0) {
				aux += res.getNome() + " Votos: " + Integer.toString(res.getVotos()) + "\n";
				lblDisplayVot.setText(aux);
			}
		}

	}

	private void verificaHorario() {
		Calendar calendar = Calendar.getInstance(); // marca meio dia
		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		Date time = calendar.getTime();

		Calendar calendar2 = Calendar.getInstance(); // horario atual
		Date time2 = calendar2.getTime();

		int x = time.compareTo(time2);

		if (x >= 0) {
			lblResultadoFinal.setVisible(false);

		} else {
			lblResultado.setVisible(false);
			
		}
	}
}
