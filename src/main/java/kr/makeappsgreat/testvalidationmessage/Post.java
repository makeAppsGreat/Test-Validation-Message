package kr.makeappsgreat.testvalidationmessage;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class Post {

    @NotBlank
    private String title;

    @Size(min = 10)
    private String content;

    @NotBlank(message = "{test-validation-message}")
    private String hidden;
}
