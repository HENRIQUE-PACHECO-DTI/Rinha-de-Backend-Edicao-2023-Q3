package io.github.henriqueaguiiar.rinhaDeBackend.domain.service;

import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.input.PersonInputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.output.PersonOutputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

     PersonOutputDTO createPerson (PersonInputDTO personInputDTO);

     PersonOutputDTO getPersonById(String id);

     List<PersonOutputDTO> getAllPerson();

    void validateInputPerson(PersonInputDTO personInputDTO);


}
