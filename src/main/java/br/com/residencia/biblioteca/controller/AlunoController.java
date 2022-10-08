package br.com.residencia.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.service.AlunoService;

@RestController
@RequestMapping("/alunos") //recurso que aparecerá no endereço http
public class AlunoController {
	@Autowired
	AlunoService alunoService;
	
	//criei metodos para chamar crud 
	@GetMapping
	public ResponseEntity<List<Aluno>> getAllAlunos () {
		return new ResponseEntity<> (alunoService.getAllAlunos(), 
				HttpStatus.OK);
	}
	
	//get recupera uma informação
	@GetMapping("/{id}")
											//digo que vai receber um parametro do url do tipo pathVariable
	public ResponseEntity<Aluno> getAlunoById(@PathVariable Integer id) {
		Aluno aluno = alunoService.getAlunoById(id);
		if(null != aluno)
			return new ResponseEntity <>( aluno,
					HttpStatus.OK);
		else 
			return new ResponseEntity <>( aluno,
					HttpStatus.NOT_FOUND);
	}
	
	//post envia uma informação
	@PostMapping
										//para dizer que o dado vai ser recebido no body da requisição
	public ResponseEntity<Aluno> saveAluno(@RequestBody Aluno aluno) {
		return new ResponseEntity<> (alunoService.saveAluno(aluno), 
				HttpStatus.CREATED);
	}
	
	//put coloca uma informação
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> updateAluno (@RequestBody Aluno aluno, 
			@PathVariable Integer id) {
		return new ResponseEntity<> (alunoService.updateAluno(aluno, id), 
				HttpStatus.OK);
	}
	
	//delete del uma informação
	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> deleteAluno(@PathVariable Integer id) {
		Aluno aluno = alunoService.getAlunoById(id);
		if (null == aluno)
			return new ResponseEntity<> (aluno, 
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(alunoService.deleteAluno(id),
					HttpStatus.OK);
	}
	
	
	
}
