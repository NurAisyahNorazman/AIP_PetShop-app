package com.assignment.pet.owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

    @Override
    // Save new owner to database
    public Owner saveOwner(Owner owner)
    {
        return ownerRepository.save(owner);
    }

    // Find owners the user created on a particular date
    @Override
    public List<Owner> findByDateCreated(LocalDate dateCreated)
    {
        return (List<Owner>) ownerRepository.findByDateCreated(dateCreated);
    }

    // Read all owner
    @Override public List<Owner> readOwner()
    {
        return (List<Owner>) ownerRepository.findAll();
    }

    // Delete owners by id
    @Override
    public void deleteOwnerById(Long owner_id)
    {
        ownerRepository.deleteById(owner_id);
    }

}
