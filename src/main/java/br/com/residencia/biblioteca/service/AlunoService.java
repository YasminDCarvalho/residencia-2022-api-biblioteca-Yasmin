package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoEmprestimoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoAlunoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Emprestimo;
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
	
public List<AlunoDTO> getAllAlunosDTO(){
		
		List<Aluno> listaAluno = alunoRepository.findAll();
		List<AlunoDTO> listaAlunoDTO = new ArrayList<>();
		
			for(Aluno aluno: listaAluno) {
		
				AlunoDTO alunoDTO = toDTO(aluno);
				
				listaAlunoDTO.add(alunoDTO);
			}
		
			return listaAlunoDTO;
    }
	
	//get all lista de emprestimos
	public List<AlunoEmprestimoDTO> getAllEmprestimosAlunoDTO(){
		//lista de entidade aluno
		List<Aluno> listaAluno = alunoRepository.findAll();
		//lista de entidade AlunoEmprestimoDTO
		List<AlunoEmprestimoDTO> listaAlunoEmprestimoDTO = new ArrayList<>();
		
			for(Aluno aluno: listaAluno) {
				AlunoEmprestimoDTO alunoEmprestimoDTO = toDTO2(aluno);
				List<Emprestimo> listaEmprestimos = new ArrayList<>();
				List<EmprestimoAlunoDTO> listaEmprestimosDTO = new ArrayList<>();
				
				
				//crio uma lista de emprestimos para cada aluno
				listaEmprestimos = emprestimoRepository.findByAluno(aluno);
				
				//for pega a lista de emprestimo e converte para dto
				for(Emprestimo emprestimo : listaEmprestimos) {
					//Conversão para DTO
					EmprestimoAlunoDTO emprestimoDTO = emprestimoService.toDTO2(emprestimo);
					
					//pega os emprestimos convertidos e coloca na listaEmprestimoDTO
					listaEmprestimosDTO.add(emprestimoDTO);
				}
				
				//seta no aluno a lista de emprestimos
				alunoEmprestimoDTO.setListaEmprestimosDTO(listaEmprestimosDTO);
				listaAlunoEmprestimoDTO.add(alunoEmprestimoDTO);
			}
			
			return listaAlunoEmprestimoDTO;
	}

	
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
	
	private AlunoEmprestimoDTO toDTO2 (Aluno aluno) {
		AlunoEmprestimoDTO alunoEmprestimoDTO = new AlunoEmprestimoDTO();
		
		alunoEmprestimoDTO.setNome(aluno.getNome());
		alunoEmprestimoDTO.setCpf(aluno.getCpf());
		alunoEmprestimoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		
		return alunoEmprestimoDTO;
	}
}

