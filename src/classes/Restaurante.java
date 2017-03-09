package classes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * @author Eduardo Dornelles
 *	Está classe represennta os restaurantes e suas informações. *  
 */

public class Restaurante {

	private String nome;
	private String site;

	private String endereco;
	private String descricao;
	private int semanaVisitada; // salva o numero da semana em que o restaurante
								// foi visitado
	private int votos;
	private static int diaVoto; // grava o ultima dia em que houve algum voto

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public String toString() {
		return nome + "|" + endereco + "|" + descricao + "|" + Integer.toString(semanaVisitada) + "|"
				+ Integer.toString(votos) + "|" + Integer.toString(diaVoto) + "|" + site;

	}

	public String somaVotoTxt() {
		votos++; // soma voto
		return nome + "|" + endereco + "|" + descricao + "|" + Integer.toString(semanaVisitada) + "|"
				+ Integer.toString(votos) + "|" + Integer.toString(diaVoto) + "|" + site;

	}

	public String ZerarVotoTxt() {
		votos = 0;
		return nome + "|" + endereco + "|" + descricao + "|" + Integer.toString(semanaVisitada) + "|" + "0" + "|"
				+ Integer.toString(diaVoto) + "|" + site;

	}

	public String salvaSemanaTxt() {
		semanaVisitada = semanaAtual();
		return nome + "|" + endereco + "|" + descricao + "|" + Integer.toString(semanaVisitada) + "|"
				+ Integer.toString(votos) + "|" + Integer.toString(diaVoto) + "|" + site;

	}

	public String salvaDiaTxt() {
		diaVoto = diaAtual();
		return nome + "|" + endereco + "|" + descricao + "|" + Integer.toString(semanaVisitada) + "|"
				+ Integer.toString(votos) + "|" + Integer.toString(diaVoto) + "|" + site;

	}

	public int getSemanaVisitada() {
		return semanaVisitada;
	}

	public void setSemanaVisitada(int semanaVisitada) {
		this.semanaVisitada = semanaVisitada;
	}

	private int semanaAtual() {
		Date data = new Date(System.currentTimeMillis());
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		int semana = cal.get(Calendar.WEEK_OF_YEAR);
		return semana;
	}

	public int getDiaVoto() {
		return diaVoto;
	}

	public void setDiaVoto(int diaVoto) {
		Restaurante.diaVoto = diaVoto;
	}

	public int diaAtual() {
		Date data = new Date(System.currentTimeMillis());
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		int semana = cal.get(Calendar.DAY_OF_YEAR);
		return semana;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
}