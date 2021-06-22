package com.vectorapi.repository.documento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.vectorapi.model.Documento;
import com.vectorapi.model.Documento_;
import com.vectorapi.repository.filter.DocumentoFilter;

public class DocumentoRepositoryImpl implements DocumentoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Documento> filtrar(DocumentoFilter documentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Documento> criteria = builder.createQuery(Documento.class);
		Root<Documento> root = criteria.from(Documento.class);
		
		Predicate[] predicates = criarRestricoes(documentoFilter,builder,root);
		criteria.where(predicates);
		
		TypedQuery<Documento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(documentoFilter));
	}

	private Predicate[] criarRestricoes(DocumentoFilter documentoFilter, CriteriaBuilder builder,
			Root<Documento> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!ObjectUtils.isEmpty(documentoFilter.getResumo())) {
			predicates.add(builder.like(
					builder.lower(root.get(Documento_.resumo)), "%" + documentoFilter.getResumo().toLowerCase() + "%"));
			
		}
		if(!ObjectUtils.isEmpty(documentoFilter.getSolicitante())) {
			predicates.add(builder.like(
					builder.lower(root.get(Documento_.solicitante)), "%" + documentoFilter.getSolicitante().toLowerCase() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Documento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}

	private Long total(DocumentoFilter documentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Documento> root = criteria.from(Documento.class);
		
		Predicate[] predicates = criarRestricoes(documentoFilter, builder, root);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
