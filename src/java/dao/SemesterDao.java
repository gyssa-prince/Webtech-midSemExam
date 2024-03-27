package dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

/**
 *
 * @author Gyssagara
 */
public class SemesterDao {
    public long createSemester(Semester semester) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(semester);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }
    
    public void updateSemester(Semester semester) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(semester);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteSemester(long semesterId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Semester semester = session.get(Semester.class, semesterId);
            if (semester != null) {
                session.delete(semester);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Optional<Semester> findSemesterById(long semesterId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Semester semester = session.get(Semester.class, semesterId);
            return Optional.ofNullable(semester);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public List<Semester> getAllSemesters() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Semester> semesters = session.createQuery("FROM Semester", Semester.class).list();
            return semesters;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
