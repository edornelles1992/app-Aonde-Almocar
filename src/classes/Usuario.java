package classes;

/** 
 * @author Eduardo Dornelles
 *	Está classe representa os usuarios que terão acesso ao sistema.
 */

public class Usuario {

	private String login;
	private String nome;
	private String senha;
	
	private String UltimoVoto;
		
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String formataTxt(){
		
		
		return login+"|"+nome+"|"+senha+"|"+UltimoVoto;
		
	}	

	public String getUltimoVoto() {
		return UltimoVoto;
	}
	public void setUltimoVoto(String ultimoVoto) {
		UltimoVoto = ultimoVoto;
	}
	
}
