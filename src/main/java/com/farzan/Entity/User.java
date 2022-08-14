package com.farzan.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Name;
    private String Family;
    private String email;

    public User()
    {
        super();
    }

    public User(String name, String family, String email)
    {
        super();
        this.Name = name;
        this.Family = family;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
