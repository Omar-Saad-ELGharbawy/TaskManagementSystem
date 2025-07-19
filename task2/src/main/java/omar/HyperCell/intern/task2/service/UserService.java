package omar.HyperCell.intern.task2.service;

import jakarta.persistence.EntityNotFoundException;
import omar.HyperCell.intern.task2.exception.AppException;
import omar.HyperCell.intern.task2.exception.ErrorCode;
import omar.HyperCell.intern.task2.exception.ValidationException;
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

    public User getUserById(Long id) throws AppException {
        if (id == null) {
            throw new ValidationException(ErrorCode.INVALID_USER_ID);
        }
        if ( id <= 0 || id > userRepository.count()) {
            throw new ValidationException(ErrorCode.OUT_OF_RANGE_USER_ID);
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID " + id));
    }

//    public User createUser(UserDto dto) throws AppException {
//        if (dto.name() == null || dto.name().isEmpty()) {
//            throw new ValidationException(ErrorCode.INVALID_USER_NAME);
//        }
//        if (dto.email() == null || dto.email().isEmpty()) {
//            throw new ValidationException(ErrorCode.INVALID_USER_EMAIL);
//        }
//        User user = new User();
//        user.setName(dto.name());
//        user.setEmail(dto.email());
//        return userRepository.save(user);
//    }

//    public User updateUser(Long id, UserDto dto) throws AppException {
//        if (id == null) {
//            throw new ValidationException(ErrorCode.INVALID_USER_ID);
//        }
//        else if ( id <= 0 || id > userRepository.count()) {
//            throw new ValidationException(ErrorCode.OUT_OF_RANGE_USER_ID);
//        }
//
//        if (dto.name() == null || dto.name().isEmpty()) {
//            throw new ValidationException(ErrorCode.INVALID_USER_NAME);
//        }
//        else if (dto.email() == null || dto.email().isEmpty()) {
//            throw new ValidationException(ErrorCode.INVALID_USER_EMAIL);
//        }
//        User user = getUserById(id);
//        user.setName(dto.name());
//        user.setEmail(dto.email());
//        return userRepository.save(user);
//    }

    public void deleteUser(Long id) throws ValidationException {
        if (id == null) {
            throw new ValidationException(ErrorCode.INVALID_USER_ID);
        }
        if ( id <= 0 || id > userRepository.count()) {
            throw new ValidationException(ErrorCode.OUT_OF_RANGE_USER_ID);
        }
        userRepository.deleteById(id);
    }


}
