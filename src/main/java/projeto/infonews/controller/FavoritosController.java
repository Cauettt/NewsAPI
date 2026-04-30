package projeto.infonews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.infonews.dto.FavoritosDTO;
import projeto.infonews.service.FavoritosService;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    @Autowired
    private FavoritosService service;

    @PostMapping
    public ResponseEntity<FavoritosDTO> adicionar(@RequestBody FavoritosDTO favorito, @RequestHeader Long usuarioId) {

        return ResponseEntity.ok(service.salvarFavorito(favorito, usuarioId));
    }

    @GetMapping
    public ResponseEntity<List<FavoritosDTO>> listar(@RequestHeader Long usuarioId) {

        return ResponseEntity.ok(service.listarFavoritos(usuarioId));
    }
}