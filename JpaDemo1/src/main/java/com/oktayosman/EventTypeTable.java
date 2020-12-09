package com.oktayosman;


import javax.persistence.*;
import java.sql.DriverManager;
import java.util.List;

public class EventTypeTable {


    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");


    public void addEventType(String eventType) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            EventType eventTypeObj = new EventType();
            eventTypeObj.setEventType(eventType);
            em.persist(eventTypeObj);
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


    public EventType getEventType(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT e FROM EventType e WHERE e.id = :id_event";
        TypedQuery<EventType> tq = em.createQuery(query, EventType.class);
        tq.setParameter("id_event", id);
        EventType eventTypeObj = null;
        try {
            eventTypeObj = tq.getSingleResult();
            System.out.println(eventTypeObj.getEventType());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return eventTypeObj;
    }


    public List<EventType> getAllEventTypes() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT e FROM EventType e WHERE e.id IS NOT NULL";
        TypedQuery<EventType> tq = em.createQuery(strQuery, EventType.class);
        List<EventType> eventTypes = null;
        try {
            eventTypes = tq.getResultList();
            eventTypes.forEach(eventType->System.out.println(eventType.getEventType()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return eventTypes;
    }


    public void updateEventType(int id, EventType eventType) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            eventType.setId(id);
            em.merge(eventType);
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


    public void deleteEventType(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        EventType eventType = null;

        try {
            et = em.getTransaction();
            et.begin();
            eventType = em.find(EventType.class, id);
            em.remove(eventType);
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
