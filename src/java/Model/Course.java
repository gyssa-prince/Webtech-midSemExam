package Model;

import javax.persistence.*;

/**
 *
 * @author Gyssagara
 */

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    
    @Column(name = "course_name")
    private String courseName;
    
    @OneToOne
    @JoinColumn(name ="semester_id")
    private Semester semester;
    
    @OneToOne
    @JoinColumn(name = "department_id")
    private Academic department;
    
    @OneToOne(mappedBy = "course")
    private CourseDef courseDefinition;
    
    @OneToOne(mappedBy = "course")
    private Teacher teacher;

    public Course() {
    }

    public Course(Long id, String courseName, Semester semester, Academic department) {
        this.id = id;
        this.courseName = courseName;
        this.semester = semester;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Academic getDepartment() {
        return department;
    }

    public void setDepartment(Academic department) {
        this.department = department;
    }

    public CourseDef getCourseDefinition() {
        return courseDefinition;
    }

    public void setCourseDefinition(CourseDef courseDefinition) {
        this.courseDefinition = courseDefinition;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
