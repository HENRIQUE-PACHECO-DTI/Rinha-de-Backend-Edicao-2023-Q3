package io.github.henriqueaguiiar.rinhaDeBackend.domain.exception;

public class PersonNotFoundException extends RuntimeException{
    private static final long serialVersionUUID = 1L;

    public PersonNotFoundException(String message) {
        super(message);
    }
}
