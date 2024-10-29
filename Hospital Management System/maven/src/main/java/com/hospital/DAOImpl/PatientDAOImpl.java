package com.hospital.DAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hospital.Dao.PatientDAO;
import com.hospital.entity.Patient;

import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private SessionFactory sessionFactory;

    public PatientDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Patient patient) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession(); // Open the session first
        try {
            transaction = session.beginTransaction();
            session.save(patient);
            transaction.commit();
            System.out.println("Patient added successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error adding patient: " + e.getMessage());
        } finally {
            session.close(); // Close the session in the finally block
        }
    }


    @Override
    public Patient read(int pid) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Patient.class, pid);
        }
    }

    @Override
    public void update(Patient patient) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(patient);
            transaction.commit();
            System.out.println("Patient updated successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating patient: " + e.getMessage());
        }
    }

    @Override
    public void delete(int pid) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, pid); // Directly retrieve patient
            if (patient != null) {
                session.delete(patient);
                transaction.commit();
                System.out.println("Patient deleted successfully!");
            } else {
                transaction.commit(); // Commit even if patient is not found
                System.out.println("Patient not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting patient: " + e.getMessage());
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Patient", Patient.class).list();
        }
    }
}
