package projeto.infonews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.infonews.dto.HistoricoDTO;
import projeto.infonews.service.HistoricoService;
import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    @GetMapping
    public ResponseEntity<List<HistoricoDTO>> verHistorico(@RequestHeader Long usuarioId) {

        return ResponseEntity.ok(service.obterHistorico(usuarioId));
    }
}