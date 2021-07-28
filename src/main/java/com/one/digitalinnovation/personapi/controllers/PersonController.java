package com.one.digitalinnovation.personapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.one.digitalinnovation.personapi.dto.MessageResponseDTO;
import com.one.digitalinnovation.personapi.dto.request.PersonDTO;
import com.one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import com.one.digitalinnovation.personapi.services.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	
	private PersonService service;
	
	@Autowired
	public PersonController(PersonService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return service.createPerson(personDTO);
	
	}
	
	 @GetMapping
	 public List<PersonDTO> listAll() {
	     return service.listAll();
	 }
	 
	 @GetMapping("/{id}")
	 public PersonDTO finById(@PathVariable Long id) throws PersonNotFoundException {
		 return service.findById(id);
	 }

}
