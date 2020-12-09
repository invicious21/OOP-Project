package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class SeatTypeTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");


    //not working SQL syntax error


    public void addSeatType(String seatType,double price) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            SeatType seatTypeObj = new SeatType();

            seatTypeObj.setType(seatType);
            seatTypeObj.setPrice(price);
            em.persist(seatTypeObj);
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

    public SeatType getSeatType(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT s1 FROM SeatType s1 WHERE s1.id = :id";
        TypedQuery<SeatType> tq = em.createQuery(query, SeatType.class);
        tq.setParameter("id", id);
        SeatType seatTypeObj = null;
        try {
            seatTypeObj = tq.getSingleResult();
            System.out.println(seatTypeObj.getType());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return seatTypeObj;
    }

    public List<SeatType> getAllSeatTypes() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT s1 FROM SeatType s1 WHERE s1.id IS NOT NULL";
        TypedQuery<SeatType> tq = em.createQuery(strQuery, SeatType.class);
        List<SeatType> seatTypes = null;
        try {
            seatTypes = tq.getResultList();
            seatTypes.forEach(seatType->System.out.println(seatType.getType()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return seatTypes;
    }

    public void updateSeatType(int id, SeatType seatType) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            seatType.setId(id);
            em.merge(seatType);
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

    public void deleteSeatType(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        SeatType seatType = null;

        try {
            et = em.getTransaction();
            et.begin();
            seatType = em.find(SeatType.class, id);
            em.remove(seatType);
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
