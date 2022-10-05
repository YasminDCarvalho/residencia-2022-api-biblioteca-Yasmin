package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.repository.AlunoRepository;

@Service
public class AlunoService {
	@Autowired
 	AlunoRepository alunoRepository; //instanciei

	//metodo - devolve uma lista de alunos
				//Aluno = entidade
	public List<Aluno> getAllAlunos () {
		return alunoRepository.findAll();
	}
	
	public Aluno getAlunoById(Integer id) {
		return alunoRepository.findById(id).get();
		//return alunoRepository.findById(id).orElse(null);
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
		alunoExistenteNoBanco.setEmprestimos(aluno.getEmprestimos());
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
}

