package projeto.infonews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.infonews.dto.MonitoramentoDTO;
import projeto.infonews.dto.MonitoramentoFeedItemDTO;
import projeto.infonews.dto.NewsApiResponseDTO;
import projeto.infonews.entity.MonitoramentoEntity;
import projeto.infonews.entity.TipoMonitoramento;
import projeto.infonews.repository.MonitoramentoRepository;
import projeto.infonews.repository.UsuariosRepository;

import java.util.List;

@Service
public class MonitoramentoService {

    @Autowired
    private MonitoramentoRepository monitoramentoRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private NewsService newsService;

    public MonitoramentoDTO adicionar(MonitoramentoDTO dto, Long usuarioId) {
        MonitoramentoEntity entity = new MonitoramentoEntity();
        entity.setNome(dto.nome());
        entity.setValor(dto.valor());
        entity.setTipo(TipoMonitoramento.valueOf(dto.tipo()));
        entity.setUsuario(usuariosRepository.getReferenceById(usuarioId));
        return toDTO(monitoramentoRepository.save(entity));
    }

    public List<MonitoramentoDTO> listar(Long usuarioId) {
        return monitoramentoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDTO)
                .toList();
    }

    public void remover(Long id) {
        monitoramentoRepository.deleteById(id);
    }

    public List<MonitoramentoFeedItemDTO> buscarFeed(Long usuarioId) {
        return monitoramentoRepository.findByUsuarioId(usuarioId).stream()
                .map(m -> {
                    String fontes = m.getTipo() == TipoMonitoramento.FONTE ? m.getValor() : null;
                    String query  = m.getTipo() == TipoMonitoramento.TEMA  ? m.getValor() : null;
                    NewsApiResponseDTO resp = newsService.buscarNoticias(query, fontes, null);
                    List<NewsApiResponseDTO.NewsArticleDTO> artigos = resp != null ? resp.articles() : List.of();
                    return new MonitoramentoFeedItemDTO(m.getId(), m.getNome(), m.getTipo().name(), artigos);
                })
                .toList();
    }

    private MonitoramentoDTO toDTO(MonitoramentoEntity e) {
        return new MonitoramentoDTO(e.getId(), e.getNome(), e.getValor(), e.getTipo().name());
    }
}
