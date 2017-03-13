package nitro.com.controller;

import nitro.com.service.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/")
public class BlogController {

    @Autowired
    private URL url;


    String name = "denis";

    @RequestMapping(method = RequestMethod.GET, value = "/blog")
    public ModelAndView getBlog(ModelAndView model) {
        model.addObject("blogTitle", "Freemarker Template Demo using Spring");
        model.addObject("message", "Getting started with Freemarker.<br/>Find a Freemarker template demo using Spring.");
        model.addObject("references", url.getUrlList());
        model.setViewName("blog-template");
        return model;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home( ModelAndView model) {
        model.addObject("name", name);
        return model;


    }

}

