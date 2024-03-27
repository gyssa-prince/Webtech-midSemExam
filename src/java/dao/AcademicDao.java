package dao;

import java.util.List;
import Model.Academic;
import org.hibernate.Session;

/**
 * Data Access Object for Academic entities.
 * This class provides methods to perform CRUD operations on Academic entities.
 * @author Gyssagara
 */
public class AcademicDao {
    
    /**
     * Creates a new Academic unit.
     * @param academicUnit The Academic unit to create.
     * @return The created Academic unit.
     */
    public Academic createAcademicUnit(Academic academicUnit) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.save(academicUnit);
            session.beginTransaction().commit();
            session.close();
            return academicUnit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Updates an existing Academic unit.
     * @param academicUnit The Academic unit to update.
     * @return The updated Academic unit.
     */
    public Academic updateAcademicUnit(Academic academicUnit) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.update(academicUnit);
            session.beginTransaction().commit();
            session.close();
            return academicUnit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Deletes an existing Academic unit.
     * @param academicUnit The Academic unit to delete.
     * @return The deleted Academic unit.
     */
    public Academic deleteAcademicUnit(Academic academicUnit) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.delete(academicUnit);
            session.beginTransaction().commit();
            session.close();
            return academicUnit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Finds an Academic unit by its ID.
     * @param academicUnit The Academic unit to find.
     * @return The found Academic unit.
     */
    public Academic findAcademicUnitById(Academic academicUnit) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Academic theAcademicUnit = (Academic) session.get(Academic.class, academicUnit.getAcademicUnitId());
            session.close();
            return theAcademicUnit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Retrieves all Academic units.
     * @return A list of all Academic units.
     */
    public List<Academic> getAllAcademicUnits() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Academic> academicUnits = session.createQuery("FROM Academic").list();
            session.close();
            return academicUnits;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
