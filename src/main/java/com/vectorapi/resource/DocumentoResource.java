package com.vectorapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.vectorapi.model.Documento;
import com.vectorapi.repository.DocumentoRepository;
import com.vectorapi.service.DocumentoService;

@RestController
@RequestMapping("/documentos")
public class DocumentoResource {
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private DocumentoService documentoService;
	
	@GetMapping
	public List<Documento> listar(){
		return documentoRepository.findAll();	
	}
	
	@PostMapping
	public ResponseEntity<Documento> criar(@Valid @RequestBody Documento documento, HttpServletResponse response) {
		Documento documentoSalvo = documentoRepository.save(documento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
		.buildAndExpand(documentoSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(documentoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Documento> buscarPorCodigo(@PathVariable Long id) {
		return documentoRepository.findById(id)
				.map(documento -> ResponseEntity.ok(documento))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		documentoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Documento> atualizar(@PathVariable Long id, @Valid @RequestBody Documento documento){
		Documento documentoSalvo = documentoService.atualizar(id, documento);
		return ResponseEntity.ok(documentoSalvo);
	}
	
	@PutMapping("/{id}/resumo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeResumo(@PathVariable Long id, @RequestBody String resumo) {
		documentoService.atualizarPropriedadeResumo(id, resumo);
	}
	
}
