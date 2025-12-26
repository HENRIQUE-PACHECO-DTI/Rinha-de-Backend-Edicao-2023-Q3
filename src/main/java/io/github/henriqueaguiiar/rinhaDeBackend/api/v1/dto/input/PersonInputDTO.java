package io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.input;


import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author Henrique Pacheco
 * Input DTO to Person Entity
 */


public class PersonInputDTO {

    private String surName;
    private String name;
    private String bornDate;
    private List<String> stack;

    public PersonInputDTO(String surName, String name, String bornDate, List<String> stack) {
        this.surName = surName;
        this.name = name;
        this.bornDate = bornDate;
        this.stack = stack;
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
        PersonInputDTO that = (PersonInputDTO) o;
        return Objects.equals(surName, that.surName) && Objects.equals(name, that.name) && Objects.equals(bornDate, that.bornDate) && Objects.equals(stack, that.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surName, name, bornDate, stack);
    }
}
