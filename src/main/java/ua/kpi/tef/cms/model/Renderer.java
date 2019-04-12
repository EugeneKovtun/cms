package ua.kpi.tef.cms.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ua.kpi.tef.cms.dao.dto.PageDto;
import ua.kpi.tef.cms.dao.services.PageService;
import ua.kpi.tef.cms.entities.Page;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Renderer {
    private final PageService pageService;

    @Autowired
    public Renderer(PageService pageService) {
        this.pageService = pageService;
    }


    public String renderEditForm(Model model, String pageId) {
        PageDto page = pageService.getPageById(pageId);
        model.addAttribute("page", page);
        return "updateForm";
    }

    public String renderAdminPage(Model model, String currentNodeId) {
        PageDto currentNode = pageService.getPageById(currentNodeId);
        model.addAttribute("currentNode", currentNode);
        model.addAttribute("childNodes", pageService.getChildElements(currentNode));
        return "admin";
    }

    public String renderPlainPage(Model model, String id, String language) {
        PageDto pageDto = pageService.getPageById(id);
        if (pageDto.getAliasOf() != null) {
            return "redirect:/" + pageDto.getAliasOf().getId() + (language == null ? "" : ("/" + language));
        }
        Page page = pageDto.resolvePage(language);
        model.addAttribute("page", page);
        if (page.getContainerType() != null) {
            List<Page> childPages = pageService.getChildElements(pageDto).stream().map(x -> x.resolvePage(language)).collect(Collectors.toList());
            model.addAttribute("childPages", childPages);
            model.addAttribute("language", language);
        }
        return "main";
    }

    private List<PageDto> getSortedChildPages(PageDto parentPageDto) {
        return pageService.getChildElements(parentPageDto).stream().sorted().collect(Collectors.toList());
    }
}
