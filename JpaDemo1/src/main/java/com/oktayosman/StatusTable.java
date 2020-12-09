package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class StatusTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");


// WORKING
    public void addStatus(String status) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Status statusObj = new Status();
            statusObj.setStatus(status);
            em.persist(statusObj);
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

    //working
    public Status getStatus(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s FROM Status s WHERE s.id = :id";
        TypedQuery<Status> tq = em.createQuery(query, Status.class);
        tq.setParameter("id", id);
        Status statusObj = null;
        try {
            statusObj = tq.getSingleResult();
            System.out.println(statusObj.getStatus());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return statusObj;
    }

//working
    public List<Status> getAllStatuses() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT s FROM Status s WHERE s.id IS NOT NULL";
        TypedQuery<Status> tq = em.createQuery(strQuery, Status.class);
        List<Status> statuses = null;
        try {
            statuses = tq.getResultList();
            statuses.forEach(status->System.out.println(status.getStatus()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return statuses;
    }
//working
    public void updateStatus(int id, Status status) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            status.setId(id);
            em.merge(status);
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

//working
    public void deleteStatus(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Status status = null;

        try {
            et = em.getTransaction();
            et.begin();
            status = em.find(Status.class, id);
            em.remove(status);
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


