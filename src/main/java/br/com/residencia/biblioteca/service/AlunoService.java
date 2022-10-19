package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoEmprestimoDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.AlunoRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class AlunoService {
	@Autowired
 	AlunoRepository alunoRepository; //instanciei
	
	@Autowired
 	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	EmprestimoService emprestimoService;

	//metodo - devolve uma lista de alunos
				//Aluno = entidade
	public List<Aluno> getAllAlunos () {
		return alunoRepository.findAll();
	}
	
	/*
	public List<AlunoEmprestimoDTO> getAllEmprestimosAlunoDTO(){
		//lista de entidade aluno
		List<Aluno> listaAluno = alunoRepository.findAll();
		//lista de entidade editoraDTO
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
			for(Editora editora: listaEditora) {
				EditoraDTO editoraDTO = toDTO(editora);
				List<Livro> listaLivros = new ArrayList<>();
				List<LivroDTO> listaLivrosDTO = new ArrayList<>();
				
				//fui no repositório para poder usar o método findByEditora
				listaLivros = livroRepository.findByEditora(editora);
				for(Livro livro : listaLivros) {
					LivroDTO livroDTO = livroService.toDTO(livro);
					listaLivrosDTO.add(livroDTO);
				}
				
				editoraDTO.setListaLivrosDTO(listaLivrosDTO);
				listaEditoraDTO.add(editoraDTO);
			}
			
			return listaEditoraDTO;
	}
*/
	
	public Aluno getAlunoById(Integer id) {
		//return alunoRepository.findById(id).get();
		return alunoRepository.findById(id).orElse(null);
	}

	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
							//aluno que estou recebendo no metodo
	public Aluno updateAluno (Aluno aluno, Integer id) {
		//Aluno alunoExistenteNoBanco = alunoRepository.findById(id).get();
		
		Aluno alunoExistenteNoBanco = getAlunoById(id);
		
		alunoExistenteNoBanco.setBairro(aluno.getBairro());
		alunoExistenteNoBanco.setCidade(aluno.getCidade());
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento());
		alunoExistenteNoBanco.setCpf(aluno.getCpf());
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());
		
		return alunoRepository.save(alunoExistenteNoBanco);
		
		//return alunoRepository.save(aluno);
	}
	
	public Aluno deleteAluno(Integer id) {
		 alunoRepository.deleteById(id); //não tem retorno porque é void 
		 return getAlunoById(id); //retorno uma consulta de aluno por id para ver se realmente foi deletado
	}
	
	public Boolean deleteAlunoBool(Integer id) {
		 alunoRepository.deleteById(id); 
		 if (null != getAlunoById(id))
			 return false;
		 else
			 return true;
	}
	
	private Aluno toEntidade (AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();
		
		aluno.setNome(alunoDTO.getNome());
		aluno.setBairro(alunoDTO.getBairro());
		aluno.setCidade(alunoDTO.getCidade());
		aluno.setComplemento(alunoDTO.getComplemento());
		aluno.setCpf(alunoDTO.getCpf());
		aluno.setDataNascimento(alunoDTO.getDataNascimento());
		aluno.setLogradouro(alunoDTO.getLogradouro());
		aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());

		
		return aluno;
	}
	
	private AlunoDTO toDTO (Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
		
		alunoDTO.setNome(aluno.getNome());
		alunoDTO.setBairro(aluno.getBairro());
		alunoDTO.setCidade(aluno.getCidade());
		alunoDTO.setComplemento(aluno.getComplemento());
		alunoDTO.setCpf(aluno.getCpf());
		alunoDTO.setDataNascimento(aluno.getDataNascimento());
		alunoDTO.setLogradouro(aluno.getLogradouro());
		alunoDTO.setNumeroLogradouro(aluno.getNumeroLogradouro());
		alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		
		return alunoDTO;
	}
}

