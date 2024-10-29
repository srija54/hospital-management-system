package HospitalManagementSystem;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hospital.DAOImpl.AdminDAOImpl;
import com.hospital.DAOImpl.DoctorDAOImpl;
import com.hospital.DAOImpl.HospitalDAOImpl;
import com.hospital.DAOImpl.MedicalRecordDAOImpl;
import com.hospital.DAOImpl.PatientDAOImpl;
import com.hospital.DAOImpl.TestDAOImpl;
import com.hospital.entity.Admin;
import com.hospital.entity.Doctor;
import com.hospital.entity.Hospital;
import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Patient;
import com.hospital.entity.Test;

import java.util.List;
import java.util.Scanner;


public class App {
    // SessionFactory for managing database sessions
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    // DAO instances for different entities
    private static HospitalDAOImpl hospitalDAO = new HospitalDAOImpl(sessionFactory);
    private static PatientDAOImpl patientDAO = new PatientDAOImpl(sessionFactory);
    private static TestDAOImpl testDAO = new TestDAOImpl(sessionFactory);
    private static MedicalRecordDAOImpl medicalRecordDAO = new MedicalRecordDAOImpl(sessionFactory);
    private static DoctorDAOImpl doctorDAO = new DoctorDAOImpl(sessionFactory);
    private static AdminDAOImpl adminDAO = new AdminDAOImpl(sessionFactory);

    // Main method to run the application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Main menu loop
        do {
            System.out.println("=== Hospital Management System ===");
            System.out.println("1. Manage Hospitals");
            System.out.println("2. Manage Patients");
            System.out.println("3. Manage Tests");
            System.out.println("4. Manage Medical Records");
            System.out.println("5. Manage Doctors");
            System.out.println("6. Manage Admins");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            // Switch case to handle user choice
            switch (choice) {
                case 1:
                    manageHospitals(scanner);
                    break;
                case 2:
                    managePatients(scanner);
                    break;
                case 3:
                    manageTests(scanner);
                    break;
                case 4:
                    manageMedicalRecords(scanner);
                    break;
                case 5:
                    manageDoctors(scanner);
                    break;
                case 6:
                    manageAdmins(scanner);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 7);

        // Cleanup
        sessionFactory.close();
        scanner.close();
    }

    // Method to manage hospitals
    private static void manageHospitals(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== Manage Hospitals ===");
            System.out.println("1. Add Hospital");
            System.out.println("2. Update Hospital");
            System.out.println("3. Delete Hospital");
            System.out.println("4. View All Hospitals");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addHospital(scanner);
                    break;
                case 2:
                    updateHospital(scanner);
                    break;
                case 3:
                    deleteHospital(scanner);
                    break;
                case 4:
                    viewAllHospitals();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 6);
    }

    // Method to add a hospital
    private static void addHospital(Scanner scanner) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Hospital Name: ");
        String hname = scanner.nextLine();
        System.out.print("Enter Hospital Address: ");
        String haddress = scanner.nextLine();
        System.out.print("Enter Hospital Contact: ");
        String hcontact = scanner.nextLine();

        Hospital hospital = new Hospital(hname, haddress, hcontact);
        hospitalDAO.create(hospital);
    }

    // Method to update a hospital
    private static void updateHospital(Scanner scanner) {
        System.out.print("Enter Hospital ID to update: ");
        int hid = scanner.nextInt();
        Hospital hospital = hospitalDAO.read(hid);

        if (hospital != null) {
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new Hospital Name: ");
            hospital.setHname(scanner.nextLine());
            System.out.print("Enter new Hospital Address: ");
            hospital.setHaddress(scanner.nextLine());
            System.out.print("Enter new Hospital Contact: ");
            hospital.setHcontact(scanner.nextLine());

            hospitalDAO.update(hospital);
            System.out.println("Hospital updated successfully.");
        } else {
            System.out.println("Hospital not found.");
        }
    }

    // Method to delete a hospital
    private static void deleteHospital(Scanner scanner) {
        System.out.print("Enter Hospital ID to delete: ");
        int hid = scanner.nextInt();
        hospitalDAO.delete(hid);
        System.out.println("Hospital deleted successfully.");
    }

    // Method to view all hospitals
    private static void viewAllHospitals() {
        List<Hospital> hospitals = hospitalDAO.getAllHospitals();
        if (hospitals.isEmpty()) {
            System.out.println("No hospitals found.");
        } else {
            System.out.println("=== List of Hospitals ===");
            for (Hospital hospital : hospitals) {
                System.out.println("ID: " + hospital.getHid() + ", Name: " + hospital.getHname());
            }
        }
    }

    // Method to manage patients
    private static void managePatients(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== Manage Patients ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Update Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. View All Patients");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    updatePatient(scanner);
                    break;
                case 3:
                    deletePatient(scanner);
                    break;
                case 4:
                    viewAllPatients();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }

    // Method to add a patient
    private static void addPatient(Scanner scanner) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Patient Name: ");
        String pname = scanner.nextLine();
        System.out.print("Enter Patient Contact: ");
        String pcontact = scanner.nextLine();
        System.out.print("Enter Patient Address: ");
        String paddress = scanner.nextLine();
        System.out.print("Enter Patient DOB (YYYY-MM-DD): ");
        String pdob = scanner.nextLine();

        Patient patient = new Patient();
        patientDAO.create(patient);
    }

    // Method to update a patient
    private static void updatePatient(Scanner scanner) {
        System.out.print("Enter Patient ID to update: ");
        int pid = scanner.nextInt();
        Patient patient = patientDAO.read(pid);

        if (patient != null) {
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new Patient Name: ");
            patient.setPname(scanner.nextLine());
            System.out.print("Enter new Patient Contact: ");
            patient.setPcontact(scanner.nextLine());
            System.out.print("Enter new Patient Address: ");
            patient.setPaddress(scanner.nextLine());
            System.out.print("Enter new Patient DOB (YYYY-MM-DD): ");
            patient.setPdob(java.sql.Date.valueOf(scanner.nextLine()));

            patientDAO.update(patient);
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Method to delete a patient
    private static void deletePatient(Scanner scanner) {
        System.out.print("Enter Patient ID to delete: ");
        int pid = scanner.nextInt();
        patientDAO.delete(pid);
        System.out.println("Patient deleted successfully.");
    }

    // Method to view all patients
    private static void viewAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("=== List of Patients ===");
            for (Patient patient : patients) {
                System.out.println("ID: " + patient.getPid() + ", Name: " + patient.getPname());
            }
        }
    }

    // Method to manage tests
    private static void manageTests(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== Manage Tests ===");
            System.out.println("1. Add Test");
            System.out.println("2. Update Test");
            System.out.println("3. Delete Test");
            System.out.println("4. View All Tests");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTest(scanner);
                    break;
                case 2:
                    updateTest(scanner);
                    break;
                case 3:
                    deleteTest(scanner);
                    break;
                case 4:
                    viewAllTests();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }

    // Method to add a test
    private static void addTest(Scanner scanner) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Test Name: ");
        String tname = scanner.nextLine();
        System.out.print("Enter Test Price: ");
        double tprice = scanner.nextDouble();

        Test test = new Test(tname, tprice);
        testDAO.create(test);
    }

    // Method to update a test
    private static void updateTest(Scanner scanner) {
        System.out.print("Enter Test ID to update: ");
        int testid = scanner.nextInt();
        Test test = testDAO.read(testid);

        if (test != null) {
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new Test Name: ");
            test.setTname(scanner.nextLine());
            System.out.print("Enter new Test Price: ");
            test.setTprice(scanner.nextDouble());

            testDAO.update(test);
            System.out.println("Test updated successfully.");
        } else {
            System.out.println("Test not found.");
        }
    }

    // Method to delete a test
    private static void deleteTest(Scanner scanner) {
        System.out.print("Enter Test ID to delete: ");
        int testid = scanner.nextInt();
        testDAO.delete(testid);
        System.out.println("Test deleted successfully.");
    }

    // Method to view all tests
    private static void viewAllTests() {
        List<Test> tests = testDAO.getAllTests();
        if (tests.isEmpty()) {
            System.out.println("No tests found.");
        } else {
            System.out.println("=== List of Tests ===");
            for (Test test : tests) {
                System.out.println("ID: " + test.getTestid() + ", Name: " + test.getTname() + ", Price: " + test.getTprice());
            }
        }
    }

    // Method to manage medical records
    private static void manageMedicalRecords(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== Manage Medical Records ===");
            System.out.println("1. Add Medical Record");
            System.out.println("2. Update Medical Record");
            System.out.println("3. Delete Medical Record");
            System.out.println("4. View All Medical Records");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addMedicalRecord(scanner);
                    break;
                case 2:
                    updateMedicalRecord(scanner);
                    break;
                case 3:
                    deleteMedicalRecord(scanner);
                    break;
                case 4:
                    viewAllMedicalRecords();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }

    private static void addMedicalRecord(Scanner scanner) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Medical Problem: ");
        String mproblem = scanner.nextLine();
        System.out.print("Enter Medical Record Date (YYYY-MM-DD): ");
        String mdate = scanner.nextLine();

        try {
            // Convert string to java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(mdate);
            MedicalRecord medicalRecord = new MedicalRecord();

            medicalRecordDAO.create(medicalRecord);
            System.out.println("Medical Record added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }


    // Method to update a medical record
    private static void updateMedicalRecord(Scanner scanner) {
        System.out.print("Enter Medical Record ID to update: ");
        int mcid = scanner.nextInt();
        MedicalRecord medicalRecord = medicalRecordDAO.read(mcid);

        if (medicalRecord != null) {
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new Medical Problem: ");
            medicalRecord.setMproblem(scanner.nextLine());
            System.out.print("Enter new Medical Record Date (YYYY-MM-DD): ");
            medicalRecord.setMdate(java.sql.Date.valueOf(scanner.nextLine()));

            medicalRecordDAO.update(medicalRecord);
            System.out.println("Medical Record updated successfully.");
        } else {
            System.out.println("Medical Record not found.");
        }
    }

    // Method to delete a medical record
    private static void deleteMedicalRecord(Scanner scanner) {
        System.out.print("Enter Medical Record ID to delete: ");
        int mcid = scanner.nextInt();
        medicalRecordDAO.delete(mcid);
        System.out.println("Medical Record deleted successfully.");
    }

    // Method to view all medical records
    private static void viewAllMedicalRecords() {
        List<MedicalRecord> records = medicalRecordDAO.getAllMedicalRecords();
        if (records.isEmpty()) {
            System.out.println("No medical records found.");
        } else {
            System.out.println("=== List of Medical Records ===");
            for (MedicalRecord record : records) {
                System.out.println("ID: " + record.getMcid() + ", Problem: " + record.getMproblem());
            }
        }
    }

    // Method to manage doctors
    private static void manageDoctors(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== Manage Doctors ===");
            System.out.println("1. Add Doctor");
            System.out.println("2. Update Doctor");
            System.out.println("3. Delete Doctor");
            System.out.println("4. View All Doctors");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addDoctor(scanner);
                    break;
                case 2:
                    updateDoctor(scanner);
                    break;
                case 3:
                    deleteDoctor(scanner);
                    break;
                case 4:
                    viewAllDoctors();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }

    // Method to add a doctor
    private static void addDoctor(Scanner scanner) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Doctor Name: ");
        String dname = scanner.nextLine();
        System.out.print("Enter Doctor Speciality: ");
        String dspeciality = scanner.nextLine();
        System.out.print("Enter Doctor Contact: ");
        String dcontact = scanner.nextLine();

        Doctor doctor = new Doctor(dname, dspeciality, dcontact);
        doctorDAO.create(doctor);
    }

    // Method to update a doctor
    private static void updateDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID to update: ");
        int did = scanner.nextInt();
        Doctor doctor = doctorDAO.read(did);

        if (doctor != null) {
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new Doctor Name: ");
            doctor.setDname(scanner.nextLine());
            System.out.print("Enter new Doctor Speciality: ");
            doctor.setDspeciality(scanner.nextLine());
            System.out.print("Enter new Doctor Contact: ");
            doctor.setDcontact(scanner.nextLine());

            doctorDAO.update(doctor);
            System.out.println("Doctor updated successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    // Method to delete a doctor
    private static void deleteDoctor(Scanner scanner) {
        System.out.print("Enter Doctor ID to delete: ");
        int did = scanner.nextInt();
        doctorDAO.delete(did);
        System.out.println("Doctor deleted successfully.");
    }

    // Method to view all doctors
    private static void viewAllDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            System.out.println("=== List of Doctors ===");
            for (Doctor doctor : doctors) {
                System.out.println("ID: " + doctor.getDid() + ", Name: " + doctor.getDname());
            }
        }
    }

    // Method to manage admins
    private static void manageAdmins(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n=== Manage Admins ===");
            System.out.println("1. Add Admin");
            System.out.println("2. Update Admin");
            System.out.println("3. Delete Admin");
            System.out.println("4. View All Admins");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addAdmin(scanner);
                    break;
                case 2:
                    updateAdmin(scanner);
                    break;
                case 3:
                    deleteAdmin(scanner);
                    break;
                case 4:
                    viewAllAdmins();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }

    // Method to add an admin
    private static void addAdmin(Scanner scanner) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Admin Name: ");
        String aname = scanner.nextLine();

        Admin admin = new Admin(aname);
        adminDAO.create(admin);
    }

    // Method to update an admin
    private static void updateAdmin(Scanner scanner) {
        System.out.print("Enter Admin ID to update: ");
        int aid = scanner.nextInt();
        Admin admin = adminDAO.read(aid);

        if (admin != null) {
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new Admin Name: ");
            admin.setAname(scanner.nextLine());

            adminDAO.update(admin);
            System.out.println("Admin updated successfully.");
        } else {
            System.out.println("Admin not found.");
        }
    }

    // Method to delete an admin
    private static void deleteAdmin(Scanner scanner) {
        System.out.print("Enter Admin ID to delete: ");
        int aid = scanner.nextInt();
        adminDAO.delete(aid);
        System.out.println("Admin deleted successfully.");
    }

    // Method to view all admins
    private static void viewAllAdmins() {
        List<Admin> admins = adminDAO.getAllAdmins();
        if (admins.isEmpty()) {
            System.out.println("No admins found.");
        } else {
            System.out.println("=== List of Admins ===");
            for (Admin admin : admins) {
                System.out.println("ID: " + admin.getAid() + ", Name: " + admin.getAname());
            }
        }
    }
}
