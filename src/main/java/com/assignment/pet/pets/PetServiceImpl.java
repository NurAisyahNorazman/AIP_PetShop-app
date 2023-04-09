package com.assignment.pet.pets;

import com.assignment.pet.exceptions.IdNotExistException;
import com.assignment.pet.owners.Owner;
import com.assignment.pet.owners.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    // User create new pets for existing owner using API
    @Override
    public Pet savePet(Long owner_id, Pet pet)
    {
        boolean isExist = ownerRepository.existsById(owner_id);

        if(isExist){
            Owner owner = ownerRepository.findById(owner_id).get();
            pet.setOwner(owner);
            return petRepository.save(pet);
        }else throw new IdNotExistException("Owner Id did not exist!");
    }

    // Delete existing pets from existing owner
    @Override
    public void deletePet(Long owner_id, Long pet_id)
    {
        boolean isOwnerExist = ownerRepository.existsById(owner_id);
        boolean isPetExist = petRepository.existsById(pet_id);

        if(isOwnerExist && isPetExist)
            petRepository.deleteById(pet_id);
        else
            throw new IdNotExistException("Pet with owner not found!");
    }

    // Update existing pets that belongs to a certain owner
    @Override
    public Pet updatePet(Long owner_id,Long pet_id, Pet updatedPet)
    {
        boolean isExist = ownerRepository.existsById(owner_id);
        boolean isPetExist = petRepository.existsById(owner_id);


        if(isExist && isPetExist){
            Owner owner = ownerRepository.findById(owner_id).get();
            Pet pet = petRepository.findById(pet_id).get();
            pet.setName(updatedPet.getName());
            pet.setOwner(owner);
            pet.setBreed(updatedPet.getBreed());
            return petRepository.save(pet);
        }else throw new IdNotExistException("Not Found!");
    }

    // Find owner of a certain pet name
    @Override
    public Owner getOwnerByPetName(String name) {
        Pet pet = petRepository.findByName(name);
        if (pet == null) {
            throw new IdNotExistException("Name did not exist!");
        }
        return ownerRepository.findByPetsId(pet.getId());
    }

    // Find owner of a certain pet id
    @Override
    public Owner getOwnerByPetId(Long pet_id) {
        Pet pet = petRepository.findById(pet_id).orElseThrow(() -> new IdNotExistException("Id did not exist!"));
        return ownerRepository.findByPetsId(pet.getId());
    }

    @Override
    public List<Pet> getPetByOwnerId(Long owner_id) {
        return petRepository.findByOwnerId(owner_id);
    }

    // Read all pet
    @Override public List<Pet> readPet()
    {
        return (List<Pet>) petRepository.findAll();
    }

    //User read pets from existing owner by Id
    @Override
    public Optional<Pet> readPetById(Long owner_id, Long pet_id)
    {
        boolean isExist = ownerRepository.existsById(owner_id);

        if(isExist){
            return petRepository.findById(pet_id);
        }else throw new IdNotExistException("Owner Id did not exist!");
    }
}
