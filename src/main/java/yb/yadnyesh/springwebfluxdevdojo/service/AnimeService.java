package yb.yadnyesh.springwebfluxdevdojo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import yb.yadnyesh.springwebfluxdevdojo.domain.Anime;
//import yb.yadnyesh.springwebfluxdevdojo.repository.AnimeRepository;

//@Service
//@Slf4j
//@RequiredArgsConstructor
public class AnimeService {

    //private final AnimeRepository animeRepository;

    public Flux<Anime> findAll() {
        //return animeRepository.findAll();
        return null;
    }
}
