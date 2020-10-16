package yb.yadnyesh.springwebfluxdevdojo.repository;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import yb.yadnyesh.springwebfluxdevdojo.domain.Link;

@Repository
public class RedisLinkRepository implements LinkRepository {

    private final ReactiveRedisOperations reactiveRedisOperations;

    public RedisLinkRepository(ReactiveRedisOperations reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }

    @Override
    public Mono<Link> save(Link link) {
        return reactiveRedisOperations.opsForValue()
                .set(link.getKey(), link.getOriginalLink())
                .map(__ -> link);
    }

    @Override
    public Mono<Link> findByKey(String key) {
        return reactiveRedisOperations.opsForValue()
                .get(key)
                .map(result -> new Link(result.toString(), key));
    }
}
