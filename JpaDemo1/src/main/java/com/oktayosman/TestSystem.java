package com.oktayosman;

import com.mysql.cj.xdevapi.Client;
import org.w3c.dom.events.EventException;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class TestSystem {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");


    public static void main(String[] args) {

        UsersTable user = new UsersTable();
        Role role = new Role();
        //role.setId(2);
        //user.addUser("petur_petrov","joepeshi","petrov21@abv.bg",role);
        ClientsTable client = new ClientsTable();
        Users usr = new Users();
        //usr.setId(9);
        //client.addClient("ul. Petur Beron 23",usr);
        OrganizerTable org = new OrganizerTable();
        Clients clnt = new Clients();
        //clnt.setId(7);


        OrganizerEventsTable oeTable = new OrganizerEventsTable();
        Organizer organizer = new Organizer();
        OrganizerTable orgTable = new OrganizerTable();
        organizer=orgTable.getOrganizer(11);

        Event event = new Event();
        EventTable et = new EventTable();
        //event=et.getEvent(5);

        //oeTable.addOrgEvent(organizer,event);
       // oeTable.updateOrgEvent(3,organizer,event);


    ENTITY_MANAGER_FACTORY.close();
    }
}

