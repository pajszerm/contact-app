package com.example.contactapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact", schema = "contact-app")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "contact")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "contact")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;
}
