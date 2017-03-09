package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Eduardo Dornelles
 *	Está classe representa a lista de todos os restaurantes que estarão disponiveis para votação
 *	bem como seus métodos para manipulação da coleção e persistencia dos dados em arquivos
 */
public class ListaRestaurante {

	public ArrayList<Restaurante> lista = new ArrayList<>();

	public void add(Restaurante rest) {
		lista.add(rest);
	}

	public ListaRestaurante() {
		popularLista();
	}

	public void popularLista() {
		String doc = "Restaurantes.txt";
		ArquivoTexto novo = new ArquivoTexto();
		novo.abrirParaLeitura(doc);

		boolean res = true;

		while (res == true) {
			String linha = novo.lerLinhaArquivo();

			if (linha != null) {

				String[] aux = linha.split("[|]");
				Restaurante rest = new Restaurante();
				rest.setNome(aux[0]);
				rest.setEndereco(aux[1]);
				rest.setDescricao(aux[2]);
				rest.setSemanaVisitada(Integer.parseInt(aux[3]));
				rest.setVotos(Integer.parseInt(aux[4]));
				rest.setDiaVoto(Integer.parseInt(aux[5]));
				rest.setSite(aux[6]);
				lista.add(rest);
			} else
				res = false;
		}
		novo.fechar();
	}

	public void gravarVotosTxt(String restaurante) throws IOException {
		Restaurante resAtual = new Restaurante();
		for (Restaurante res : lista) {
			if (res.getNome().equals(restaurante)) {
				resAtual = res;
			}

		}

		String arquivo = "Restaurantes.txt";
		String arquivoTmp = "ARQUIVOx-tmp";

		BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTmp));
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));

		String linha;
		while ((linha = reader.readLine()) != null) {
			if (linha.contains(restaurante)) {
				linha = resAtual.somaVotoTxt(); // acumula um voto e formata
												// para gravar no txt
				resAtual.setVotos(resAtual.getVotos());

			}
			writer.write(linha + "\n");
		}

		writer.close();
		reader.close();

		new File(arquivo).delete();
		new File(arquivoTmp).renameTo(new File(arquivo));

	}

	public void gravarSemanaTxt(String restaurante) throws IOException {
		Restaurante resAtual = new Restaurante();
		for (Restaurante res : lista) {
			if (res.getNome().equals(restaurante)) {
				resAtual = res;
			}

		}

		String arquivo = "Restaurantes.txt";
		String arquivoTmp = "ARQUIVOx-tmp";

		BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTmp));
		BufferedReader reader = new BufferedReader(new FileReader(arquivo));

		String linha;
		while ((linha = reader.readLine()) != null) {
			if (linha.contains(restaurante)) {
				linha = resAtual.salvaSemanaTxt();
				resAtual.setVotos(resAtual.getVotos());

			}
			writer.write(linha + "\n");
		}

		writer.close();
		reader.close();

		new File(arquivo).delete();
		new File(arquivoTmp).renameTo(new File(arquivo));

	}

	public void gravarDiaTxt() throws IOException { // salvar ultimo o ultimo
													// dia em que houve um voto
		Restaurante resAtual = new Restaurante();
		for (Restaurante res : lista) {

			resAtual = res;

			String arquivo = "Restaurantes.txt";
			String arquivoTmp = "ARQUIVOx-tmp";

			BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTmp));
			BufferedReader reader = new BufferedReader(new FileReader(arquivo));

			String linha;
			while ((linha = reader.readLine()) != null) {
				if (linha.contains(res.getNome())) {

					linha = resAtual.salvaDiaTxt();
					resAtual.setVotos(resAtual.getVotos());

				}
				writer.write(linha + "\n");
			}

			writer.close();
			reader.close();

			new File(arquivo).delete();
			new File(arquivoTmp).renameTo(new File(arquivo));
		}
	}

	public void ReiniciarVotos() throws IOException {
		Restaurante resAtual = new Restaurante();
		for (Restaurante res : lista) {

			resAtual = res;

			String arquivo = "Restaurantes.txt";
			String arquivoTmp = "ARQUIVOx-tmp";

			BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTmp));
			BufferedReader reader = new BufferedReader(new FileReader(arquivo));

			String linha;
			while ((linha = reader.readLine()) != null) {
				if (linha.contains(res.getNome())) {

					linha = resAtual.ZerarVotoTxt();
					resAtual.setVotos(0);

				}
				writer.write(linha + "\n");
			}

			writer.close();
			reader.close();

			new File(arquivo).delete();
			new File(arquivoTmp).renameTo(new File(arquivo));
		}

	}

	public void AdicionarRestaurante(Restaurante res) {

		String arquivo = "Restaurantes.txt";
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true));
			writer.newLine();
			writer.write(res.toString());
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
