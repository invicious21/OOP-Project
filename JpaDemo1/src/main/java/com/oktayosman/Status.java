package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="status")
public class Status implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @Column(name = "id", updatable = false,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status", nullable = false)
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
