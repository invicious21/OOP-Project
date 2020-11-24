package com.oktayosman;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OrganizerTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addOrganizer(double honorarium,Users user) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Organizer organizerObj = new Organizer();
            //organizerObj.setId_organizer(id);
            organizerObj.setHonorarium(honorarium);
            organizerObj.setUser_id(user);
            em.persist(organizerObj);
            et.commit();
        }
        catch(Exception ex) {
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();

        }
    }


}
