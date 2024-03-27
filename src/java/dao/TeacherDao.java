package dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

/**
 *
 * @author Gyssagara
 */
public class TeacherDao {
    public long createTeacher(Teacher teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            long id = (long) session.save(teacher);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Indicate failure
    }

    public void updateTeacher(Teacher teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(long teacherId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, teacherId);
            if (teacher != null) {
                session.delete(teacher);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Teacher> findTeacherById(long teacherId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Teacher teacher = session.get(Teacher.class, teacherId);
            return Optional.ofNullable(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Teacher> getAllTeachers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Teacher> teachers = session.createQuery("FROM Teacher", Teacher.class).list();
            return teachers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
