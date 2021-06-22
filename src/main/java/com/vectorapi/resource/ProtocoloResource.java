package com.vectorapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vectorapi.model.Protocolo;
import com.vectorapi.repository.ProtocoloRepository;
import com.vectorapi.service.ProtocoloService;

@RestController
@RequestMapping("/protocolos")
public class ProtocoloResource {
	
	@Autowired
	private ProtocoloRepository protocoloRepository;
	
	@Autowired
	private ProtocoloService protocoloService;
	
	@GetMapping
	public List<Protocolo> listar(){
		return protocoloRepository.findAll();	
	}
	
	@PostMapping
	public ResponseEntity<Protocolo> criar(@Valid @RequestBody Protocolo protocolo, HttpServletResponse response) {
		Protocolo protocoloSalvo = protocoloRepository.save(protocolo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
		.buildAndExpand(protocoloSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(protocoloSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Protocolo> buscarPorCodigo(@PathVariable Long id) {
		return protocoloRepository.findById(id)
				.map(protocolo -> ResponseEntity.ok(protocolo))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		protocoloRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Protocolo> atualizar(@PathVariable Long id, @Valid @RequestBody Protocolo protocolo){
		Protocolo protocoloSalvo = protocoloService.atualizar(id, protocolo);
		return ResponseEntity.ok(protocoloSalvo);
	}
	
}
