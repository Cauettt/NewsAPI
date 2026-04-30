package projeto.infonews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.infonews.dto.UsuariosRequestDTO;
import projeto.infonews.dto.UsuariosResponseDTO;
import projeto.infonews.entity.UsuariosEntity;
import projeto.infonews.repository.UsuariosRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository repository;

    public UsuariosResponseDTO registrarUsuario(UsuariosRequestDTO request) {
        if (!request.senha().equals(request.confirmacaoSenha())) {
            throw new RuntimeException("As senhas não coincidem!");
        }

        if (repository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Este e-mail já está cadastrado.");
        }

        UsuariosEntity novoUsuario = new UsuariosEntity();
        novoUsuario.setEmail(request.email());
        novoUsuario.setSenha(request.senha());

        UsuariosEntity usuarioSalvo = repository.save(novoUsuario);
        return new UsuariosResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getEmail());
    }

    public List<UsuariosResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(u -> new UsuariosResponseDTO(u.getId(), u.getEmail()))
                .collect(Collectors.toList());
    }

    public UsuariosResponseDTO buscarPorId(Long id) {
        UsuariosEntity u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuariosResponseDTO(u.getId(), u.getEmail());
    }

    public UsuariosResponseDTO atualizar(Long id, UsuariosRequestDTO dados) {
        UsuariosEntity usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!dados.senha().equals(dados.confirmacaoSenha())) {
            throw new RuntimeException("As senhas não coincidem!");
        }

        usuario.setEmail(dados.email());
        usuario.setSenha(dados.senha());

        UsuariosEntity salvo = repository.save(usuario);
        return new UsuariosResponseDTO(salvo.getId(), salvo.getEmail());
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para exclusão");
        }
        repository.deleteById(id);
    }
}