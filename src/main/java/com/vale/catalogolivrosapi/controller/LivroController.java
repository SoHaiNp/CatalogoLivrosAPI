package com.vale.catalogolivrosapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vale.catalogolivrosapi.model.Livro;
import com.vale.catalogolivrosapi.repository.LivroRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/livros")
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@GetMapping
	public List<Livro> listarTodos() {
		return livroRepository.findAll();
	}
	
	@PostMapping
	public Livro adicionarLivro(@RequestBody Livro livro) {
		return livroRepository.save(livro);
	}
	
	// Adicionar métodos para atualizar e deletar livros
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livroDetalhes) {
		Optional<Livro> livroOptional = livroRepository.findById(id);
		
		if (!livroOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Livro livro = livroOptional.get();
		livro.setTitulo(livroDetalhes.getTitulo());
		livro.setAutor(livroDetalhes.getAutor());
		livro.setIsbn(livroDetalhes.getIsbn());
		livroRepository.save(livro);
		
		return ResponseEntity.ok(livro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
		if (!livroRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		livroRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}


