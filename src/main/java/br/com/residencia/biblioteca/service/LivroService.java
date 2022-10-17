package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
 	LivroRepository livroRepository; //instanciei

	//metodo - devolve uma lista de livros
				//Livro = entidade
	public List<Livro> getAllLivros () {
		return livroRepository.findAll();
	}
	
	//devolvo uma entidade, recebo um dto
	private Livro toEntidade (LivroDTO livroDTO) {
		Livro livro = new Livro();
		
		livro.setNomeLivro(livroDTO.getNomeLivro());
		livro.setNomeAutor(livroDTO.getNomeAutor());
		livro.setCodigoIsbn(livroDTO.getCodigoIsbn());
		livro.setDataLancamento(livroDTO.getDataLancamento());
		
		return livro;
	}
	
	//devolvo um dto, recebo uma entidade
	private LivroDTO toDTO (Livro livro) {
		LivroDTO livroDTO = new LivroDTO();
		
		livroDTO.setCodigoLivro(livro.getCodigoLivro());
		livroDTO.setNomeLivro(livro.getNomeLivro());
		livroDTO.setNomeAutor(livro.getNomeAutor());
		livroDTO.setCodigoIsbn(livro.getCodigoIsbn());
		livroDTO.setDataLancamento(livro.getDataLancamento());
		
		return livroDTO;
	}
	
	
	public Livro getLivroById(Integer id) {
		return livroRepository.findById(id).get();
	}

	public Livro saveLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public LivroDTO saveLivroDTO(LivroDTO livroDTO) {
		Livro livro = toEntidade(livroDTO);
		Livro novoLivro = livroRepository.save(livro);
		
		LivroDTO livroAtualizadoDTO = toDTO (novoLivro);
		return livroAtualizadoDTO; 
	}
	
							//livro que estou recebendo no metodo
	public Livro updateLivro (Livro livro, Integer id) {
		
		Livro livroExistenteNoBanco = getLivroById(id);
		
		livroExistenteNoBanco.setNomeLivro(livro.getNomeLivro());
		livroExistenteNoBanco.setNomeAutor(livro.getNomeAutor());
		livroExistenteNoBanco.setDataLancamento(livro.getDataLancamento());
		livroExistenteNoBanco.setCodigoIsbn(livro.getCodigoIsbn());
		
		return livroRepository.save(livroExistenteNoBanco);
		
	}
	
	public LivroDTO updateLivroDTO (LivroDTO livroDTO, Integer id) {
		Livro livroExistenteNoBanco = getLivroById(id);
		LivroDTO livroAtualizadoDTO = new LivroDTO();
		
		//verificação se for null
		if(livroExistenteNoBanco != null) {
			
			livroExistenteNoBanco = toEntidade(livroDTO);
		
			Livro livroAtualizado = livroRepository.save(livroExistenteNoBanco);
			livroAtualizadoDTO = toDTO(livroAtualizado);
		
		}
		return livroAtualizadoDTO;
	}
	
	public Livro deleteLivro(Integer id) {
		livroRepository.deleteById(id);  
		 return getLivroById(id); 
	}
	
	public Boolean deleteLivroBool(Integer id) {
		livroRepository.deleteById(id); 
		 if (null != getLivroById(id))
			 return false;
		 else
			 return true;
	}
	
}
