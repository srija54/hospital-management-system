package com.hospital.DAOImpl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hospital.Dao.HospitalDAO;
import com.hospital.entity.Hospital;

import java.util.List;

public class HospitalDAOImpl implements HospitalDAO {
    private SessionFactory sessionFactory;

    public HospitalDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Hospital hospital) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(hospital);
            transaction.commit();
            System.out.println("Hospital added successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error adding hospital: " + e.getMessage());
        }
    }

    @Override
    public Hospital read(int hid) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Hospital.class, hid);
        }
    }

    @Override
    public void update(Hospital hospital) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(hospital);
            transaction.commit();
            System.out.println("Hospital updated successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating hospital: " + e.getMessage());
        }
    }

    @Override
    public void delete(int hid) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Hospital hospital = read(hid);
            if (hospital != null) {
                session.delete(hospital);
                System.out.println("Hospital deleted successfully!");
            } else {
                System.out.println("Hospital not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting hospital: " + e.getMessage());
        }
    }

    @Override
    public List<Hospital> getAllHospitals() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Hospital", Hospital.class).list();
        }
    }
}
