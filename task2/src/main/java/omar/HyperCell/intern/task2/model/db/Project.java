package omar.HyperCell.intern.task2.model.db;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="description", columnDefinition = "TEXT")
    private String description;

}
