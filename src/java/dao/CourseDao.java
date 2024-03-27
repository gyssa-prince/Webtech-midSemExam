package dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

/**
 *
 * @author Gyssagara
 */
public class CourseDao {
    public long createCourse(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(course);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }
    
    public void updateCourse(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCourse(long courseId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            if (course != null) {
                session.delete(course);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Optional<Course> findCourseById(long courseId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Course course = session.get(Course.class, courseId);
            return Optional.ofNullable(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public List<Course> getAllCourses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Course> courses = session.createQuery("FROM Course", Course.class).list();
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
