package modelo;

public class Artigo {
	private Area area;
	private String titulo;
	private String conteudo;
	
	Artigo(Area area, String titulo, String conteudo){
		this.area = area;
		if (titulo != null && !titulo.equals("")) {
			this.titulo = titulo;
		}
		if (conteudo != null && !conteudo.equals("")) {
			this.conteudo = conteudo;
		}
	}
	
	String getTitulo() {
		return titulo;
	}
	
	void mudarTitulo(String novoTitulo) {
		if (novoTitulo != null && !novoTitulo.equals("")) {
			titulo = novoTitulo;
		}
	}
	
	void mudarConteudo(String novoConteudo) {
		if (novoConteudo != null && !novoConteudo.equals("")) {
			conteudo = novoConteudo;
		}
	}
	
	void exibirArtigo() {
		if (titulo == null) {
			System.out.println("Indisponível");
			return;
		}
		if (conteudo == null) {
			System.out.println(area + " | Título: " + titulo);
			System.out.println("Conteúdo indisponível...");
		}
		else {
			System.out.println(area + " | Título: " + titulo);
			System.out.println(conteudo);
		}
	}
}
