package projeto.infonews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.infonews.dto.NewsSourcesResponseDTO;
import projeto.infonews.service.SourcesService;

@RestController
@RequestMapping("/sources")
public class SourcesController {

    @Autowired
    private SourcesService sourcesService;

    @GetMapping
    public ResponseEntity<NewsSourcesResponseDTO> listar(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String country) {

        return ResponseEntity.ok(sourcesService.buscarFontes(language, category, country));
    }
}
