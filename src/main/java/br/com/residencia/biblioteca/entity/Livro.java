package br.com.residencia.biblioteca.entity;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "codigolivro")
	private Integer codigoLivro;
	
	@Column (name = "nomelivro")
	private String nomeLivro;
	
	@Column (name = "nomeautor")
	private String nomeAutor;

	@Column (name = "datalancamento")
	private Instant dataLancamento;
	
	@Column (name = "codigoisbn")
	private Integer codigoIsbn;
	
	@ManyToOne //Muitos para um. Muitos livros são publicados por uma editora. 
	@JoinColumn (name = "codigoeditora", referencedColumnName = "codigoeditora") 
	private Editora editora; //criei uma instancia da classe editora para fazer o relacionamento
	
	//@OneToOne (mappedBy = "livro")//Um livro pode ser emprestado 1 vez
	//private Emprestimo emprestimo; //instancia para fazer relacionamento
	
	@OneToMany (mappedBy = "livro")//Um livro pode ser emprestado n vezes 
	private Set<Emprestimo> emprestimos;

	
	public Integer getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(Integer codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public Instant getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Instant dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getCodigoIsbn() {
		return codigoIsbn;
	}

	public void setCodigoIsbn(Integer codigoIsbn) {
		this.codigoIsbn = codigoIsbn;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Set<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(Set<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}	
	
}
