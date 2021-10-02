package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Transactional
	public List<CityDTO> findAllSortedByName() {
		return cityRepository.findAll(Sort.by("name")).stream().map(CityDTO::new).collect(Collectors.toList());
	}

	public void delete(Long id) {
		try {
			cityRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cidade não encontrada");
			
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta remoção viola a integridade do Banco de Dados");
		}
		
	}
}
