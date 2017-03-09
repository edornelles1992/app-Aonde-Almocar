package interface_grafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import classes.ListaUsuario;
import classes.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
/**
 * 
 * @author Eduardo Dornelles
 *	Está Classe é responsavel pela tela inicial do sistema
 *	Solicita o login e senha do usuario para liberar o acesso a votação e/ou resultados.
 */
public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private ListaUsuario lstUsuario = new ListaUsuario();
	private JLabel lblBemVindo = new JLabel("Bem Vindo! Informe seus dados:");
	private JButton btnAcessar = new JButton("Acessar");
	private JTextField textFieldLogin = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel lblLogin = new JLabel("Login:");
	private JLabel lblSenha = new JLabel("Senha:");
	private static Login frame = new Login();

	public static void abreLogin() {
		try {
			
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Login() {
		setResizable(false);
		setTitle("Aonde Almoçar 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindo.setBounds(10, 50, 264, 14);
		contentPane.add(lblBemVindo);

		btnAcessar.setBounds(100, 246, 89, 23);
		contentPane.add(btnAcessar);

		textFieldLogin.setBounds(103, 116, 86, 20);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);

		passwordField.setBounds(103, 186, 86, 20);
		contentPane.add(passwordField);

		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(53, 119, 46, 14);
		contentPane.add(lblLogin);

		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setBounds(53, 189, 46, 14);
		contentPane.add(lblSenha);

		btnAcessar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnAcessar) {
			
			String login = textFieldLogin.getText();			
			String senha = passwordField.getText();
		
			for (Usuario usu : lstUsuario.lista) {
				if (usu.getLogin().equals(login)) {
					
					if (usu.getSenha().equals(senha)) {
						if (!verificaHorario()){ //fora do horario

							MenuResultado.abreMenu("fim");
							frame.setVisible(false);
							JOptionPane.showMessageDialog(btnAcessar, "Votação de hoje encerrada! Confira os resultados");
							return;
						}							
						if (usu.getUltimoVoto().equals(dataAtual())){ //ja votou mas a votação não encerrou
							MenuResultado.abreMenu("andamento");
							frame.setVisible(false);
							JOptionPane.showMessageDialog(btnAcessar, "Você já votou hoje, mas ainda restam seus colegas! Confira os resultados parciais");
						} else {
						MenuVotar novo = new MenuVotar(); //vai para o menu de votação
						novo.abreMenu(usu);
						frame.setVisible(false);		
						}				
						return;
					}
					JOptionPane.showMessageDialog(btnAcessar, "Senha Incorreta!");
					return;
				}

			}
			JOptionPane.showMessageDialog(btnAcessar, "Usuario Inexistente!");
		}
				
	}
	
	private static boolean verificaHorario() {
		Calendar calendar = Calendar.getInstance(); //marca meio dia
		    calendar.set(Calendar.HOUR_OF_DAY, 11);
		    calendar.set(Calendar.MINUTE, 30);
		    calendar.set(Calendar.SECOND, 0);
		    Date time = calendar.getTime();
		    
		    Calendar calendar2 = Calendar.getInstance(); //horario atual
		    Date time2 = calendar2.getTime();
		    
		    int x =time.compareTo(time2);
		    
		    if (x>=0){
		    	return true;
		    } else { return false;}
	}	

	
	private String dataAtual(){
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy"); 
		 return formatarDate.format(data);
		
	}
	
	
}
