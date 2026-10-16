package modelo;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Set;

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
	private int contarInscricoesAtivas() {
		int qtdAtivas = 0;
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.isAtiva()) {
				qtdAtivas++;
			}
		}
		return qtdAtivas;
	}
	
	public boolean realizarInscricao(Curso curso) {
		if (!isCadastroAtivo()) {
			return false;
		}
		if (curso == null || !curso.isAberto() || !curso.temVaga()) {
			return false;
		}
		if (contarInscricoesAtivas() == MAX_INSCRICOES_ATIVAS) {
			return false;
		}
		// Verificando se já existe uma inscrição para esse curso
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getCurso().equals(curso)) {
				if (inscricao.isAtiva() || inscricao.isConcluida()) {
					return false;
				}
				if (inscricao.isCancelada() || inscricao.isReprovada()) {
					inscricao.reativarInscricao();
					return true;
				}
			}
		}
		// Criando uma nova inscrição para esse curso
		// Isso é uma composição!
		Inscricao novaInscricao = new Inscricao(curso);
		inscricoes.add(novaInscricao);
		return true;
	}
	
	public boolean cancelarInscricao(Curso curso) {
		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getCurso().equals(curso)) {
				if (inscricao.isAtiva()) {
					inscricao.cancelarInscricao();
					return true;
				}
			}
		}
		return false;
	}

	public boolean desativarCadastro() {
		if(cadastroAtivo) {
			for(Inscricao inscricao: inscricoes) {
				if(inscricao.isAtiva()) {
					inscricao.cancelarInscricao();
				}
			}
			cadastroAtivo = false;
			return true;
		}
		return false;
	}
	
	void adicionarCertificado(Certificado certificado) {
		if(certificado != null) {
			certificados.add(certificado);
		}
	}
	
	boolean solicitarCertificado(Curso curso) {
		for(Inscricao inscricao: inscricoes) {
			if(inscricao.getCurso().equals(curso) && inscricao.isConcluida()) {
				Certificado certificado= new Certificado(nome, curso.getNome(), curso.getCargaHorariaHoras());
				adicionarCertificado(certificado);
				return true;
			}
		}
		return false;
	}

	// Ordenação da lista certificados
	public void OrdenarCertificados() {
		Collections.sort(certificados);
	}
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Aluno:");
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Idade: " + idade);
		System.out.println("E-mail: " + email);
		System.out.println("Inscrições ativas: " + contarInscricoesAtivas());
		System.out.println("Quantidade de certificados: " + certificados.size());
	}
}