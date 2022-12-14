package br.com.residencia.biblioteca.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "editora")
public class Editora {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "codigoeditora")
	private Integer codigoEditora; //tipo serial no banco é igual a tipo Integer em java
	
	@Column (name = "nome")
	private String nome; //varchar = string 
	
	@OneToMany (mappedBy = "editora")//Um para muitos. Uma editora publica muitos livros. mappedBy = Mapeada por
	private Set<Livro> livros; // set é uma coleção de livros

	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}

	
}
