package org.mentor.project.dao;

import org.hibernate.Transaction;
import org.mentor.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private EntityManagerFactory factory;

    @Autowired
    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }


    @Override
    public void save(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
    }

    @Override
    public List<User> getAll() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        List<User> list =  manager
                .createNativeQuery("SELECT * FROM persons",User.class).getResultList();
        manager.getTransaction().commit();
        return list;
    }

    @Override
    public User findById(Long id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = manager.find(User.class,id);
        manager.getTransaction().commit();
        return user;
    }


    @Override
    public void update(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("UPDATE User SET name =?1, surname= ?2, age= ?3 WHERE id= ?4");
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getSurname());
        query.setParameter(3, user.getAge());
        query.setParameter(4,user.getId());
        query.executeUpdate();
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = manager.find(User.class,id);
        manager.remove(user);
        manager.getTransaction().commit();
    }
}

