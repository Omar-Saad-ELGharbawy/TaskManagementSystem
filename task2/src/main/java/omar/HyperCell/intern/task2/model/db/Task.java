package omar.HyperCell.intern.task2.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import omar.HyperCell.intern.task2.model.db.converter.PriorityConverter;
import omar.HyperCell.intern.task2.model.db.converter.StatusConverter;
import omar.HyperCell.intern.task2.model.db.enums.Priority;
import omar.HyperCell.intern.task2.model.db.enums.Status;
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

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

//    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    private Status status;

//    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    @Convert(converter = PriorityConverter.class)
    private Priority priority;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @JoinColumn(name = "assigned_to")
    @ManyToOne private User assignedTo;
    @JoinColumn(name = "project_id")
    @ManyToOne private Project project;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
