package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class ClientsTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");


    public void addClient(String address,Users user) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Clients clientObj = new Clients();
            clientObj.setAddress(address);
            clientObj.setUser(user);
            em.persist(clientObj);
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

    public Clients getClient(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM Clients c WHERE c.id = :id";
        TypedQuery<Clients> tq = em.createQuery(query, Clients.class);
        tq.setParameter("id", id);
        Clients clientObj = null;
        Users userObj = null;
        try {
            clientObj = tq.getSingleResult();
            System.out.println(clientObj.getId());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return clientObj;
    }

    public List<Clients> getAllClients() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT c FROM Clients c WHERE c.id IS NOT NULL";
        TypedQuery<Clients> tq = em.createQuery(strQuery, Clients.class);
        List<Clients> clients = null;
        try {
            clients = tq.getResultList();
            clients.forEach(client->System.out.println("Client id: "+client.getId()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return clients;
    }


    public void updateClient(int id,Clients client) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            client.setId(id);
            //client.setAddress(address);
            em.merge(client);
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

    public void deleteClient(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Clients client = null;

        try {
            et = em.getTransaction();
            et.begin();
            client = em.find(Clients.class, id);
            em.remove(client);
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
