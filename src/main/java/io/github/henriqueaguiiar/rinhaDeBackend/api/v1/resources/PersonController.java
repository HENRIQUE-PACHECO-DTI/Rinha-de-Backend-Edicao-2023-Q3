package io.github.henriqueaguiiar.rinhaDeBackend.api.v1.resources;


import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.input.PersonInputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.output.PersonOutputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.exception.CreatePersonException;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.exception.PersonNotFoundException;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para expor os recursos de Person
 * @author Henrique Pacheco
 * @version 1.0.0
 */



@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private  final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonInputDTO personInputDTO){
       try{
           var person =  personService.createPerson(personInputDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(person);
       }catch (CreatePersonException e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonbyId(@PathVariable String id){
        try{
            var person = personService.getPersonById(id);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        } catch (PersonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonOutputDTO>> getPersons(
            @RequestParam(name = "t", required = false) String termo) {

        if (termo == null || termo.isBlank()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personService.getAllPerson());
        }

        List<PersonOutputDTO> filtered = personService.getAllByTerm(termo);
        return ResponseEntity.status(HttpStatus.OK).body(filtered);
    }

    @GetMapping("/contagemPessoas")
    public ResponseEntity<Integer> contagemPessoas(){
        var contagem = personService.contagemPessoas();
        return ResponseEntity.status(HttpStatus.OK).body(contagem);
    }
}
