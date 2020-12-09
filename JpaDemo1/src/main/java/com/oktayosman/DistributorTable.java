package com.oktayosman;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class DistributorTable implements Serializable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addDistributor(String firstName,String lastName,String phoneNumber,double honorarium,Clients client) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Distributor distributorObj = new Distributor();
            distributorObj.setPhoneNumber(phoneNumber);
            distributorObj.setHonorarium(honorarium);
            distributorObj.setClient(client);
            em.persist(distributorObj);
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

    public Distributor getDistributor(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT d FROM Distributor d WHERE d.id = :id";
        TypedQuery<Distributor> tq = em.createQuery(query, Distributor.class);
        tq.setParameter("id", id);
        Distributor distributorObj = null;

        try {
            distributorObj = tq.getSingleResult();
            System.out.println(distributorObj.getId()+" "+
                    distributorObj.getPhoneNumber()+ " "+distributorObj.getHonorarium());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return distributorObj;
    }

    public List<Distributor> getAllDistributors() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT d FROM Distributor d WHERE d.id IS NOT NULL";
        TypedQuery<Distributor> tq = em.createQuery(strQuery, Distributor.class);
        List<Distributor> distributors = null;
        try {
            distributors = tq.getResultList();
            distributors.forEach(distributor->System.out.println("Distributor id: "+distributor.getId()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return distributors;
    }

    public void updateDistributor(int id,Distributor distributor) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            distributor.setId(id);
            em.merge(distributor);
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

    public void deleteDistributor(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Distributor distributor = null;

        try {
            et = em.getTransaction();
            et.begin();
            distributor = em.find(Distributor.class, id);
            em.remove(distributor);
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
