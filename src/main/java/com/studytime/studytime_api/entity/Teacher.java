package com.studytime.studytime_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "teachers")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Teacher {
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
    @NotBlank(message = "Email name cannot be blank!")
    private String email;

    @Column(name = "phone_number",nullable = false, length = 20)
    @NotBlank(message = "Phone number name cannot be blank!")
    private String phoneNumber;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plan> plans;
}
