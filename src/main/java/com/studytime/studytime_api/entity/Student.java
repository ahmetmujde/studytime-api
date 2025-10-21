package com.studytime.studytime_api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "students")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "First name cannot be blank!")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "Last name cannot be blank!")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    @NotBlank(message = "Email cannot be blank!")
    private String email;

    @Column(name = "phone_number",length = 12)
    private String phoneNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases;
}
