package omar.HyperCell.intern.task2.service;

import jakarta.persistence.EntityNotFoundException;
import omar.HyperCell.intern.task2.model.db.User;
import omar.HyperCell.intern.task2.model.dto.UserDto;
import omar.HyperCell.intern.task2.repositry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID " + id));
    }

    public User createUser(UserDto dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDto dto) {
        User user = getUserById(id);
        user.setName(dto.name());
        user.setEmail(dto.email());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
