package com.assignment.pet.owners;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByPetsId(Long pet_id);

    @Query("SELECT o FROM Owner o WHERE o.dateCreated = :dateCreated")
    List<Owner> findByDateCreated(@Param("dateCreated") LocalDate dateCreated);

}
