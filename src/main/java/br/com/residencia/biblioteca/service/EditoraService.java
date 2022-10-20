package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.residencia.biblioteca.dto.ConsultaCnpjDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.EditoraRepository;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class EditoraService {

	
	@Autowired
 	EditoraRepository editoraRepository; //instanciei

	@Autowired
 	LivroRepository livroRepository;
	
	@Autowired
	LivroService livroService;
	
	@Autowired 
	EmailService emailService;
	
	public ConsultaCnpjDTO consultaCnpjApiExterna (String cnpj) {
		//classe que faz parte do spring, cliente http
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
		
		//HashMap é um tipo de array
		Map<String, String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);
		
													//método da classe (para onde vou fazer a requisição, classe usada para quando fazer a requisição e tiver um resultado, parâmetros) 
		ConsultaCnpjDTO consultaCnpjDTO = restTemplate.getForObject (uri, ConsultaCnpjDTO.class, params);
		
		return consultaCnpjDTO;
	}
	
	//recebe um dto e entrega uma dto
	public Editora saveCnpjApiExterna(String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO = consultaCnpjApiExterna(cnpj);
		
		Editora editora = new Editora();
		editora.setNome(consultaCnpjDTO.getNome());
	
		return editoraRepository.save(editora);
	}
	
	public List<Editora> getAllEditoras () {
		return editoraRepository.findAll();
	}
	
	public List<EditoraDTO> getAllEditorasLivrosDTO(){
		//lista de entidade editora
		List<Editora> listaEditora = editoraRepository.findAll();
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
	
	//como cheguei a conclusão que a conversão é comum a todos, criei os metodos de conversão de dto para entidade e de entidade para dto 
	private Editora toEntidade (EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		
		editora.setCodigoEditora(editoraDTO.getCodigoEditora());
		editora.setNome(editoraDTO.getNome());
		return editora;
	}
	
	private EditoraDTO toDTO (Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		
		editoraDTO.setCodigoEditora (editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());
		return editoraDTO;
	}

	public List<EditoraDTO> getAllEditorasDTO(){
		
		//lista de entidades que preciso transformar em uma lista de dtos 
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		//1. Percorrer a lista de entidades Editora (chamada listaEditora)		
		//2. Na lista de entidade, pegar cada entidade existente nela
	
		//lado esquerdo quem vai receber, lado direito de onde vem
			for(Editora editora: listaEditora) {
		
				//3. Transformar cada entidade existente na lista em um DTO
				EditoraDTO editoraDTO = toDTO(editora);
				
				//OBS: para converter a entidade no DTO, usar o metodo toDTO
				//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
				listaEditoraDTO.add(editoraDTO);
			}
			//5. Retornar/devolver a lista de DTO preenchida
			return listaEditoraDTO;
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
		Editora editora = toEntidade(editoraDTO);
		//nova instancia da entidade editora vazia
		//--Editora editora = new Editora();
		//setando valor pra instancia que acabei de criar (atribuo novo nome)
		//--editora.setNome(editoraDTO.getNome());
		
		//metodo do repositorio encarregado pelo processo save, porém, só trabalha com entidade e não com dto
		//dessa forma salvei o valor que acabei de criar 
		//pego resultado do save e salvo em uma nova instancia editora 
		Editora novaEditora = editoraRepository.save(editora);
		
		EditoraDTO editoraAtualizadaDTO = toDTO (novaEditora);
		//crio um novo dto vazio 
		//pegar entidade e colocar dentro de um dto
		//--EditoraDTO novaEditoraDTO = new EditoraDTO();
		
		//seto o código e nome pegando da novaEditora
		//atribuir o valor de novaEditora e novaEditoraDTO
		//--novaEditoraDTO.setCodigoEditora (novaEditora.getCodigoEditora());
		//--novaEditoraDTO.setNome(novaEditora.getNome());
		return editoraAtualizadaDTO; 
	}

/*
	//duas formas de otimização
	public EditoraDTO saveEditoraDTOOtimizado(EditoraDTO editoraDTO) {
		Editora novaEditora = editoraRepository.save(toEntidade(editoraDTO));
		return toDTO (novaEditora);
	}
	
	public EditoraDTO saveEditoraDTOOtimizadoTwo (EditoraDTO editoraDTO) {
		return toDTO (editoraRepository.save(toEntidade(editoraDTO)));
	}
*/
	
	public EditoraDTO updateEditoraDTO (EditoraDTO editoraDTO, Integer id) {
		//pego a editora caso o id exista (igual o outro update) - guardo a editora pega pelo id
		Editora editoraExistenteNoBanco = getEditoraById(id);
		//crio nova editora dto
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		
		//verificação se for null
		if(editoraExistenteNoBanco != null) {
			editoraDTO.setCodigoEditora(editoraExistenteNoBanco.getCodigoEditora());
			
			//toEntidade seta o nome 
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			//atualizar os dados da instancia 
			//editoraExistenteNoBanco.setCodigoEditora(id);
			//altero o nome a partir do nome que recebi no editoraDTO
			//--editoraExistenteNoBanco.setNome(editoraDTO.getNome());
			//salvo ele atualizado
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			//toDTO seta codigo e nome 
			editoraAtualizadaDTO = toDTO(editoraAtualizada);
			//seto o dto a partir da editoraAtualizada 
			//--editoraAtualizadaDTO.setCodigoEditora(editoraAtualizada.getCodigoEditora());
			//--editoraAtualizadaDTO.setNome(editoraAtualizada.getNome());
		}
		emailService.sendEmail("yasmimd_carvalho@hotmail.com", "Teste de envio de e-mail", 
				editoraAtualizadaDTO.toString());
		
		return editoraAtualizadaDTO;
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