package tech.getarrays.employeemanager.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Project> projects;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees; // Référence au HEAD_DEPARTMENT

    public Department() {}



    public Long getId() {
        return id;
    }

        // Constructeur avec un argument String
    public Department(String name) {
        this.name = name;
    }

        // Getter et Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
