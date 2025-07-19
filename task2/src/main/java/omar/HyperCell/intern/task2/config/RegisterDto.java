package omar.HyperCell.intern.task2.config;

import omar.HyperCell.intern.task2.model.db.enums.Role;

public record RegisterDto(String firstname, String lastname, String username, String password, Role role) {
}
