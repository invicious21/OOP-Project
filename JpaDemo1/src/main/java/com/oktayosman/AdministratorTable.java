package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class AdministratorTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addAdministrator(String firstName,String lastName,Users user) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Administrator adminObj = new Administrator();
            adminObj.setFirstName(firstName);
            adminObj.setLastName(lastName);
            adminObj.setUser(user);
            em.persist(adminObj);
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


    public Administrator getAdministrator(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT a FROM Administrator a WHERE a.id = :id";
        TypedQuery<Administrator> tq = em.createQuery(query, Administrator.class);
        tq.setParameter("id", id);
        Administrator adminObj = null;

        try {
            adminObj = tq.getSingleResult();
            System.out.println(adminObj.getId()+ " "+adminObj.getFirstName()+ " "
                    +adminObj.getLastName());
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return adminObj;
    }

    public List<Administrator> getAllAdmins() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT a FROM Administrator a WHERE a.id IS NOT NULL";
        TypedQuery<Administrator> tq = em.createQuery(strQuery, Administrator.class);
        List<Administrator> admins = null;
        try {
            admins = tq.getResultList();
            admins.forEach(admin->System.out.println("Administrator id: "+admin.getId()+" "+
                    admin.getFirstName()+" "+admin.getLastName()));
        }
        catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

        return admins;
    }

    public void updateAdministrator(int id,Administrator administrator) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            administrator.setId(id);
            em.merge(administrator);
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

    public void deleteAdministrator(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Administrator administrator = null;

        try {
            et = em.getTransaction();
            et.begin();
            administrator = em.find(Administrator.class, id);
            em.remove(administrator);
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
