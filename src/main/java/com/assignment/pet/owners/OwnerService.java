package com.assignment.pet.owners;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OwnerService {

    // Create new owner
    Owner saveOwner(Owner owner);

    // Find owners the user created on a particular date
    List<Owner> findByDateCreated(LocalDate dateCreated);

    // Read all owner
    List<Owner> readOwner();

    // Delete owners by id
    void deleteOwnerById(Long owner_id);

}
