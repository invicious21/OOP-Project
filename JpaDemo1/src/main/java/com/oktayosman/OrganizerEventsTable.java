package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class OrganizerEventsTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addOrgEvent(Organizer organizer , Event event) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            OrganizerEvents orgEvObj = new OrganizerEvents();
            orgEvObj.setOrganizer(organizer);
            orgEvObj.setEvent(event);
            em.persist(orgEvObj);
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

    public OrganizerEvents getOrgEvent(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT oe FROM OrganizerEvents oe WHERE oe.id = :id";
        TypedQuery<OrganizerEvents> tq = em.createQuery(query, OrganizerEvents.class);
        tq.setParameter("id", id);
        OrganizerEvents oeObj = null;

        try {
            oeObj = tq.getSingleResult();
            System.out.println(oeObj.getId());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return oeObj;
    }

    public List<OrganizerEvents> getAllOrgEvents() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT oe FROM OrganizerEvents oe WHERE oe.id IS NOT NULL";
        TypedQuery<OrganizerEvents> tq = em.createQuery(strQuery, OrganizerEvents.class);
        List<OrganizerEvents> orgEvents = null;
        try {
            orgEvents = tq.getResultList();
            orgEvents.forEach(orgEvent->System.out.println("Org-Event id: "+orgEvent.getId()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return orgEvents;
    }

    public void updateOrgEvent(int id,Organizer organizer,Event event) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            event.setId(id);
            em.merge(organizer);
            em.merge(event);
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

    public void deleteOrgEvent(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        OrganizerEvents orgEvent = null;

        try {
            et = em.getTransaction();
            et.begin();
            orgEvent = em.find(OrganizerEvents.class, id);
            em.remove(orgEvent);
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
