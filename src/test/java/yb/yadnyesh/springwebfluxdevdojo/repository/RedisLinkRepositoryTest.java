package yb.yadnyesh.springwebfluxdevdojo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;
import yb.yadnyesh.springwebfluxdevdojo.domain.Link;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLinkRepositoryTest {

    @Autowired
    private RedisLinkRepository redisLinkRepository;

    @Test
    public void returnSameLinkAsParameters() {
        Link link = new Link("https://spring.io","yadnyesh");
        StepVerifier.create(redisLinkRepository.save(link))
                .expectNext(link)
                .verifyComplete();
    }

    @Test
    public void savesInRedis() {
        Link link = new Link("https://spring.io","yadnyesh");
        StepVerifier.create(redisLinkRepository.save(link)
                            .flatMap(__ -> redisLinkRepository.findByKey(link.getKey())))
                    .expectNext(link)
                    .verifyComplete();

    }
}