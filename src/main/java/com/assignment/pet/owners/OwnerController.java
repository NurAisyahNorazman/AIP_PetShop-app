package com.assignment.pet.owners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    //User create new owner using API
    @PostMapping("/owners")
    public Owner createOwner(@RequestBody Owner owner)
    {
        return ownerService.saveOwner(owner);
    }

    //Find owners the user created on a particular date
    @GetMapping("/owners/byDateCreated")
    public List<Owner> getOwnersByDateCreated(@RequestParam("dateCreated") String dateCreatedStr) {
        LocalDate dateCreated = LocalDate.parse(dateCreatedStr);
        return ownerService.findByDateCreated(dateCreated);
    }

    //Read all owners
    @GetMapping("/owners")
    public List<Owner> readOwner()
    {
        return ownerService.readOwner();
    }

    // Delete owners by id
    @DeleteMapping("/owners/{id}")
    public String deletePetById(@PathVariable("id") Long owner_id)
    {
        ownerService.deleteOwnerById(owner_id);
        return "Deleted Successfully";
    }
}
