package yb.yadnyesh.springwebfluxdevdojo.controller;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import yb.yadnyesh.springwebfluxdevdojo.service.LinkService;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/link")
    Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request) {
        return linkService.shortenLink(request.getLink())
                .map(CreateLinkResponse::new);
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

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class CreateLinkRequest {
        private String link;
    }

    @Value
    public static class CreateLinkResponse {
        private String shortenedLink;
    }
}
