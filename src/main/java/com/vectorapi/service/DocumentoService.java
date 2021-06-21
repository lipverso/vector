package com.vectorapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.vectorapi.model.Documento;
import com.vectorapi.repository.DocumentoRepository;

@Service
public class DocumentoService {
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	public Documento atualizar(Long id, Documento documento) {
		Documento documentoSalvo = buscarDocumentoPorId(id);
		BeanUtils.copyProperties(documento, documentoSalvo, "id");
		documentoRepository.save(documentoSalvo);
		
		return documentoSalvo;
	}

	public void atualizarPropriedadeResumo(Long id,@RequestBody String resumo) {
		Documento documentoSalvo = buscarDocumentoPorId(id);
		documentoSalvo.setResumo(resumo);
		documentoRepository.save(documentoSalvo);
	}
	
	private Documento buscarDocumentoPorId(Long id) {
		return documentoRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
}
