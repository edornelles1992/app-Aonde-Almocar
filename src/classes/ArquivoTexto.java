package classes;
/**
 * Classe com os metodos para facilitar a manipulação com documentos de texto
 * 
 */

import java.io.*;

public class ArquivoTexto

{
	private String fname;
	private FileReader arqreader;
	private FileWriter arqwriter;
	private BufferedReader buffer;

	public void abrirParaLeitura(String fn) {
		fname = fn;
		try {
			arqreader = new FileReader(fname);
			buffer = new BufferedReader(arqreader);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void abrirParaEscrita(String fn) {
		fname = fn;
		try {
			arqwriter = new FileWriter(fname, true);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void escreverLinhaArquivo(String s) {
		try {
			arqwriter.write(s + "\n");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public String lerLinhaArquivo() {
		try {
			return buffer.readLine();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return (null);
		}
	}
	
	

	public void fechar() {
		try {
			if (arqreader != null) {
				arqreader.close();
				arqreader = null;
			}
			if (arqwriter != null) {
				arqwriter.close();
				arqwriter = null;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}