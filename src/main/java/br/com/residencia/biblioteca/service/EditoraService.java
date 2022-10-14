package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.repository.EditoraRepository;

@Service
public class EditoraService {
	
	@Autowired
 	EditoraRepository editoraRepository; //instanciei

	public List<Editora> getAllEditoras () {
		return editoraRepository.findAll();
	}
	
	/*private Editora converteDTOParaEntidade (EditoraDTO editoraDTO) {
		//return
	}
	
	private EditoraDTO converteDTOParaEntidade (Editora editora) {
		//return
	}

	public List<EditoraDTO> getAllEditorasDTO () {
		//lista de entidades que preciso transformar em uma lista de dtos 
		return editoraRepository.findAll();
	}*/
	
	public List<EditoraDTO> getAllEditorasDTO(){
        List<EditoraDTO> listaDTO = new ArrayList<EditoraDTO>();
        BeanUtils.copyProperties(getAllEditoras(), listaDTO);
        return listaDTO;
    }
	
	public Editora getEditoraById(Integer id) {
		//return editoraRepository.findById(id).get();
		return editoraRepository.findById(id).orElse(null);
	}

	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	//recebe um dto e entrega uma dto
	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		//nova instancia da entidade editora
		Editora editora = new Editora();
		//setando valor pra instancia que acabei de criar
		editora.setNome(editoraDTO.getNome());
		
		//metodo do repositorio encarregado pelo processo save, porém, só trabalha com entidade e não com dto
		//desa forma salvei o valor que acabei de criar 
		Editora novaEditora = editoraRepository.save(editora);
		
		//crio um novo dto vazio 
		EditoraDTO novaEditoraDTO = new EditoraDTO();
		
		//seto o código e nome pegando da novaEditora
		novaEditoraDTO.setCodigoEditora (novaEditora.getCodigoEditora());
		novaEditoraDTO.setNome(novaEditora.getNome());
		return novaEditoraDTO; 
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
