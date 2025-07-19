package omar.HyperCell.intern.task2.repositry;

import omar.HyperCell.intern.task2.model.db.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
