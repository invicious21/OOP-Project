package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Column(name = "id_event")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "seat_type")
    private String seatType;

    @Column(name = "seat_price")
    private double seatPrice;

    @Column(name = "max_ticket_bought_by1")
    private int maxTicketsBoughtBy1;

    @Column(name="ddate")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;


    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public int getMaxTicketsBoughtBy1() {
        return maxTicketsBoughtBy1;
    }

    public void setMaxTicketsBoughtBy1(int maxTicketsBoughtBy1) {
        this.maxTicketsBoughtBy1 = maxTicketsBoughtBy1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
