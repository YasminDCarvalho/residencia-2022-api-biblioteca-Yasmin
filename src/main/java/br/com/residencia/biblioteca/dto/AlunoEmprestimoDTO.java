package br.com.residencia.biblioteca.dto;

import java.util.List;

public class AlunoEmprestimoDTO {
	
	private Integer numeroMatriculaAluno;
	private String nome;
	private String cpf;
	private List<EmprestimoAlunoDTO> listaEmprestimosDTO;
	
	public Integer getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}
	public void setNumeroMatriculaAluno(Integer numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<EmprestimoAlunoDTO> getListaEmprestimosDTO() {
		return listaEmprestimosDTO;
	}
	public void setListaEmprestimosDTO(List<EmprestimoAlunoDTO> listaEmprestimosDTO) {
		this.listaEmprestimosDTO = listaEmprestimosDTO;
	}
	
}
