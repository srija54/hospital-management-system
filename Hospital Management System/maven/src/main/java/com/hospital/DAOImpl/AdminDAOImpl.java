package com.hospital.DAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hospital.Dao.AdminDAO;
import com.hospital.entity.Admin;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    private SessionFactory sessionFactory;

    public AdminDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Admin admin) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
            System.out.println("Admin added successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error adding admin: " + e.getMessage());
        }
    }

    @Override
    public Admin read(int aid) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Admin.class, aid);
        }
    }

    @Override
    public void update(Admin admin) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(admin);
            transaction.commit();
            System.out.println("Admin updated successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating admin: " + e.getMessage());
        }
    }

    @Override
    public void delete(int aid) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Admin admin = read(aid);
            if (admin != null) {
                session.delete(admin);
                System.out.println("Admin deleted successfully!");
            } else {
                System.out.println("Admin not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting admin: " + e.getMessage());
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Admin", Admin.class).list();
        }
    }
}
