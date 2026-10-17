package modelo;

public class Inscricao {
	private Curso curso;
	private StatusInscricao status;
	private int cursoHorasAssistidas;
	private Certificado certificado;
	
	Inscricao(Curso curso) {
		// Verificação de "curso == null || !curso.isAberto() || !curso.temVaga()" já realizada em Aluno quando tenta se inscrever em um curso
		this.curso = curso;
		status = StatusInscricao.ATIVA;
		this.curso.registrarInscricaoAtiva();
		cursoHorasAssistidas = 0;
		certificado = null;
	}
	
	 public boolean isInscricaoAtiva() {
		 return status == StatusInscricao.ATIVA;
	 }
	 
	 public boolean isInscricaoConcluida() {
		 return status== StatusInscricao.CONCLUIDA;
	 }

	 public boolean isInscricaoCancelada() {
		 return status== StatusInscricao.CANCELADA;
	 }
	 
	 public boolean isInscricaoReprovada() {
		 return status== StatusInscricao.REPROVADA;
	 }
	 
	 public boolean cancelarInscricao() {
		 if(isInscricaoAtiva()) {
			 status = StatusInscricao.CANCELADA;
			 curso.registrarTransicaoDeInscricaoAtivaParaCancelada();
			 return true;
		 }
		 return false;
	 }
	 
	 public boolean reativarInscricao() {
		 if(curso.isAberto() && curso.temVaga()) {
			 if(isInscricaoCancelada()) {
				 status = StatusInscricao.ATIVA;
				 curso.registrarTransicaoDeInscricaoCanceladaParaAtiva();
				 return true;
			 }
			 if(isInscricaoReprovada()) {
				 cursoHorasAssistidas =0;
				 status = StatusInscricao.ATIVA;
				 curso.registrarTransicaoDeInscricaoReprovadaParaAtiva();
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 void assistirCurso(int assistirHoras) {
		 if (assistirHoras > 0) {
			 if (cursoHorasAssistidas + assistirHoras < curso.getCargaHorariaHoras()) {
				 cursoHorasAssistidas += assistirHoras; 
			 }
			 else {
				 cursoHorasAssistidas = curso.getCargaHorariaHoras();
				 status = StatusInscricao.CONCLUIDA;
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
	
	public Curso getCurso() {
		return curso;
	}
}