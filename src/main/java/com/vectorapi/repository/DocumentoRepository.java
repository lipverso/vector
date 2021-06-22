package com.vectorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vectorapi.model.Documento;
import com.vectorapi.repository.documento.DocumentoRepositoryQuery;

public interface DocumentoRepository extends DocumentoRepositoryQuery, JpaRepository<Documento, Long>{

}
