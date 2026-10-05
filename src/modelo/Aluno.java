package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Aluno extends Usuario {
	private ArrayList<Inscricao> inscricoes;
	private ArrayList<Certificado> certificados;
	private static final int MAX_INSCRICOES_ATIVAS = 3;

	// Construtor
	public Aluno(String nome, String cpf, int idade, String email) {
		super(nome, cpf, idade, email);
		inscricoes = new ArrayList<Inscricao>();
		certificados = new ArrayList<Certificado>();
	}

	// Métodos

	public int contarInscricoesAtivas() {
		int qtdAtivas = 0;
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.isAtiva()) {
				qtdAtivas++;
			}
		}
		return qtdAtivas;
	}

	public boolean inscrever(Curso curso) {
		if (curso == null) {
			return false;
		}
		if (!isCadastroAtivo()) {
			return false;
		}

		if (contarInscricoesAtivas() >= MAX_INSCRICOES_ATIVAS) {
			return false;
		}

		// saber se o aluno ja esta inscrito no curso ou se ja concluiu nesse curso que ele quer se inscrever
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getCurso().equals(curso)) {
				if (inscricao.isAtiva() || inscricao.isConcluida()) {
					return false;
				}
			}
		}
		
		Inscricao novaInscricao= new Inscricao(curso);
		inscricoes.add(novaInscricao);
		return true;
	}
	
	public boolean cancelarInscricao(Curso curso) {
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getCurso().equals(curso)) {
				if (inscricao.isAtiva()) {
					inscricao.cancelar();
					return true;
				}
			}
		}
		return false;
	}
	
	public void adicionarCertificado(Certificado certificado) {
		if(certificado != null) {
			certificados.add(certificado);
		}
	}
	
	public boolean solicitarCertificado(Curso curso) {
		for(Inscricao inscricao: inscricoes) {
			if(inscricao.getCurso().equals(curso) && inscricao.isConcluida()) {
				Certificado certificado= new Certificado(nome, curso.getNome(), curso.getCargaHorariaHoras());
				adicionarCertificado(certificado);
				return true;
			}
		}
		return false;
	}

	//ordenação da lista certificados
	public void OrdenarCertificados() {
		Collections.sort(certificados);
	}
	
	public void inativar() {
		cadastroAtivo= false;
		for(Inscricao inscricao: inscricoes) {
			if(inscricao.isAtiva()) {
				inscricao.cancelar();
			}
		}
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Aluno:");
		super.exibirInformacoes();
		System.out.println("Inscrições ativas: " +contarInscricoesAtivas());
		System.out.println("Quantidade de certificados: "+certificados.size()); 
		
	}
