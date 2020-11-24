package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="seats")
public class Seats implements Serializable {
    public static final long serialVersionUID=1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "all_seats")
    private int allSeats;

    @ManyToOne
    @JoinColumn(name = "type-seat_id")
    private SeatType seatType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAllSeats() {
        return allSeats;
    }

    public void setAllSeats(int allSeats) {
        this.allSeats = allSeats;
    }
}
