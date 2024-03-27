package dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gyssagara
 */
public class StudentDao {
    private static final Logger logger = LoggerFactory.getLogger(StudentDao.class);

    public long createStudent(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(student);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            logger.error("Error creating student: {}", e.getMessage());
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }

    public void updateStudent(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error updating student: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteStudent(long studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.delete(student);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error deleting student: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    public Optional<Student> findStudentById(long studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.get(Student.class, studentId);
            return Optional.ofNullable(student);
        } catch (Exception e) {
            logger.error("Error finding student by ID: {}", e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Student> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            return students;
        } catch (HibernateException e) {
            logger.error("Error retrieving students from the database: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
