package projeto.infonews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.infonews.dto.NewsApiResponseDTO;
import projeto.infonews.service.HistoricoService;
import projeto.infonews.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/buscar")
    public ResponseEntity<NewsApiResponseDTO> buscar(
            @RequestParam String q,
            @RequestParam(required = false) String fontes,
            @RequestHeader Long usuarioId) {

        historicoService.registrarBusca(q, usuarioId);

        return ResponseEntity.ok(newsService.buscarNoticias(q, fontes));
    }
}