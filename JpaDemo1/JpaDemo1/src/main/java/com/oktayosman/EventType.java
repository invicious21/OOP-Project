package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="event_type")
public class EventType implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Column(name = "id_event",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String event_type) {
        this.eventType = eventType;
    }
}
