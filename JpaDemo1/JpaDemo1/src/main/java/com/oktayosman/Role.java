package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @Column(name = "id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
