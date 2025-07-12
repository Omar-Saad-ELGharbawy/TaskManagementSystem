package omar.HyperCell.intern.task2.repositry;

import omar.HyperCell.intern.task2.enums.Priority;
import omar.HyperCell.intern.task2.enums.Status;
import omar.HyperCell.intern.task2.model.db.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {}


//public interface TaskRepository extends JpaRepository<Task,Long> {
//    List<Task> findByProjectId(Long projectId);
//    List<Task> findByAssignedToId(Long userId);
//    List<Task> findByStatus(Status status);
//    List<Task> findByPriority(Priority priority);
//
//}
