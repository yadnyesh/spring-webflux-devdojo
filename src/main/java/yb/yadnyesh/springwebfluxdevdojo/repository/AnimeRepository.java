package yb.yadnyesh.springwebfluxdevdojo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import yb.yadnyesh.springwebfluxdevdojo.domain.Anime;

public interface AnimeRepository extends ReactiveCrudRepository<Anime, Integer> {
}
