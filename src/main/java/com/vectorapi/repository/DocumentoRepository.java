package com.vectorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vectorapi.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{

}
