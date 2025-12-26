package io.github.henriqueaguiiar.rinhaDeBackend.domain.exception;
public class CreatePersonException extends RuntimeException{
    private static final long serialVersionUUID = 1L;

    public CreatePersonException(String message) {
        super(message);
    }
}
