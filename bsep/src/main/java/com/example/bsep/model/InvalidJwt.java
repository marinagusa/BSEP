package com.example.bsep.model;

import javax.persistence.*;

@Entity
public class InvalidJwt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String jwt;

    public InvalidJwt() {}

    public InvalidJwt(String jwt){
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
