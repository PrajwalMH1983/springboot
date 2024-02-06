package com.springsecurity.springsecurityclient.entity;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled = false;

}
