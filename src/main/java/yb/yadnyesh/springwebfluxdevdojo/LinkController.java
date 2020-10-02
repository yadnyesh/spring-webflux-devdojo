package yb.yadnyesh.springwebfluxdevdojo;

import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import yb.yadnyesh.springwebfluxdevdojo.service.LinkService;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/link")
    Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request) {
        linkService.shortenLink(request.getLink())
                .map(CreateLinkRequest::new);
        return Mono.just(new CreateLinkResponse("http://localhost:8080/abcd1234"));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateLinkRequest {
        private String link;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateLinkResponse {
        private String shortenedLink;
    }
}
