package projeto.infonews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.infonews.entity.FavoritosEntity;
import java.util.List;

@Repository
public interface FavoritosRepository extends JpaRepository<FavoritosEntity, Long> {
    List<FavoritosEntity> findByUsuarioId(Long usuarioId);
}