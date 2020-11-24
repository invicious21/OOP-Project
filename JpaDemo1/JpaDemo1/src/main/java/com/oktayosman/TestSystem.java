package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class TestSystem {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");


    public static void main(String[] args) {

        EventTypeTable eventType = new EventTypeTable();

        //eventType.addEventType(2,"Music festival");



          UsersTable usr = new UsersTable();
          Users user = new Users();
          //usr.getUser(5,"invicious3");
         // usr.deleteUser(5);

          //usr.addUser("invicious3","invicious21","invicious3@abv.bg","0898982695","Oktay","Erol");
          //usr.getAllUsers();
          //SeatTypeTable seatType = new SeatTypeTable();
         // RoleTable role = new RoleTable();
          //usr.addUser("distributor1","distributor1","distributor1@abv.bg","0898982690","Boqn","Boqnov",role.getRole(3));
         // OrganizerTable organizer = new OrganizerTable();
         // organizer.addOrganizer(3000,user);








        ENTITY_MANAGER_FACTORY.close();
    }
}

