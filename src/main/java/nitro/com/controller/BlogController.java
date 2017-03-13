package nitro.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import nitro.com.service.URL;
@Controller
public class BlogController {
    @Autowired
    private URL url;
    @RequestMapping(value = "/blog")
    public ModelAndView getBlog(ModelAndView mv) {
        mv.addObject("blogTitle", "Freemarker Template Demo using Spring");
        mv.addObject("message", "Getting started with Freemarker.<br/>Find a Freemarker template demo using Spring.");
        mv.addObject("references", url.getUrlList());
        mv.setViewName("blog-template");
        return mv;
    }
}

