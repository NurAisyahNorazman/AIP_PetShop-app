package com.assignment.pet.pets;

import com.assignment.pet.exceptions.ErrorResponse;
import com.assignment.pet.exceptions.IdNotExistException;
import com.assignment.pet.owners.Owner;
import com.assignment.pet.owners.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private OwnerService ownerService;

    //Read all pets
    @GetMapping("/pets")
    public List<Pet> readOwner()
    {
        return petService.readPet();
    }

    //User read pets from existing owner by Id
    @GetMapping("/owners/{owner_id}/pets/{pet_id}")
    public Optional<Pet> readPetById(@PathVariable("owner_id") Long owner_id, @PathVariable("pet_id") Long pet_id)
    {
        return petService.readPetById(owner_id, pet_id);
    }

    //User create new pets for existing owner using API
    @PostMapping("/owners/{owner_id}/pets")
    public Pet createPet(@PathVariable("owner_id") Long owner_id, @RequestBody Pet pet)
    {
        return petService.savePet(owner_id, pet);
    }

    //User delete existing pets from existing owner
    @DeleteMapping("/owners/{owner_id}/pets/{pet_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePet(@PathVariable("owner_id") Long owner_id, @PathVariable("pet_id") Long pet_id)
    {
        petService.deletePet(owner_id, pet_id);
        return "Deleted Successfully";
    }

    //User find owner by pet name
    @GetMapping("/pets/name/{name}/owners")
    public Owner getOwnerByPetName(@PathVariable String name) {
        return petService.getOwnerByPetName(name);
    }

    //User find owner of a certain pet by id
    @GetMapping("/pets/{pet_id}/owners")
    public Owner getOwnerByPetId(@PathVariable Long pet_id) {
        return petService.getOwnerByPetId(pet_id);
    }

    //User find all pets belonging to a certain owner
    @GetMapping("/owners/{owner_id}/pets")
    public List<Pet> getPetByOwnerId(@PathVariable Long owner_id) {
        return petService.getPetByOwnerId(owner_id);
    }

    //User update existing pets that belongs to a certain owner
    @PutMapping("/owners/{owner_id}/pets/{pet_id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public Pet updatePet(@PathVariable("owner_id") Long owner_id, @PathVariable("pet_id") Long pet_id, @RequestBody Pet updatedPet) {
        return petService.updatePet(owner_id,pet_id,updatedPet);
    }

    //Handle Exception with proper message
    @ExceptionHandler(value = IdNotExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleIdNotExistException(
            IdNotExistException ex)
    {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage());
    }

}
