package com.assignment.pet.pets;

import com.assignment.pet.owners.Owner;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.time.LocalDateTime;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonBackReference
    @ManyToOne
    private Owner owner;
    private String breed;
    @Column(name = "date_created", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate dateCreated;
    @Column(name = "date_modified")
    @UpdateTimestamp
    private LocalDate dateModified;
}
