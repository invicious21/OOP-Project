package com.oktayosman;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class EventTable{

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addEvent(int maxTicketsBought, String date, EventType eventType,Status status) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Event eventObj = new Event();
            eventObj.setMaxTicketsBoughtBy1(maxTicketsBought);
            eventObj.setDate(date);
            eventObj.setEventType(eventType);
            eventObj.setStatus(status);
            em.persist(eventObj);
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


    public Event getEvent(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT e FROM Event e WHERE e.id = :id";
        TypedQuery<Event> tq = em.createQuery(query, Event.class);
        tq.setParameter("id", id);
        Event eventObj = null;

        try {
            eventObj = tq.getSingleResult();
            System.out.println(eventObj.getId());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return eventObj;
    }

    public List<Event> getAllEvents() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM Event e WHERE e.id IS NOT NULL";
        TypedQuery<Event> tq = em.createQuery(strQuery, Event.class);
        List<Event> events = null;
        try {
            events = tq.getResultList();
            events.forEach(event->System.out.println("Event id: "+event.getId()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return events;
    }


    public void updateEvent(int id,Event event) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            event.setId(id);
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

    public void deleteEvent(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Event event = null;

        try {
            et = em.getTransaction();
            et.begin();
            event = em.find(Event.class, id);
            em.remove(event);
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
