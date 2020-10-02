package yb.yadnyesh.springwebfluxdevdojo.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import yb.yadnyesh.springwebfluxdevdojo.domain.Link;
import yb.yadnyesh.springwebfluxdevdojo.repository.LinkRepository;

@Service
public class LinkService {
    private final String baseUrl;
    private final LinkRepository linkRepository;

    public Mono<String> shortenLink(String originallink) {
        String randomKey = RandomStringUtils.randomAlphabetic(6);
        return linkRepository.save(new Link(originallink,randomKey))
                .map(result -> baseUrl +result.getKey());
    }

    public LinkService(@Value("${app.baseUrl}") String baseUrl, LinkRepository linkRepository) {
        this.baseUrl = baseUrl;
        this.linkRepository = linkRepository;
    }
}
