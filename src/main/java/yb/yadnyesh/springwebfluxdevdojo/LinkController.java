package yb.yadnyesh.springwebfluxdevdojo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LinkController {

    @PostMapping("/link")
    Mono<CreateLinkResponse> create(@RequestBody CreateLinkRequest request) {
        return Mono.just(new CreateLinkResponse("http://localhost:8080/abcd1234"));
    }

    static class CreateLinkRequest {
        private String link;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    static private class CreateLinkResponse {
        private String shortenedLink;

        public CreateLinkResponse(String shortenedLink) {
            this.shortenedLink = shortenedLink;
        }

        public String getShortenedLink() {
            return shortenedLink;
        }

        public void setShortenedLink(String shortenedLink) {
            this.shortenedLink = shortenedLink;
        }
    }
}
