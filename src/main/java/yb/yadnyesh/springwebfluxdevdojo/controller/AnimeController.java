package yb.yadnyesh.springwebfluxdevdojo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import yb.yadnyesh.springwebfluxdevdojo.domain.Anime;
import yb.yadnyesh.springwebfluxdevdojo.repository.AnimeRepository;

@RestController
@RequestMapping("animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeRepository animeRepository;

    @GetMapping
    public Flux<Anime> animeFlux() {
        return animeRepository.findAll();
    }
}

