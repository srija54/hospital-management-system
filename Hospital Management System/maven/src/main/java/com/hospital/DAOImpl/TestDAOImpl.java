package com.hospital.DAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hospital.Dao.TestDAO;
import com.hospital.entity.Test;

import java.util.List;

public class TestDAOImpl implements TestDAO {
    private SessionFactory sessionFactory;

    public TestDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Test test) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(test);
            transaction.commit();
            System.out.println("Test added successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error adding test: " + e.getMessage());
        }
    }

    @Override
    public Test read(int testid) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Test.class, testid);
        }
    }

    @Override
    public void update(Test test) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(test);
            transaction.commit();
            System.out.println("Test updated successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating test: " + e.getMessage());
        }
    }

    @Override
    public void delete(int testid) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Test test = read(testid);
            if (test != null) {
                session.delete(test);
                System.out.println("Test deleted successfully!");
            } else {
                System.out.println("Test not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting test: " + e.getMessage());
        }
    }

    @Override
    public List<Test> getAllTests() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Test", Test.class).list();
        }
    }
}
