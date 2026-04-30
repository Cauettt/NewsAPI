package projeto.infonews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.infonews.entity.HistoricoEntity;
import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Long> {

    List<HistoricoEntity> findByUsuarioIdOrderByDataConsultaDesc(Long usuarioId);

}