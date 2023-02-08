package com.pplbo.fortic5.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

    private Date dateOfBirth;

    private Enum<Gender> gender;

    private String location;
}
