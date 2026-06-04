package projeto.infonews.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.infonews.dto.UsuariosRequestDTO;
import projeto.infonews.dto.UsuariosResponseDTO;
import projeto.infonews.service.UsuariosService;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService service;

    @PostMapping
    public ResponseEntity<UsuariosResponseDTO> criar(@RequestBody @Valid UsuariosRequestDTO dados) {
        return ResponseEntity.ok(service.registrarUsuario(dados));
    }

    @GetMapping
    public ResponseEntity<List<UsuariosResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuariosResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuariosRequestDTO dados) {
        return ResponseEntity.ok(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuariosResponseDTO> login(@RequestBody @Valid UsuariosRequestDTO dados) {
        return ResponseEntity.ok(service.login(dados));
    }
}