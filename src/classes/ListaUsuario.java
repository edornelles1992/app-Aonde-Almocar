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
 *	Está classe representa a lista contendo os usuarios cadastrados no sistema
 *	bem como seus métodos para manipulação da coleção e persistencia dos dados
 */

public class ListaUsuario {

	public ArrayList<Usuario> lista = new ArrayList<>();

	public void add(Usuario usu) {
		lista.add(usu);
	}

	public ListaUsuario() {
		carregarLista();
	}

	public void carregarLista() {
		String doc = "Usuarios.txt";
		ArquivoTexto novo = new ArquivoTexto();
		novo.abrirParaLeitura(doc);

		boolean res = true;

		while (res == true) {
			String linha = novo.lerLinhaArquivo();

			if (linha != null) {

				String[] aux = linha.split("[|]");
				Usuario usu = new Usuario();
				usu.setLogin(aux[0]);
				usu.setNome(aux[1]);
				usu.setSenha(aux[2]);				
				usu.setUltimoVoto(aux[3]);
				lista.add(usu);
			} else
				res = false;
		}
		novo.fechar();
	}

	
	public void alteraVoto(Usuario usu) throws IOException {
	    String arquivo = "Usuarios.txt";
	    String arquivoTmp = "ARQUIVO-tmp";

	    BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTmp));
	    BufferedReader reader = new BufferedReader(new FileReader(arquivo));

	    String linha;
	    while ((linha = reader.readLine()) != null) {
	        if (linha.contains(usu.getLogin())) {
	            linha = usu.formataTxt();
	        }
	        writer.write(linha + "\n");
	    }

	    writer.close();        
	    reader.close();

	    new File(arquivo).delete();
	    new File(arquivoTmp).renameTo(new File(arquivo));
	}	

}
