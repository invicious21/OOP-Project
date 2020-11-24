package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "clients")
public class Clients implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @Column(name = "id_client")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
