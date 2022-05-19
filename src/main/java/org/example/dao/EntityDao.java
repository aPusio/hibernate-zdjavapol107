package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public abstract class EntityDao <T>{
    protected SessionFactory sessionFactory;
    private Class<T> clazz;

    public EntityDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public Long save(T entity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    public Optional<T> getById(Long id){
        Session session = sessionFactory.openSession();
        T entity = session.find(clazz, id);
        session.close();
        return Optional.ofNullable(entity);
    }

    public void update(T entity){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void delete(Long id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        getById(id).ifPresent(session::delete);
//        Optional<Author> byId = getById(id);
//        byId.ifPresent(session::delete);
        transaction.commit();
        session.close();
    }

}
