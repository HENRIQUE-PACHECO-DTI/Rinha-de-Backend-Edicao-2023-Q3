package io.github.henriqueaguiiar.rinhaDeBackend.domain.mapper;


import com.github.f4b6a3.uuid.UuidCreator;
import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.input.PersonInputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.output.PersonOutputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.model.Person;
import org.springframework.stereotype.Component;

public class PersonMapper {

    public static Person  toEntity(PersonInputDTO personInputDTO){
        Person person = new Person();
        person.setId(UuidCreator.getTimeOrderedEpoch().toString());
        person.setSurName(personInputDTO.getSurName());
        person.setName(personInputDTO.getName());
        person.setBornDate(personInputDTO.getBornDate());
        person.setStack(personInputDTO.getStack());
        return person;
    }

    public static PersonOutputDTO toOutputDTO(Person person){
        PersonOutputDTO personOutput = new PersonOutputDTO();
        personOutput.setId(person.getId());
        personOutput.setSurName(person.getSurName());
        personOutput.setName(person.getName());
        personOutput.setBornDate(person.getBornDate());
        personOutput.setStack(person.getStack());
        return personOutput;
    }
}
