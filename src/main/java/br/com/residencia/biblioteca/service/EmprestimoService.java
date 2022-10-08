package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

	@Autowired
 	EmprestimoRepository emprestimoRepository; //instanciei

	public List<Emprestimo> getAllEmprestimos () {
		return emprestimoRepository.findAll();
	}
	
	public Emprestimo getEmprestimoById(Integer id) {
		//return emprestimoRepository.findById(id).get();
		return emprestimoRepository.findById(id).orElse(null);
	}

	public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
		return emprestimoRepository.save(emprestimo);
	}
					
	public Emprestimo updateEmprestimo (Emprestimo emprestimo, Integer id) {
		
		Emprestimo emprestimoExistenteNoBanco = getEmprestimoById(id);
		
		emprestimoExistenteNoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoExistenteNoBanco.setDataEntrega(emprestimo.getDataEntrega());
		emprestimoExistenteNoBanco.setValorEmprestimo(emprestimo.getValorEmprestimo());
		//emprestimoExistenteNoBanco.setAluno(emprestimo.getAluno());
		//emprestimoExistenteNoBanco.setLivro(emprestimo.getLivro());
		//posso ou não colocar os que vem de OneToOne e Many
		
		return emprestimoRepository.save(emprestimoExistenteNoBanco);
		
	}
	
	public Emprestimo deleteEmprestimo(Integer id) {
			emprestimoRepository.deleteById(id);  
		 return getEmprestimoById(id); 
	}
	
	public Boolean deleteEmprestimoBool(Integer id) {
		emprestimoRepository.deleteById(id); 
		 if (null != getEmprestimoById(id))
			 return false;
		 else
			 return true;
	}
	
}

