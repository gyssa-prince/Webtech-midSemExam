package dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

/**
 *
 * @author Gyssagara
 */
public class StudentRegistrationDao {
    public long createStudentRegistration(StudentRegistration student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(student);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }

    public void updateStudentRegistration(StudentRegistration student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentRegistration(long registrationId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            StudentRegistration student = session.get(StudentRegistration.class, registrationId);
            if (student != null) {
                session.delete(student);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<StudentRegistration> findStudentRegistrationById(long registrationId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            StudentRegistration student = session.get(StudentRegistration.class, registrationId);
            return Optional.ofNullable(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<StudentRegistration> getAllStudentRegistrations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<StudentRegistration> students = session.createQuery("FROM StudentRegistration", StudentRegistration.class).list();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
