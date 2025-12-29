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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsavel por realizar as operações dos endpoints de Person. Evitando Expor Repository
 * @author Henrique Pacheco
 * @version 1.0.0
 */

@Service
public class PersonServiceImpl implements PersonService {
    private static final DateTimeFormatter BIRTHDATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
    public List<PersonOutputDTO> getAllByTerm(String term) {
        List<Person> personList = personRepository.search(term.toLowerCase());
        List<PersonOutputDTO> personOutputDTOList = new ArrayList<>();
        for(Person person : personList){
            personOutputDTOList.add(PersonMapper.toOutputDTO(person));
        }
        return personOutputDTOList;
    }


    @Override
    public PersonOutputDTO getPersonById(String id){
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Pessoa Não Encontrada com este Id"));
        return PersonMapper.toOutputDTO(person);
    }
    /**
     *Metodo para validar o input de dados para POST/Criação de Recurso.
     *
     * Validações:
     * apelido:	obrigatório, único, string de até 32 caracteres.
     * Nome: obrigatório, string de até 100 caracteres.
     * Nascimento:	obrigatório, string para data no formato AAAA-MM-DD (ano, mês, dia).
     * Stack:	opcional, vetor de string com cada elemento sendo obrigatório e de até 32 caracteres.
     * @param personInputDTO
     *
     */

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

        try {
            LocalDate.parse(personInputDTO.getBornDate(), BIRTHDATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new CreatePersonException("A data de nascimento deve estar no formato AAAA-MM-DD");
        }
    }

    @Override
    public Integer contagemPessoas() {
        List<PersonOutputDTO> allPerson = getAllPerson();
        return allPerson.size();
    }
}
