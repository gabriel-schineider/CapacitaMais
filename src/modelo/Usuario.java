package modelo;

import java.util.Objects;

public abstract class Usuario {
	// Atributos
	protected String nome;
	protected String cpf;
	protected int idade;
	private final int idadeMinima = 16;
	protected String email;
	protected boolean cadastroAtivo;
	
	// Construtor
	public Usuario(String nome, String cpf, int idade, String email) {
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		if (cpf != null && !cpf.equals("")) {
			this.cpf = cpf;
		}
		if (idade >= idadeMinima) {
			this.idade = idade;
		}
		if (email != null && !email.equals("")) {
			this.email = email;
		}
		cadastroAtivo = true;
	}
	
	// Métodos
	public boolean atualizarEmail(String emailNovo) {
		if (emailNovo != null && !emailNovo.equals("") && !emailNovo.equals(email)) {
			email = emailNovo;
			return true;
		}
		return false;
	}
	
	public void ativarCadastro() {
		if(!cadastroAtivo) {
			cadastroAtivo = true;
		}
	}
	
	public void desativarCadastro() {
		if(cadastroAtivo) {
			cadastroAtivo = false;
		}
	}
	
	public boolean isCadastroAtivo() {
		return cadastroAtivo;
	}
	
	public void exibirInformacoes() {
		System.out.printf("\tNome: %s\n\tCPF: %s\n\tIdade: %d\n\tE-mail: %s\n", nome, cpf, idade, email);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf);
	}
}
