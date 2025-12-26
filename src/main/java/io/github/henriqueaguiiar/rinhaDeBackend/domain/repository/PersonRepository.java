package io.github.henriqueaguiiar.rinhaDeBackend.domain.repository;

import io.github.henriqueaguiiar.rinhaDeBackend.api.v1.dto.output.PersonOutputDTO;
import io.github.henriqueaguiiar.rinhaDeBackend.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {



    @Query("""
        SELECT p 
        FROM Person p 
        WHERE LOWER(p.surName) LIKE LOWER(CONCAT('%', :termo, '%'))
           OR LOWER(p.name) LIKE LOWER(CONCAT('%', :termo, '%'))
           OR EXISTS (
               SELECT s 
               FROM Person p2 JOIN p2.stack s 
               WHERE p2 = p AND LOWER(s) LIKE LOWER(CONCAT('%', :termo, '%'))
           )
    """)
    List<Person> search(@Param("termo") String termo);

}
