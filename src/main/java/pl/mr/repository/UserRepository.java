package pl.mr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mr.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
