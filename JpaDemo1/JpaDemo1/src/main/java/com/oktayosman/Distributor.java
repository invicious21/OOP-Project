package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "distributor")
public class Distributor implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @Column(name = "id_distributor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "honorarium",nullable = false)
    private double honorarium;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHonorarium() {
        return honorarium;
    }

    public void setHonorarium(double honorarium) {
        this.honorarium = honorarium;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
