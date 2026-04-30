package projeto.infonews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.infonews.dto.HistoricoDTO;
import projeto.infonews.entity.HistoricoEntity;
import projeto.infonews.repository.HistoricoRepository;
import projeto.infonews.repository.UsuariosRepository;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public void registrarBusca(String termo, Long usuarioId) {
        HistoricoEntity entity = new HistoricoEntity();
        entity.setTermoBusca(termo);
        entity.setUsuario(usuariosRepository.getReferenceById(usuarioId));

        historicoRepository.save(entity);
    }

    public List<HistoricoDTO> obterHistorico(Long usuarioId) {
        return historicoRepository.findByUsuarioIdOrderByDataConsultaDesc(usuarioId).stream()
                .map(h -> new HistoricoDTO(h.getId(), h.getTermoBusca(), h.getDataConsulta()))
                .toList();
    }
}