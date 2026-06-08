package projeto.infonews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import projeto.infonews.entity.HistoricoEntity;
import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Long> {

    List<HistoricoEntity> findByUsuarioIdOrderByDataConsultaDesc(Long usuarioId);

    @Transactional
    @Modifying
    @Query("DELETE FROM HistoricoEntity h WHERE h.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);

}