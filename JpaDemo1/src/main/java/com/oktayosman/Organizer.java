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
    private int id;


    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "honorarium",nullable = false)
    private double honorarium;

    @OneToOne
    @JoinColumn(name = "clients_id_client",nullable = false)
    private Clients client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getHonorarium() {
        return honorarium;
    }

    public void setHonorarium(double honorarium) {
        this.honorarium = honorarium;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
}
