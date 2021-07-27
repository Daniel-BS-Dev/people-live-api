package com.one.digitalinnovation.personapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.digitalinnovation.personapi.dto.MessageResponseDTO;
import com.one.digitalinnovation.personapi.entities.Person;
import com.one.digitalinnovation.personapi.repositories.PersonRepository;

@Service
public class PersonService {
	
    private PersonRepository repository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.repository = personRepository;
	}
	
	public MessageResponseDTO createPerson(Person person) {
		Person savePerson = repository.save(person);
		return MessageResponseDTO
				.builder()
				.message("Created person With ID "+ savePerson.getId())
				.build();
	}


}
