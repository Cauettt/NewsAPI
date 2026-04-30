package projeto.infonews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.infonews.dto.FavoritosDTO;
import projeto.infonews.entity.FavoritosEntity;
import projeto.infonews.repository.FavoritosRepository;
import projeto.infonews.repository.UsuariosRepository;
import java.util.List;

@Service
public class FavoritosService {

    @Autowired
    private FavoritosRepository favoritosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public FavoritosDTO salvarFavorito(FavoritosDTO dto, Long usuarioId) {
        FavoritosEntity entity = new FavoritosEntity();
        entity.setTitulo(dto.titulo());
        entity.setDescricao(dto.descricao());
        entity.setUrl(dto.url());
        entity.setFonteNome(dto.fonteNome());
        entity.setUsuario(usuariosRepository.getReferenceById(usuarioId));

        FavoritosEntity salva = favoritosRepository.save(entity);
        return new FavoritosDTO(salva.getId(), salva.getTitulo(), salva.getDescricao(), salva.getUrl(), salva.getFonteNome());
    }

    public List<FavoritosDTO> listarFavoritos(Long usuarioId) {
        return favoritosRepository.findByUsuarioId(usuarioId).stream()
                .map(f -> new FavoritosDTO(f.getId(), f.getTitulo(), f.getDescricao(), f.getUrl(), f.getFonteNome()))
                .toList();
    }
}