package modelo;

public class Inscricao {
	private Curso curso;
	private StatusInscricao status;
	private int cursoHorasAssistidas;
	private Certificado certificado;
	
	Inscricao(Curso curso) {
		// Verificação de "curso == null || !curso.isAberto()" já realizada em Aluno quando tenta se inscrever em um curso
		this.curso = curso;
		status = StatusInscricao.ATIVA;
		this.curso.registrarInscricaoAtiva();
		cursoHorasAssistidas = 0;
		certificado = null;
	}
	
	 boolean isInscricaoAtiva() {
		 return status == StatusInscricao.ATIVA;
	 }
	 
	 boolean isInscricaoConcluida() {
		 return status== StatusInscricao.CONCLUIDA;
	 }

	 boolean isInscricaoCancelada() {
		 return status== StatusInscricao.CANCELADA;
	 }
	 
	 boolean cancelarInscricao() {
		 if(isInscricaoAtiva()) {
			 status = StatusInscricao.CANCELADA;
			 curso.registrarTransicaoDeInscricaoAtivaParaCancelada();
			 return true;
		 }
		 return false;
	 }
	 
	boolean reativarInscricao() {
		if(curso.isAberto()) {
			if(isInscricaoCancelada()) {
				status = StatusInscricao.ATIVA;
				curso.registrarTransicaoDeInscricaoCanceladaParaAtiva();
				return true;
			}
		}
		return false;
	 }
	 
	 void assistirCurso(int assistirHoras) {
		 if (assistirHoras < 0 || !isInscricaoAtiva()) { // Inscrição não ativa é igual à inscrição concluída ou cancelada
			 return;
		 }
		 else {
			 if (cursoHorasAssistidas + assistirHoras < curso.getCargaHorariaHoras()) {
				 cursoHorasAssistidas += assistirHoras; 
			 }
			 else {
				 cursoHorasAssistidas = curso.getCargaHorariaHoras();
				 status = StatusInscricao.CONCLUIDA;
				 curso.registrarTransicaoDeInscricaoAtivaParaConcluida();
			 }
		 }
	 }
	 
	Certificado solicitarCertificado(String nomeAluno) {
	// Impedir que o aluno gere o certificado antes de finalizar o curso ou que gere o mesmo certificado mais de uma vez
		if(!isInscricaoConcluida() || certificado != null) {
			return null;
		}
	// Se ainda não gerou o certificado e finalizou a carga horária do curso
		if(certificado == null && cursoHorasAssistidas == curso.getCargaHorariaHoras()) {
			certificado = new Certificado(nomeAluno, curso.getNome(), curso.getCargaHorariaHoras());
		}
		return certificado;
	}
	
	Curso getCurso() {
		return curso;
	}
}