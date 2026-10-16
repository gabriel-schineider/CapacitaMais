package modelo;

import java.util.Collections;
import java.util.Set;

public class Aluno extends Usuario {
	private Set<Inscricao> inscricoes;
	private Set<Certificado> certificados;
	private int inscricoesAtivas;
	private static final int MAX_INSCRICOES_ATIVAS = 3;

	// Construtor
	public Aluno(String nome, String cpf, int idade, String email) {
		super(nome, cpf, idade, email);
		inscricoes = new Set<Inscricao>();
		certificados = new Set<Certificado>();
	}

	// Métodos
	
	public boolean realizarInscricao(Curso curso) {
		if (!isCadastroAtivo()) {
			return false;
		}
		if (curso == null || !curso.isAberto() || !curso.temVaga()) {
			return false;
		}
		if (inscricoesAtivas == MAX_INSCRICOES_ATIVAS) { // PRECISO VER A LÓGICA DE ADD INSCRIÇÃO ATIVA PARA VER SE, DE FATO, ADD SEMPRE +1 E POSSO USAR O "==" AO INVÉS DO ">=", QUE NÃO FARIA SENTIDO...
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
					inscricao.cancelar();
					return true;
				}
			}
		}
		return false;
	}

	public void inativar() {
		cadastroAtivo= false;
		for(Inscricao inscricao: inscricoes) {
			if(inscricao.isAtiva()) {
				inscricao.cancelar();
			}
		}
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
	
	
	public void exibirInformacoes() {
		System.out.println("--> Informações de Aluno:");
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
		System.out.println("Idade: " + idade);
		System.out.println("E-mail: " + email);
		System.out.println("Inscrições ativas: " + inscricoesAtivas);
		System.out.println("Quantidade de certificados: " + certificados.size()); 
		
	}
}