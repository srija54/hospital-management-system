package com.hospital.DAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hospital.Dao.DoctorDAO;
import com.hospital.entity.Doctor;

import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {
    private SessionFactory sessionFactory;

    public DoctorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Doctor doctor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(doctor);
            transaction.commit();
            System.out.println("Doctor added successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error adding doctor: " + e.getMessage());
        }
    }

    @Override
    public Doctor read(int did) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Doctor.class, did);
        }
    }

    @Override
    public void update(Doctor doctor) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(doctor);
            transaction.commit();
            System.out.println("Doctor updated successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating doctor: " + e.getMessage());
        }
    }

    @Override
    public void delete(int did) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Doctor doctor = read(did);
            if (doctor != null) {
                session.delete(doctor);
                System.out.println("Doctor deleted successfully!");
            } else {
                System.out.println("Doctor not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting doctor: " + e.getMessage());
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Doctor", Doctor.class).list();
        }
    }
}
