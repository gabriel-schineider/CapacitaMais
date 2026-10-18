package modelo;

import java.util.ArrayList;

public class Administradora implements Notificavel{
	private String nome;
	private ArrayList<Usuario> usuarios;
	
	public Administradora(String nome) {
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		}
		usuarios = new ArrayList<>();
	}
	
	 public boolean adicionarUsuario(Usuario usuario) {
	        if (usuario == null || usuarios.contains(usuario)) { // nulo ou cpf já cadastrado (equals compara cpf)
	            return false;
	        }
	        usuarios.add(usuario);
	        return true;
	 }
	 
	 public boolean removerUsuario(Usuario usuario) {
		return usuarios.remove(usuario);
	 }
		 
	 public void desativarUsuarios() {
		 for (Usuario usuario : usuarios) {
			 usuario.desativarCadastro();
		 }
	 }
	 
	 public void exibirInformacoesDosUsuarios() {
		 for (Usuario usuario : usuarios) {
			 usuario.exibirInformacoes();
			 System.out.println();
		 }
	 }
	 
	 public void exibirInformacoes() {
		 System.out.println("    --> Informações de Adm");
		 System.out.println("Nome: " + nome);
		 System.out.println("Quantidade de usuários administrados: " + usuarios.size());
	 }
	 
	 @Override
		public void notificar(String mensagem) {
			System.out.printf("Mensagem de adm(a) %s:\n", nome);
			System.out.printf("   %s\n", mensagem);
		}
}