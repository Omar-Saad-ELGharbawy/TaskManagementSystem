package omar.HyperCell.intern.task2.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import omar.HyperCell.intern.task2.exception.AppException;
import omar.HyperCell.intern.task2.exception.NoContentException;
import omar.HyperCell.intern.task2.exception.ValidationException;
import omar.HyperCell.intern.task2.model.db.Task;
import omar.HyperCell.intern.task2.model.db.enums.Priority;
import omar.HyperCell.intern.task2.model.db.enums.Status;
import omar.HyperCell.intern.task2.model.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import omar.HyperCell.intern.task2.repositry.*;
import omar.HyperCell.intern.task2.exception.ErrorCode;


@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository; // To set the project on a task
    private final UserRepository userRepository; // To set the assigned user

    // Method to create a new task
    public Task createTask(TaskDto dto) throws AppException {
        validateCreateOrUpdate(dto);

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

    public Task updateTask(Long id, TaskDto dto) throws AppException  {
        validateCreateOrUpdate(dto);

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

    private void validateCreateOrUpdate(TaskDto dto) throws NoContentException, ValidationException {
        if (dto == null) {
            throw new NoContentException(ErrorCode.EMPTY_TASK_LIST);
        }
        if (dto.title() == null || dto.title().isEmpty()) {
            throw new ValidationException(ErrorCode.INVALID_TASK_TITLE);
        }
        else if (dto.description() == null || dto.description().isEmpty()) {
            throw new ValidationException(ErrorCode.INVALID_TASK_DESCRIPTION);
        }
        else if (dto.status() == null) {
            throw new ValidationException(ErrorCode.INVALID_TASK_STATUS);
        }
        else if (dto.priority() == null) {
            throw new ValidationException(ErrorCode.INVALID_TASK_PRIORITY);
        }
        else if (dto.dueDate() == null ) {
            throw new ValidationException(ErrorCode.INVALID_TASK_DUE_DATE);
        }
        else if (dto.assignedTo() == null ) {
            throw new ValidationException(ErrorCode.INVALID_TASK_ASSIGNED_USER);
        }
        else if (dto.projectId() == null) {
            throw new ValidationException(ErrorCode.INVALID_TASK_PROJECT);
        }
    }

    public Task getTaskById(Long id) throws AppException{
        if (id == null) {
            throw new ValidationException(ErrorCode.INVALID_TASK_ID);
        }
        else if(id <= 0 ||  id > taskRepository.count() ) {
            throw new ValidationException(ErrorCode.OUT_OF_RANGE_TASK_ID);
        }
        return taskRepository.findById(id).orElseThrow(() -> new ValidationException(ErrorCode.OUT_OF_RANGE_TASK_ID));
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


    public void deleteTask(Long id) throws ValidationException {
        if (id == null) {
            throw new ValidationException(ErrorCode.INVALID_TASK_ID);
        }
        else if(id <= 0 ||  id > taskRepository.count() ) {
            throw new ValidationException(ErrorCode.OUT_OF_RANGE_TASK_ID);
        }
        taskRepository.deleteById(id);
    }

}
