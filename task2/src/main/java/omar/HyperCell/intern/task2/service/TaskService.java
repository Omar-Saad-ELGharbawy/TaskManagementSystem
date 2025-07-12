package omar.HyperCell.intern.task2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import omar.HyperCell.intern.task2.enums.*;
import omar.HyperCell.intern.task2.model.db.Task;
import omar.HyperCell.intern.task2.model.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import omar.HyperCell.intern.task2.repositry.*;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository; // To set the project on a task
    private final UserRepository userRepository; // To set the assigned user

    // Method to create a new task
    public Task createTask(TaskDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Task data cannot be null");
        }
        if (dto.projectId() == null) {
            throw new IllegalArgumentException("Project ID is required");
        }

        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());
        task.setAssignedTo(
                dto.assignedTo() != null ?
                        userRepository.findById(dto.assignedTo()).orElse(null)
                        : null
        );
        task.setProject(
                projectRepository.findById(dto.projectId())
                        .orElseThrow(() -> new EntityNotFoundException("Project not found"))
        );
        // The creation and update timestamps are automatically handled by Hibernate annotations
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found with id " + id));
    }

    public Page<Task> getFilteredTasks(Long projectId, Long assignedToId, Status status, Priority priority, Pageable pageable) {
        Specification<Task> spec = (root, query, cb) -> cb.conjunction(); // always true, like 'where 1=1'

        if (projectId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("project").get("id"), projectId));
        }
        if (assignedToId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("assignedTo").get("id"), assignedToId));
        }
        if (status != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }
        if (priority != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("priority"), priority));
        }

        return taskRepository.findAll(spec, pageable);
    }

    public Task updateTask(Long id, TaskDto dto) {
        Task task = getTaskById(id);
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());

        task.setAssignedTo(
                dto.assignedTo() != null ?
                        userRepository.findById(dto.assignedTo()).orElse(null)
                        : null
        );

        task.setProject(
                projectRepository.findById(dto.projectId())
                        .orElseThrow(() -> new EntityNotFoundException("Project not found"))
        );

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
