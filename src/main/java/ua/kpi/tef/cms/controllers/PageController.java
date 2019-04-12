package ua.kpi.tef.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.kpi.tef.cms.model.Renderer;

@Controller
public class PageController {
    private final Renderer renderer;

    @Autowired
    public PageController(Renderer renderer) {
        this.renderer = renderer;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/main";
    }

    @GetMapping("/ua")
    public String homeUa() {
        return "redirect:/main/ua";
    }


    @GetMapping({"/{id}", "/{id}/{language}"})
    public String homePage(@PathVariable(required = false) String id, @PathVariable(required = false) String language, Model model) {
        return renderer.renderPlainPage(model, id, language);
    }
}
// TODO: 11.04.19 fix frontend
// TODO: 11.04.19 fix links  localization should be as PathVariable
// TODO: 11.04.19 Change root element and mapping to him