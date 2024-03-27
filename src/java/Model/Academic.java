package Model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Gyssagara
 */
 
@Entity
@Table(name = "academic_unit")
public class Academic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "academic_unit_id")
    private Long academicUnitId;

    @Column(name = "academic_unit_name")
    private String academicUnitName;

    @Enumerated(EnumType.STRING)
    @Column(name = "academic_unit_type")
    private EAcademic academicUnitType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Academic parentId;

    public Academic() {
    }

    public Academic(Long academicUnitId, String academicUnitName, EAcademic academicUnitType, Academic parentId) {
        this.academicUnitId = academicUnitId;
        this.academicUnitName = academicUnitName;
        this.academicUnitType = academicUnitType;
        this.parentId = parentId;
    }

    public Academic(String academicUnitName, EAcademic academicUnitType, Academic parentId) {
        this.academicUnitName = academicUnitName;
        this.academicUnitType = academicUnitType;
        this.parentId = parentId;
    }

    public Long getAcademicUnitId() {
        return academicUnitId;
    }

    public void setAcademicUnitId(Long academicUnitId) {
        this.academicUnitId = academicUnitId;
    }

    public String getAcademicUnitName() {
        return academicUnitName;
    }

    public void setAcademicUnitName(String academicUnitName) {
        this.academicUnitName = academicUnitName;
    }

    public EAcademic getAcademicUnitType() {
        return academicUnitType;
    }

    public void setAcademicUnitType(EAcademic academicUnitType) {
        this.academicUnitType = academicUnitType;
    }

    public Academic getParentId() {
        return parentId;
    }

    public void setParentId(Academic parentId) {
        this.parentId = parentId;
    }
}
