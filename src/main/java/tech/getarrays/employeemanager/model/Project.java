package tech.getarrays.employeemanager.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    // Getters and Setters
}

