package kr.makeappsgreat.testvalidationmessage;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class SimpleController {

    private final MessageSource messageSource;

    @GetMapping("/")
    public String form(@ModelAttribute Post post) {
        System.out.println(">> 200 " + messageSource.getClass().getName());
        if (messageSource.getClass().isAssignableFrom(ResourceBundleMessageSource.class))
            ((ResourceBundleMessageSource) messageSource).getBasenameSet().forEach(s -> System.out.println(">> 210 " + s));

        return "index.html";
    }

    @PostMapping("/")
    public String formSubmit(@ModelAttribute @Validated Post post, BindingResult bindingResult,
                             RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) return "index.html";

        attributes.addFlashAttribute("post", post);
        return "redirect:/post";
    }

    @GetMapping("/post")
    public String post(@ModelAttribute Post post) {
        return "post.html";
    }
}
