package projeto.infonews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.infonews.entity.MonitoramentoEntity;
import java.util.List;

@Repository
public interface MonitoramentoRepository extends JpaRepository<MonitoramentoEntity, Long> {
    List<MonitoramentoEntity> findByUsuarioId(Long usuarioId);
}
