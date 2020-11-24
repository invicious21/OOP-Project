package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class UsersTable {


    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addUser(String username,String password, String email,String user_phone,
                        String first_name,String last_name,Role id_role) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Users usr = new Users();
            usr.setUsername(username);
            usr.setPassword(password);
            usr.setEmail(email);
            usr.setUser_phone(user_phone);
            usr.setFirst_name(first_name);
            usr.setLast_name(last_name);
            usr.setRole(id_role);
            em.persist(usr);
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

        public Users getUser(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT u FROM Users u WHERE u.id = :usrID";
        TypedQuery<Users> tq = em.createQuery(query, Users.class);
        tq.setParameter("usrID", id);
        Users usr = null;
        Role role = null;
            try {
                usr = tq.getSingleResult();
                System.out.println(usr.getFirst_name() + " " + usr.getLast_name() + " " + usr.getEmail()+" "+role.getRole());
            } catch (NoResultException ex) {
                ex.printStackTrace();
            } finally {
                em.close();
            }
            return usr;

        }

        public List<Users> getAllUsers() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT u FROM Users u WHERE u.id IS NOT NULL";
        TypedQuery<Users> tq = em.createQuery(strQuery, Users.class);

        List<Users> usrs = null;

            try{
                usrs = tq.getResultList();
                usrs.forEach(users-> System.out.println(users.getUsername()+ " "+users.getFirst_name() + " "+users.getLast_name()));
            }
            catch(NoResultException ex) {
                ex.printStackTrace();
            }
            finally {
                em.close();
            }
            return usrs;
    }

//    public void updateUserNumber(int id, Users user) {
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction et = null;
//
//        try {
//            et = em.getTransaction();
//            et.begin();
//            user.setId(id);
//            em.merge(user);
//            et.commit();
//        }
//        catch(Exception ex) {
//            if(et != null) {
//                et.rollback();
//            }
//            ex.printStackTrace();
//        }
//        finally {
//            em.close();
//        }
//    }

    public void deleteUser(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Users user = null;

        try {
            et = em.getTransaction();
            et.begin();
            user = em.find(Users.class, id);
            em.remove(user);
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
