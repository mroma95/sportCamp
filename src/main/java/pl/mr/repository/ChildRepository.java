package pl.mr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mr.model.Child;

import java.util.List;
@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {
    Child findChildByPesel(String pesel);
}
