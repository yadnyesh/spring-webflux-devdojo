package yb.yadnyesh.springwebfluxdevdojo.service;

import org.junit.Test;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

public class LinkServiceTest {

    private LinkService linkService = new LinkService("http://some-domian.com/");

    @Test
    public void shortensLink() {
        StepVerifier.create(linkService.shortenLink("https://spring.io"))
                .expectNextMatches(result -> result != null && result.length() > 0
                                    && result.startsWith("http://some-domian.com/"))
                .expectComplete()
                .verify();
    }

}