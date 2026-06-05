package projeto.infonews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.infonews.dto.MonitoramentoDTO;
import projeto.infonews.dto.MonitoramentoFeedItemDTO;
import projeto.infonews.service.MonitoramentoService;

import java.util.List;

@RestController
@RequestMapping("/monitoramento")
public class MonitoramentoController {

    @Autowired
    private MonitoramentoService monitoramentoService;

    @PostMapping
    public ResponseEntity<MonitoramentoDTO> adicionar(
            @RequestBody MonitoramentoDTO dto,
            @RequestHeader Long usuarioId) {

        return ResponseEntity.ok(monitoramentoService.adicionar(dto, usuarioId));
    }

    @GetMapping
    public ResponseEntity<List<MonitoramentoDTO>> listar(@RequestHeader Long usuarioId) {
        return ResponseEntity.ok(monitoramentoService.listar(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        monitoramentoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/feed")
    public ResponseEntity<List<MonitoramentoFeedItemDTO>> feed(@RequestHeader Long usuarioId) {
        return ResponseEntity.ok(monitoramentoService.buscarFeed(usuarioId));
    }
}
