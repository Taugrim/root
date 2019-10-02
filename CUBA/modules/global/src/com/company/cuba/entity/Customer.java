package com.company.cuba.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Table(name = "CUBA_CUSTOMER")
@Entity(name = "cuba_Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = 5417997801213295236L;
    @NotNull
    @Column(name="Name",nullable = false)
    protected  String name;
    @Email
    @NotNull
    @Column(name="EMAIL",nullable =false)
    protected String email;

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
}