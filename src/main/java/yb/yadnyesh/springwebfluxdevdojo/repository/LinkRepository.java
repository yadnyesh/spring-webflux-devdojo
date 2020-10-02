package yb.yadnyesh.springwebfluxdevdojo.repository;

import reactor.core.publisher.Mono;
import yb.yadnyesh.springwebfluxdevdojo.domain.Link;

public interface LinkRepository {
    Mono<Link> save(Link link);
}
