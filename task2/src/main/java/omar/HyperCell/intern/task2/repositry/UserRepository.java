package omar.HyperCell.intern.task2.repositry;

import omar.HyperCell.intern.task2.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
