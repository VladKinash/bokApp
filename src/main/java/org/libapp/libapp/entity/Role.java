package org.libapp.libapp.entity;

import jakarta.persistence.*;
import org.libapp.libapp.repository.RoleRepo;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "roles", schema = "bookapp")
public class Role {


    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "Name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}