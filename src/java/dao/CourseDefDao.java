package dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

/**
 *
 * @author Gyssagara
 */
public class CourseDefDao {
    public long createCourseDefinition(CourseDef courseDefinition) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(courseDefinition);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }
    
    public void updateCourseDefinition(CourseDef courseDefinition) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(courseDefinition);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCourseDefinition(long courseId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CourseDef courseDefinition = session.get(CourseDef.class, courseId);
            if (courseDefinition != null) {
                session.delete(courseDefinition);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Optional<CourseDef> findCourseDefinitionById(long courseId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CourseDef courseDefinition = session.get(CourseDef.class, courseId);
            return Optional.ofNullable(courseDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public List<CourseDef> getAllCourseDefinitions() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<CourseDef> courseDefinitions = session.createQuery("FROM CourseDef", CourseDef.class).list();
            return courseDefinitions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
