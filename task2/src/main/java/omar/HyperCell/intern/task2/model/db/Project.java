package omar.HyperCell.intern.task2.model.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

}
