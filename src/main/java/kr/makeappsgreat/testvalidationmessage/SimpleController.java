package kr.makeappsgreat.testvalidationmessage;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SimpleController {

    @GetMapping("/")
    public String form(@ModelAttribute Post post) {
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
