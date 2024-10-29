package com.hospital.DAOImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hospital.Dao.MedicalRecordDAO;
import com.hospital.entity.MedicalRecord;

import java.util.List;

public class MedicalRecordDAOImpl implements MedicalRecordDAO {
    private SessionFactory sessionFactory;

    public MedicalRecordDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(MedicalRecord medicalRecord) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(medicalRecord);
            transaction.commit();
            System.out.println("Medical record added successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error adding medical record: " + e.getMessage());
        }
    }

    @Override
    public MedicalRecord read(int mcid) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MedicalRecord.class, mcid);
        }
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(medicalRecord);
            transaction.commit();
            System.out.println("Medical record updated successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating medical record: " + e.getMessage());
        }
    }

    @Override
    public void delete(int mcid) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            MedicalRecord medicalRecord = read(mcid);
            if (medicalRecord != null) {
                session.delete(medicalRecord);
                System.out.println("Medical record deleted successfully!");
            } else {
                System.out.println("Medical record not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting medical record: " + e.getMessage());
        }
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM MedicalRecord", MedicalRecord.class).list();
        }
    }
}
