package com.assignment.pet.owners;

import com.assignment.pet.pets.Pet;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(name = "date_created", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate dateCreated;

    @Column(name = "date_modified")
    @UpdateTimestamp
    private LocalDate dateModified;

    @JsonManagedReference
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;
}
