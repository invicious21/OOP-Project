package com.oktayosman;

import javax.persistence.*;
import java.util.List;

public class RoleTable {

    private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JpaDemo1");

    public void addRole(String role) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Role roleObj = new Role();
            roleObj.setRole(role);
            em.persist(roleObj);
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


        public Role getRole(int id) {
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
            String query = "SELECT r FROM Role r WHERE r.id = :role_id";
            TypedQuery<Role> tq = em.createQuery(query, Role.class);
            tq.setParameter("role_id", id);
            Role roleObj = null;
            try {
                roleObj = tq.getSingleResult();
                System.out.println(roleObj.getRole());
            }
            catch (NoResultException ex) {
                ex.printStackTrace();
            }
            finally {
                em.close();
            }

            return roleObj;
        }

        public List<Role> getAllRoles() {
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
            String strQuery = "SELECT r FROM Role r WHERE r.id IS NOT NULL";
            TypedQuery<Role> tq = em.createQuery(strQuery, Role.class);
            List<Role> roles = null;
            try {
                roles = tq.getResultList();
                roles.forEach(role->System.out.println(role.getRole()));
            }
            catch (NoResultException ex) {
                ex.printStackTrace();
            }
            finally {
                em.close();
            }

            return roles;
        }




}
