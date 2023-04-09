package com.assignment.pet.owners;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder()
                .id(1L)
                .firstName("Nick")
                .lastName("Hans")
                .dateCreated(LocalDate.now())
                .dateModified(LocalDate.now())
                .build();
    }

    @Test
    public void saveOwner() {

        given(ownerRepository.save(owner)).willReturn(owner);

        Owner savedOwner = ownerService.saveOwner(owner);

        assertNotNull(savedOwner);
    }

    @Test
    public void findByDateCreated() {

        List<Owner> owners = new ArrayList<>();
        owners.add(owner);
        given(ownerRepository.findByDateCreated(LocalDate.now())).willReturn(owners);

        List<Owner> savedOwners = ownerService.findByDateCreated(owner.getDateCreated());

        assertNotNull(savedOwners);
        assertEquals(1, savedOwners.size());
        assertEquals(owner, savedOwners.get(0));
    }

    @Test
    public void readOwner() {

        Owner owner2 = Owner.builder()
                .id(2L)
                .firstName("Pablo")
                .lastName("Rodrigo")
                .dateCreated(LocalDate.now())
                .dateModified(LocalDate.now())
                .build();

        given(ownerRepository.findAll()).willReturn(List.of(owner,owner2));

        List<Owner> ownerList = ownerService.readOwner();

        assertNotNull(ownerList);
        assertEquals((ownerList.size()),2);
    }

    @Test
    public void deleteOwnerById()
    {
        // Call the deletePet method with the owner id
        ownerService.deleteOwnerById(1L);

        // Verify that the pet repository's deleteById method was called once with the owner id
        verify(ownerRepository, times(1)).deleteById(1L);
    }
}