package com.assignment.pet.pets;

import com.assignment.pet.owners.Owner;
import com.assignment.pet.owners.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private PetServiceImpl petService;

    private Owner owner;

    private Pet pet;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);
        owner = Owner.builder()
                .id(1L)
                .firstName("Nick")
                .lastName("Hans")
                .dateCreated(LocalDate.now())
                .dateModified(LocalDate.now())
                .build();

        pet = Pet.builder()
                .id(1L)
                .name("King")
                .owner(owner)
                .breed("Monkey")
                .dateCreated(LocalDate.now())
                .dateModified(LocalDate.now())
                .build();
    }

//when()thenReturn() = given()willReturn()

    @Test
    public void savePet() {

        // Mock the owner repository's findById method to return the owner object
        when(ownerRepository.existsById(1L)).thenReturn(true);
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        // Mock the pet repository's saves method to return the saved pet object
        when(petRepository.save(pet)).thenReturn(pet);

        // Call the savePet method with the owner id and the pet object
        Pet savedPet = petService.savePet( 1L, pet);

        // Verify that the pet repository's saves method was called once with the pet object as the parameter
        verify(petRepository, times(1)).save(pet);

        // Verify that the saved pet object has the correct owner
        assertEquals(owner, savedPet.getOwner());

    }

    @Test
    void deletePet() {

        // Mock the owner repository's existsById method to return true when called with the owner id parameter
        when(ownerRepository.existsById(1L)).thenReturn(true);

        // Mock the pet repository's findById method to return the pet object when called with the pet id parameter
        when(petRepository.existsById(1L)).thenReturn(true);
        //when(petRepository.findById(2L)).thenReturn(Optional.of(pet));

        // Call the deletePet method with the owner id and pet id parameters
        petService.deletePet(1L, 1L);

        // Verify that the pet repository's deleteById method was called once with the pet id parameter
        verify(petRepository, times(1)).deleteById(1L);
    }

    @Test
    void updatePet() {

        // Create a test updated pet
        Pet updatedPet = new Pet();
        updatedPet.setName("UpdatedPet");
        updatedPet.setBreed("UpdatedBreed");

        // Set up the mock owner repository to return the test owner
        when(ownerRepository.existsById(1L)).thenReturn(true);
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        // Set up the mock pet repository to return the test pet
        when(petRepository.existsById(1L)).thenReturn(true);
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        // Call the method being tested
        Pet result = petService.updatePet(1L, 1L, updatedPet);

        // Check that the correct pet was returned with the updated values
        assertEquals("UpdatedPet", result.getName());
        assertEquals("UpdatedBreed", result.getBreed());
        assertEquals(owner, result.getOwner());
        assertNotNull(result.getDateCreated());
        assertNotNull(result.getDateModified());
        assertEquals(result.getDateCreated(), result.getDateModified());
    }

    @Test
    void getOwnerByPetName() {

        // Set up the mock pet repository to return the test pet
        when(petRepository.findByName("TestPet")).thenReturn(pet);

        // Set up the mock owner repository to return the test owner
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
        owner.setPets(pets);
        when(ownerRepository.findByPetsId(1L)).thenReturn(owner);

        // Call the method being tested
        Owner result = petService.getOwnerByPetName("TestPet");

        // Check that the correct owner was returned
        assertEquals(owner, result);
    }

    @Test
    void getOwnerByPetId() {

        // Mock the pet repository's findById method to return the pet object when called with the pet id parameter
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        // Mock the owner repository's findByPetsId method to return the owner object when called with the pet id parameter
        when(ownerRepository.findByPetsId(1L)).thenReturn(owner);

        // Call the getOwnerByPetId method with the pet id parameter
        Owner result = petService.getOwnerByPetId(1L);

        // Verify that the pet repository's findById method was called once with the pet id parameter
        verify(petRepository, times(1)).findById(1L);

        // Verify that the owner repository's findByPetsId method was called once with the pet id parameter
        verify(ownerRepository, times(1)).findByPetsId(1L);

        // Verify that the result object is equal to the expected owner object
        assertEquals(owner, result);
    }

    @Test
    void getPetByOwnerId() {

        Pet pet2 = Pet.builder()
                .id(2L)
                .name("Queen")
                .owner(owner)
                .breed("Cat")
                .dateCreated(LocalDate.now())
                .dateModified(LocalDate.now())
                .build();

        List<Pet> pets = Arrays.asList(pet, pet2);

        // Mock the pet repository's findByOwnerId method to return the list of pet objects when called with the owner id parameter
        when(petRepository.findByOwnerId(1L)).thenReturn(pets);

        // Call the getPetByOwnerId method with the owner id parameter
        List<Pet> result = petService.getPetByOwnerId(1L);

        // Verify that the pet repository's findByOwnerId method was called once with the owner id parameter
        verify(petRepository, times(1)).findByOwnerId(1L);

        // Verify that the result list contains the expected pet objects
        assertEquals(pets, result);
    }
}