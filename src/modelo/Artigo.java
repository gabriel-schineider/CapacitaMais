package modelo;

public class Artigo {
	private Area area;
	private String titulo;
	private String conteudo;
	
	Artigo(Area area, String titulo, String conteudo){
		// Tudo bem se o título ou o conteúdo for nulo
		this.area = area;
		this.titulo = titulo;
		this.conteudo = conteudo;
	}
	
	String getTitulo() {
		return titulo;
	}
	
	void exibirArtigo() {
		if(titulo == null && conteudo == null) {
			System.out.println("Indisponível");
		}
		else {
			System.out.println(area + " | Título: " + titulo);
			System.out.println(conteudo);
		}
	}
}
