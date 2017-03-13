package nitro.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {


    @RequestMapping("/{name}")
    public String home(@PathVariable  String name  , Model model){
        model.addAttribute("name", name);
        return "home";

    }
}
