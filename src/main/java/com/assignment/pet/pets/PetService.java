package com.assignment.pet.pets;

import com.assignment.pet.owners.Owner;

import java.util.List;
import java.util.Optional;

public interface PetService {

    // User create pet for existing owner
    Pet savePet(Long owner_id, Pet pet);

    //User read pets from existing owner by Id
    Optional<Pet> readPetById(Long owner_id, Long pet_id);

    // User delete existing pets from existing owner
    void deletePet(Long owner_id, Long pet_id);

    //User update existing pets that belongs to a certain owner
    Pet updatePet(Long owner_id, Long pet_id, Pet updatedPet);

    // User find owner by pet name
    Owner getOwnerByPetName(String name);

    // User find owner by pet id
    Owner getOwnerByPetId(Long pet_id);

    //User find all pets belonging to a certain owner
    List<Pet> getPetByOwnerId(Long owner_id);

    // Read all owner
    List<Pet> readPet();
}
