package interface_grafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.ListaRestaurante;
import classes.Restaurante;
import classes.Usuario;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * @author Eduardo Dornelles
 * Está classe cria o menu para adicionar um novo restaurante 
 *  
 */
public class MenuAdicionar extends JFrame implements ActionListener{
	
	private static MenuAdicionar frame = new MenuAdicionar();
	
	private ListaRestaurante restaurantes = new ListaRestaurante();
	JButton btnAdicionar = new JButton("Adicionar");
	private static Usuario usuario;
	private JPanel contentPane;
	private static JTextField textFieldNome;
	private static JTextField textFieldEndereco;
	private static JTextField textFieldDescricao;
	private static JTextField textFieldSite;
	JLabel lblNome = new JLabel("*Nome:");
	JLabel lblEndereco = new JLabel("*Endere\u00E7o:");
	JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
	JLabel lblSite = new JLabel("Site:");
	JLabel lblObs = new JLabel("Campos com (*) s\u00E3o obrigat\u00F3rios!");
	JLabel lblNewLabel = new JLabel("Exemplo: http://www.restaurante.com.br/");
	private final JLabel lblTitulo = new JLabel("Preencha os campos");
	private final JButton btnVoltar = new JButton("Voltar");
	
	public static void abreMenuAdicionar(Usuario usu) {
		try {
			usuario = usu;
			frame.setVisible(true);
			textFieldNome.setText("");
			textFieldEndereco.setText("");
			textFieldSite.setText("");
			textFieldDescricao.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MenuAdicionar() {
		setResizable(false);
		setTitle("Adicionar Restaurante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(135, 45, 211, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(135, 95, 211, 20);
		contentPane.add(textFieldEndereco);
		textFieldEndereco.setColumns(10);
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setBounds(135, 144, 211, 20);
		contentPane.add(textFieldDescricao);
		textFieldDescricao.setColumns(10);
		
		textFieldSite = new JTextField();
		textFieldSite.setBounds(135, 194, 211, 20);
		contentPane.add(textFieldSite);
		textFieldSite.setColumns(10);
		
		
		btnAdicionar.setBounds(135, 281, 89, 23);
		contentPane.add(btnAdicionar);
		
		
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(10, 48, 115, 14);
		contentPane.add(lblNome);
		
		
		lblEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereco.setBounds(10, 98, 115, 14);
		contentPane.add(lblEndereco);
		
		
		lblDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescricao.setBounds(10, 147, 115, 14);
		contentPane.add(lblDescricao);
		
		
		lblSite.setHorizontalAlignment(SwingConstants.CENTER);
		lblSite.setBounds(10, 197, 115, 14);
		contentPane.add(lblSite);
		
		
		lblObs.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblObs.setBounds(103, 259, 185, 14);
		contentPane.add(lblObs);
		
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(135, 217, 221, 14);
		contentPane.add(lblNewLabel);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitulo.setBounds(103, 11, 163, 14);
		
		contentPane.add(lblTitulo);
		btnVoltar.setBounds(10, 281, 89, 23);
		
		contentPane.add(btnVoltar);
		
		btnAdicionar.addActionListener(this);
		btnVoltar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnAdicionar){
			if (textFieldNome.getText().equals("")||textFieldEndereco.getText().equals("")){
				JOptionPane.showMessageDialog(btnAdicionar , "Preencha todos os campos obrigatórios!");
				return;
			} else {
				
				for (Restaurante rest: restaurantes.lista){
					if (rest.getNome().equals(textFieldNome.getText())){
						JOptionPane.showMessageDialog(btnAdicionar , "Restaurante já Cadastrado!");
						return;
					}
					
				}
				Restaurante res = new Restaurante();
				res.setNome(textFieldNome.getText());
				res.setEndereco(textFieldEndereco.getText());
				res.setDescricao(textFieldDescricao.getText());
				res.setSite(textFieldSite.getText());
			
				restaurantes.AdicionarRestaurante(res);
				JOptionPane.showMessageDialog(btnAdicionar , "Restaurante Adicionar com sucesso, Abra o sistema novamente para atualizar a lista de opções");
				frame.setVisible(false);	
				MenuVotar menu = new MenuVotar();				
				menu.abreMenu(usuario);
				
				
			}
		}
		
		if (e.getSource()==btnVoltar){
			frame.setVisible(false);	
			MenuVotar menu = new MenuVotar();				
			menu.abreMenu(usuario);
		}
		
	}
	

}
