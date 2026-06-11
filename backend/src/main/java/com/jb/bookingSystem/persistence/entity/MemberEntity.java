package com.jb.bookingSystem.persistence.entity;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "members")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public MemberEntity(){}
    public MemberEntity(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        MemberEntity member = (MemberEntity) object;
        return java.util.Objects.equals(id, member.id) && java.util.Objects.equals(name, member.name) && java.util.Objects.equals(email, member.email) && java.util.Objects.equals(password, member.password);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, name, email, password);
    }
}


