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

    @Column(name = "max_ticket_bought_by1")
    private int maxTicketsBoughtBy1;

    @Column(name = "ddate")
    private String date;

    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;


    public int getMaxTicketsBoughtBy1() {
        return maxTicketsBoughtBy1;
    }

    public void setMaxTicketsBoughtBy1(int maxTicketsBoughtBy1) {
        this.maxTicketsBoughtBy1 = maxTicketsBoughtBy1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
