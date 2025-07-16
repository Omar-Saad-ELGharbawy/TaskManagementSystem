package omar.HyperCell.intern.task2.controller;

import lombok.RequiredArgsConstructor;
import omar.HyperCell.intern.task2.exception.AppException;
import omar.HyperCell.intern.task2.exception.ValidationException;
import omar.HyperCell.intern.task2.model.db.User;
import omar.HyperCell.intern.task2.model.dto.UserDto;
import omar.HyperCell.intern.task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws AppException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto dto) throws AppException {
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto dto) throws AppException {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws ValidationException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
