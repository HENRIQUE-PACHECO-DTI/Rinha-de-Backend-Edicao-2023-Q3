package io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.output;

import com.github.f4b6a3.uuid.UuidCreator;
import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.input.PersonInputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.model.Person;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 * @author Henrique Pacheco
 * Outgoing DTO to Person Entity
 */


public class PersonOutputDTO {


    private String id;
    private String surName;
    private String name;
    private String bornDate;
    private List<String> stack;


    /**
     * Using UUID v7 to create id
     * @param personInputDTO to created OutputDTO
     */

    public PersonOutputDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonOutputDTO that = (PersonOutputDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

