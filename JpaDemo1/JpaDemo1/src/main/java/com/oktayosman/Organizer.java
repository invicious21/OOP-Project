package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "organizer")
public class Organizer implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Column(name = "id_organizer",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_organizer;

    @Column(name = "honorarium",nullable = false)
    private double honorarium;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users user_id;

    public int getId_organizer() {
        return id_organizer;
    }

    public void setId_organizer(int id_organizer) {
        this.id_organizer = id_organizer;
    }

    public double getHonorarium() {
        return honorarium;
    }

    public void setHonorarium(double honorarium) {
        this.honorarium = honorarium;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }
}
