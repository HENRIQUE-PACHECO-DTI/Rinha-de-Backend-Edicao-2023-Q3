package io.github.henriqueaguiiar.rinhaDeBackend.domain.service.impl;

import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.input.PersonInputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.output.PersonOutputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.exception.CreatePersonException;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.exception.PersonNotFoundException;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.mapper.PersonMapper;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.model.Person;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.repository.PersonRepository;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    @Override
    public PersonOutputDTO createPerson(PersonInputDTO personInputDTO) {
            validateInputPerson(personInputDTO);
            var personDatabase =  PersonMapper.toEntity(personInputDTO);
            personRepository.save(personDatabase);
            return PersonMapper.toOutputDTO(personDatabase);
    }


    @Override
    public List<PersonOutputDTO> getAllPerson(){
        List<Person> persons = personRepository.findAll();
        List<PersonOutputDTO> personOutputDTOList = new ArrayList<>();
        for(Person person : persons){
            personOutputDTOList.add(PersonMapper.toOutputDTO(person));
        }
        return  personOutputDTOList;
    }




    @Override
    public PersonOutputDTO getPersonById(String id){
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Pessoa Não Encontrada com este Id"));
        return PersonMapper.toOutputDTO(person);
    }





    @Override
    public void  validateInputPerson(PersonInputDTO personInputDTO){

        if(personInputDTO.getSurName() == null || personInputDTO.getSurName().isBlank()){
            throw new CreatePersonException("O preenchimento do sobrenome é Obrigatorio");
        }

        if (personInputDTO.getSurName().length() > 32) {
            throw new CreatePersonException("O limite máximo do sobrenome é 32 caracteres");
        }


        if(personInputDTO.getName() == null || personInputDTO.getName().isBlank()){
            throw new CreatePersonException("O preenchimento do nome é Obrigatorio");
        }


        if (personInputDTO.getName().length() > 100) {
            throw new CreatePersonException("O limite máximo do nome é 100 caracteres");
        }


        if (personInputDTO.getStack() != null) {
            for (String stackItem : personInputDTO.getStack()) {
                if (stackItem == null || stackItem.length() > 32) {
                    throw new CreatePersonException("Limite máximo de até 32 caracteres por item na Stack");
                }
            }
        }
    }
}
