package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class DistributorEventsTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addOrgEvent(Distributor distributor , Event event) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            DistributorEvents distEventObj = new DistributorEvents();
            distEventObj.setDistributor(distributor);
            distEventObj.setEvent(event);
            em.persist(distEventObj);
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

    public DistributorEvents getDistEvent(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT de FROM DistributorEvents de WHERE de.id = :id";
        TypedQuery<DistributorEvents> tq = em.createQuery(query, DistributorEvents.class);
        tq.setParameter("id", id);
        DistributorEvents distEvObj = null;

        try {
            distEvObj = tq.getSingleResult();
            System.out.println(distEvObj.getId());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return distEvObj;
    }

    public List<DistributorEvents> getAllDistEvents() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT de FROM Event de WHERE de.id IS NOT NULL";
        TypedQuery<DistributorEvents> tq = em.createQuery(strQuery, DistributorEvents.class);
        List<DistributorEvents> distEvents = null;
        try {
            distEvents = tq.getResultList();
            distEvents.forEach(distEvent->System.out.println("Dist-Event id: "+distEvent.getId()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return distEvents;
    }

    public void updateDistEvent(int id,Distributor distributor,Event event) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            event.setId(id);
            em.merge(distributor);
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


    public void deleteDistEvent(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        DistributorEvents distEvent = null;

        try {
            et = em.getTransaction();
            et.begin();
            distEvent = em.find(DistributorEvents.class, id);
            em.remove(distEvent);
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
