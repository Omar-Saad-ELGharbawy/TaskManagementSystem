package omar.HyperCell.intern.task2.model.dto;

import omar.HyperCell.intern.task2.model.db.enums.Priority;
import omar.HyperCell.intern.task2.model.db.enums.Status;

import java.time.LocalDate;

public record TaskDto(
        String title,
        String description,
        Status status,
        Priority priority,
        LocalDate dueDate,
        Long assignedTo,
        Long projectId
) {}