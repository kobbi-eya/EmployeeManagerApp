package tech.getarrays.employeemanager.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_to", nullable = false)
    private Employee assignedTo; // Peut être un Project Manager ou Developer
    // Getters and Setters
}

