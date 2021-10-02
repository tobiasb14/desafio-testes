package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event event = eventRepository.getOne(id);
			return new EventDTO(eventRepository.save(convertDtoToEntity(dto, event)));
		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrada");
		}
	}

	private Event convertDtoToEntity(EventDTO dto, Event event) {
		event.setName(dto.getName());
		event.setDate(dto.getDate());
		event.setUrl(dto.getUrl());
		event.setCity(cityRepository.getOne(dto.getCityId()));
		return event;
	}
	
}
