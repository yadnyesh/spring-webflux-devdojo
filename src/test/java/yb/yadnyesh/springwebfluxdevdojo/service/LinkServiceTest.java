package yb.yadnyesh.springwebfluxdevdojo.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import yb.yadnyesh.springwebfluxdevdojo.domain.Link;
import yb.yadnyesh.springwebfluxdevdojo.repository.LinkRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class LinkServiceTest {

    private LinkRepository linkRepository = mock(LinkRepository.class);

    private LinkService linkService = new LinkService("http://some-domian.com/", linkRepository);

    @Before
    public void setup() {
        Mockito
                .when(linkRepository.save(any()))
                .thenAnswer(new Answer<Mono<Link>>(){
            public Mono<Link> answer(InvocationOnMock invocationOnMock) throws Throwable {
                Link link = (Link) invocationOnMock.getArguments()[0];
                return Mono.just(link);
            }
        });
    }

    @Test
    public void shortensLink() {
        StepVerifier.create(linkService.shortenLink("https://spring.io"))
                .expectNextMatches(result -> result != null && result.length() > 0
                                    && result.startsWith("http://some-domian.com/"))
                .expectComplete()
                .verify();
    }

}