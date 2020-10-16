package yb.yadnyesh.springwebfluxdevdojo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class Anime {
    @Id
    private Integer id;

    @NotNull
    @NotEmpty(message = "Name of the Anime cannot be empty")
    private String  firstname;
}
