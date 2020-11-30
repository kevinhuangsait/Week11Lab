package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;


public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
        
    }
    
    public User getByUUID(String id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        User user = null;
        
        user = em.createNamedQuery("User.findByResetPasswordUuid", User.class).setParameter("resetPasswordUuid", id).getSingleResult();
        
        em.close();
        
        return user;
    }
    
    public void update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(user);
        t.commit();
        em.close();
    }
}
