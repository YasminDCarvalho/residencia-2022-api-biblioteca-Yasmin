package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.repository.EditoraRepository;

@Service
public class EditoraService {
	
	@Autowired
 	EditoraRepository editoraRepository; //instanciei

	public List<Editora> getAllEditoras () {
		return editoraRepository.findAll();
	}
	
	public Editora getEditoraById(Integer id) {
		//return editoraRepository.findById(id).get();
		return editoraRepository.findById(id).orElse(null);
	}

	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	public Editora updateEditora (Editora editora, Integer id) {
		
		Editora editoraExistenteNoBanco = getEditoraById(id);
		
		editoraExistenteNoBanco.setNome(editora.getNome());
		
		return editoraRepository.save(editoraExistenteNoBanco);
		
	}
	
	public Editora deleteEditora(Integer id) {
			editoraRepository.deleteById(id);  
		 return getEditoraById(id); 
	}
	
	public Boolean deleteEditoraBool(Integer id) {
		editoraRepository.deleteById(id); 
		 if (null != getEditoraById(id))
			 return false;
		 else
			 return true;
	}

}
