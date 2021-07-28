package com.one.digitalinnovation.personapi.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.digitalinnovation.personapi.dto.MessageResponseDTO;
import com.one.digitalinnovation.personapi.dto.mapper.PersonMapper;
import com.one.digitalinnovation.personapi.dto.request.PersonDTO;
import com.one.digitalinnovation.personapi.entities.Person;
import com.one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import com.one.digitalinnovation.personapi.repositories.PersonRepository;

@Service
public class PersonService {
	
    private PersonRepository repository;
    
    private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.repository = personRepository;
	}
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		
		Person savedPerson = repository.save(personToSave);
		return createMessageResponse(savedPerson.getId(), "Created person with ID");
	}

	 public List<PersonDTO> listAll() {
	        List<Person> people = repository.findAll();
	        return people.stream()
	                .map(personMapper::toDTO)
	                .collect(Collectors.toList());
	    }

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundException {
	    verifyIfExists(id);
	    repository.deleteById(id);
		
	}
	
	public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
		
		Person savedPerson = repository.save(personToUpdate);
		return createMessageResponse(savedPerson.getId(), "Updated person with ID");
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}
	
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}

}
