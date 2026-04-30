package projeto.infonews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.infonews.entity.UsuariosEntity;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long> {
    Optional<UsuariosEntity> findByEmail(String email);
}