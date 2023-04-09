package com.assignment.pet.pets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Pet findByName(String name);

    List<Pet> findByOwnerId(Long owner_id);

}
