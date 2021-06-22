package com.vectorapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vectorapi.model.Protocolo;
import com.vectorapi.repository.ProtocoloRepository;

@Service
public class ProtocoloService {
	
	@Autowired
	private ProtocoloRepository protocoloRepository;
	
	public Protocolo atualizar(Long id, Protocolo protocolo) {
		Protocolo protocoloSalvo = buscarDocumentoPorId(id);
		BeanUtils.copyProperties(protocolo, protocoloSalvo, "id");
		protocoloRepository.save(protocoloSalvo);
		
		return protocoloSalvo;
	}

	
	private Protocolo buscarDocumentoPorId(Long id) {
		return protocoloRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
}
