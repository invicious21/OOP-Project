package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class OrganizerTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addOrganizer(String firstName,String lastName,String phoneNumber,double honorarium,Clients client) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Organizer organizerObj = new Organizer();
            organizerObj.setPhoneNumber(phoneNumber);
            organizerObj.setHonorarium(honorarium);
            organizerObj.setClient(client);
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

    public Organizer getOrganizer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT o FROM Organizer o WHERE o.id = :id";
        TypedQuery<Organizer> tq = em.createQuery(query, Organizer.class);
        tq.setParameter("id", id);
        Organizer organizerObj = null;
        try {
            organizerObj = tq.getSingleResult();
            System.out.println(organizerObj.getId()+ " "
                    + " "+organizerObj.getPhoneNumber()+ " "+organizerObj.getHonorarium());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return organizerObj;
    }


    public List<Organizer> getAllOrganizers() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT o FROM Organizer o WHERE o.id IS NOT NULL";
        TypedQuery<Organizer> tq = em.createQuery(strQuery, Organizer.class);
        List<Organizer> organizers = null;
        try {
            organizers = tq.getResultList();
            organizers.forEach(organizer->System.out.println("Organizer id: "+organizer.getId()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return organizers;
    }

    public void updateDistributor(int id,Organizer organizer) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            organizer.setId(id);
            em.merge(organizer);
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

    public void deleteOrganizer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Organizer organizer = null;

        try {
            et = em.getTransaction();
            et.begin();
            organizer = em.find(Organizer.class, id);
            em.remove(organizer);
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
