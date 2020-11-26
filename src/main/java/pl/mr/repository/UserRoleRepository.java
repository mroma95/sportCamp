package pl.mr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mr.model.User;
import pl.mr.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    UserRole findByRole(String role);
}
