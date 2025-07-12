package omar.HyperCell.intern.task2.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omar.HyperCell.intern.task2.enums.Priority;
import omar.HyperCell.intern.task2.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING) private Status status;
    @Enumerated(EnumType.STRING) private Priority priority;
    private LocalDate dueDate;

    @JoinColumn(name = "assigned_to")
    @ManyToOne private User assignedTo;
    @JoinColumn(name = "project_id")
    @ManyToOne private Project project;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    public Task() {
//
//    }
}
