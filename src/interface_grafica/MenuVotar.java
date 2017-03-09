package interface_grafica;

import java.awt.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.ListaRestaurante;
import classes.ListaUsuario;

import classes.Restaurante;
import classes.Usuario;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;

/**
 * 
 * @author Eduardo Dornelles 
 * Esta classe é responsavel pela tela de votação,
 * disponibiliza a lista de restaurantes disponiveis (excluindo os
 * vencedores na semana atual) na tela para o usuario votar. Mostra ao
 * usuario o endereço e uma breve descrição do restaurante selecionado,
 * bem como mostra a opção de ir para o site (se houver). Também
 * disponibiliza a opção para o Usuario adicionar um novo restaurante.
 * 
 */
public class MenuVotar extends JFrame implements ActionListener {

	private ListaRestaurante restaurantes = new ListaRestaurante();
	private ListaUsuario lstUsuario = new ListaUsuario();
	private static Restaurante restaurante;
	private static Usuario usu;
	private JPanel contentPane;
	private static MenuVotar frame = new MenuVotar();

	private List displayRestaurantes = new List();
	JButton btnAdicionarRest = new JButton("Adicionar Restaurante");
	JLabel lblNewLabel = new JLabel("Bem Vindo(a) Colaborador(a), selecione o restaurante que deseja almoçar hoje:");
	private final JLabel lblNewLabel_1 = new JLabel("Selecione o restaurante para obter detalhes");
	private final JLabel lblNewLabel_end = new JLabel("Endere\u00E7o:");
	private final JLabel lblNewLabel_mostraEnd = new JLabel("");
	private final JLabel lblNewLabel_det = new JLabel("Detalhes:");
	private final JLabel lblNewLabel_mostraDet = new JLabel("");
	private final JButton btnVotar = new JButton("Votar");
	private JLabel lblLink = new JLabel("Ir para o site");
	private Desktop desktop;
	
	public void abreMenu(Usuario atual) { // abre o menu

		try {
			usu = atual;
			restaurantes = new ListaRestaurante();
			frame.setVisible(true);
			HabilitarMenu();
			CarregarRestaurantes();

		} catch (Exception a) {
			a.printStackTrace();
		}

	}

	public MenuVotar() {

		desktop = Desktop.getDesktop();
		setResizable(false);
		setTitle("Aonde Almoçar 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(84, 24, 461, 23);
		contentPane.add(lblNewLabel);
		displayRestaurantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // mostrar as informações
														// em um clique
				restauranteSelecionado();
			}
		});

		displayRestaurantes.setBounds(34, 102, 282, 310);
		contentPane.add(displayRestaurantes);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(34, 82, 282, 14);
		CarregarRestaurantes();

		contentPane.add(lblNewLabel_1);
		lblNewLabel_end.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_end.setBounds(464, 102, 66, 14);

		contentPane.add(lblNewLabel_end);
		lblNewLabel_mostraEnd.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_mostraEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_mostraEnd.setBounds(370, 120, 236, 41);

		contentPane.add(lblNewLabel_mostraEnd);
		lblNewLabel_det.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_det.setBounds(464, 172, 66, 14);

		contentPane.add(lblNewLabel_det);
		lblNewLabel_mostraDet.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_mostraDet.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_mostraDet.setBounds(370, 190, 236, 46);

		contentPane.add(lblNewLabel_mostraDet);
		btnVotar.setBounds(112, 430, 122, 34);

		contentPane.add(btnVotar);

		lblLink.setBackground(Color.LIGHT_GRAY);
		lblLink.setVisible(false);
		lblLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onLaunchBrowser(restaurante.getSite());

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblLink.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLink.setForeground(Color.BLUE);
		lblLink.setHorizontalAlignment(SwingConstants.CENTER);
		lblLink.setBounds(418, 237, 155, 16);
		contentPane.add(lblLink);

		btnAdicionarRest.setBounds(406, 370, 186, 23);
		contentPane.add(btnAdicionarRest);

		displayRestaurantes.addActionListener(this);
		btnVotar.addActionListener(this);
		btnAdicionarRest.addActionListener(this);

	}

	public static void HabilitarMenu() {
		frame.setEnabled(true);
	}

	public void CarregarRestaurantes() { // mostra na tela os restaurantes,
											// tirando os que ja foram
											// escolhidos na semana
		displayRestaurantes.removeAll();

		restaurantes = new ListaRestaurante();
		for (Restaurante res : restaurantes.lista) {
			if (res.getSemanaVisitada() == semanaAtual()) {
				continue;
			}
			displayRestaurantes.add(res.getNome());

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVotar) {
			if (displayRestaurantes.getSelectedItem() != null) {

				try { // salvar nos txts os votos
					if (restaurante.getDiaVoto() != DiaAtual()) {
						// cai nesse if para trocar o dia atual da votação e
						// reiniciar a contagem de votos
						restaurantes.gravarDiaTxt();
						restaurantes.ReiniciarVotos();
					}

					usu.setUltimoVoto(dataAtual());
					lstUsuario.alteraVoto(usu); // gravar txt
					// restaurante.setSemanaVisitada(semanaAtual());
					restaurantes.gravarVotosTxt(displayRestaurantes.getSelectedItem());
					// restaurantes.gravarDiaTxt(displayRestaurantes.getSelectedItem());
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				this.setVisible(false);
				MenuResultado.abreMenu(displayRestaurantes.getSelectedItem());
				JOptionPane.showMessageDialog(btnVotar, "Voto computado, boa sorte!");

				return;
			} else {
				JOptionPane.showMessageDialog(btnVotar, "Selecione um restaurante!");
				return;
			}
		}
		if (e.getSource() == btnAdicionarRest) {
			// this.setVisible(false);
			this.setEnabled(false);
			MenuAdicionar.abreMenuAdicionar(usu);
		}

	}

	private void restauranteSelecionado() { // mostrar endereço e descrição na
											// tela ao selecionar
		if (displayRestaurantes.getSelectedItem() != null) {

			for (Restaurante res : restaurantes.lista) {

				if (res.getNome().equals(displayRestaurantes.getSelectedItem())) {
					restaurante = res;
					if (!restaurante.getSite().equals("n")) {
						lblLink.setVisible(true);
					} else {
						lblLink.setVisible(false);
					}
					lblNewLabel_mostraEnd.setText(res.getEndereco());
					lblNewLabel_mostraDet.setText(res.getDescricao());

				}

			}

		}
	}

	private void onLaunchBrowser(String site) {
		URI uri = null;
		try {
			uri = new URI(site);
			desktop.browse(uri);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "A pagina " + uri + " Não foi localizada");

		} catch (URISyntaxException use) {
			JOptionPane.showMessageDialog(null, "Erro na URL");

		}
	}

	private String dataAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy");
		return formatarDate.format(data);

	}

	private int semanaAtual() {
		Date data = new Date(System.currentTimeMillis());
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		int semana = cal.get(Calendar.WEEK_OF_YEAR);
		return semana;
	}

	private int DiaAtual() {
		Date data = new Date(System.currentTimeMillis());
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		int semana = cal.get(Calendar.DAY_OF_YEAR);
		return semana;
	}

}