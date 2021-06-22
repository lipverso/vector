package com.vectorapi.repository.documento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vectorapi.model.Documento;
import com.vectorapi.repository.filter.DocumentoFilter;

public interface DocumentoRepositoryQuery {
	
	public Page<Documento> filtrar (DocumentoFilter documentoFilter, Pageable pageable);
}
