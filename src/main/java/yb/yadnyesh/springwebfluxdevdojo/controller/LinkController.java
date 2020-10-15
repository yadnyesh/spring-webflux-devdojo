package yb.yadnyesh.springwebfluxdevdojo.controller;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import yb.yadnyesh.springwebfluxdevdojo.service.LinkService;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/link")
    Mono<Object> create(@RequestBody CreateLinkRequest request) {
        return linkService.shortenLink(request.getLink())
                .map(CreateLinkRequest::new);
        //return Mono.just(new CreateLinkResponse("http://localhost:8080/abcd1234"));
    }

    @GetMapping("/{key}")
    Mono<ResponseEntity<Object>> getOriginalLink(@PathVariable("key") String key) {
        return linkService.getOriginalLink(key)
                .map(link -> ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                .header("Location", link.getOriginalLink())
                .build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
