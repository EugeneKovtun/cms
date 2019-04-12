package ua.kpi.tef.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kpi.tef.cms.dao.dto.PageDto;
import ua.kpi.tef.cms.dao.services.PageService;
import ua.kpi.tef.cms.entities.ContainerType;
import ua.kpi.tef.cms.entities.OrderType;
import ua.kpi.tef.cms.model.Renderer;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/page")
public class AdminController {
    private final PageService pageService;
    private final Renderer renderer;

    @Autowired
    public AdminController(PageService pageService, Renderer renderer) {
        this.pageService = pageService;
        this.renderer = renderer;
    }

    @GetMapping
    String getIndex(@RequestParam(required = false, defaultValue = "main") String currentNodeId, Model model) {
        return renderer.renderAdminPage(model, currentNodeId);

    }

    @PostMapping
    String createPage(@RequestParam String id,
                      String title, String titleUa,
                      String caption, String captionUa,
                      String content, String contentUa,
                      String intro, String introUa,
                      Integer orderNum, OrderType orderType,
                      ContainerType containerType,
                      String linkToImage, String linkToImageUa,
                      String aliasOfId,
                      @RequestParam(required = false, defaultValue = "main")
                              String parentPageId) {

        PageDto page = new PageDto().setDateTime(LocalDateTime.now()).setId(id)
                .setTitle(title).setTitleUa(titleUa)
                .setCaption(caption).setCaptionUa(captionUa)
                .setContent(content).setContentUa(contentUa)
                .setIntro(intro).setIntroUa(introUa)
                .setOrderNum(orderNum).setOrderType(orderType)
                .setContainerType(containerType)
                .setLinkToImage(linkToImage).setLinkToImageUa(linkToImageUa)
                .setParentPage(pageService.getPageById(parentPageId));
        if ("".equals(aliasOfId)) {
            pageService.savePage(page);
        } else {
            page.setAliasOf(pageService.getPageById(aliasOfId));

            pageService.savePage(page);
        }
        return "redirect:/page?message=pageCreated&currentNodeId=" + parentPageId;
    }

    @PutMapping("/{id}")
    String updatePage(@PathVariable String id,
                      String title, String titleUa,
                      String caption, String captionUa,
                      String content, String contentUa,
                      String intro, String introUa,
                      Integer orderNum, OrderType orderType,
                      ContainerType containerType,
                      String aliasOfId,
                      String linkToImage, String linkToImageUa,
                      String parentPageId) {
        PageDto page = new PageDto().setDateTime(LocalDateTime.now()).setId(id)
                .setTitle(title).setTitleUa(titleUa)
                .setCaption(caption).setCaptionUa(captionUa)
                .setContent(content).setContentUa(contentUa)
                .setIntro(intro).setIntroUa(introUa)
                .setOrderNum(orderNum).setOrderType(orderType)
                .setContainerType(containerType)
                .setLinkToImage(linkToImage).setLinkToImageUa(linkToImageUa);

        if ("".equals(parentPageId)) {
            parentPageId = null;
            page.setParentPage(null);

        } else {
            page.setParentPage(pageService.getPageById(parentPageId));
        }

        if ("".equals(aliasOfId)) {
            pageService.savePage(page);
        } else {
            page.setAliasOf(pageService.getPageById(aliasOfId));

            pageService.savePage(page);
        }

        return "redirect:/page?message=pageUpdated&currentNodeId=" + id;

    }

    @DeleteMapping("/{id}")
    String deletePage(@PathVariable String id) {
        pageService.deletePageById(id);
        return "redirect:/page?message=pageRemoved";
    }

    @GetMapping("/create")
    String create(@RequestParam(required = false, defaultValue = "main") String parentPageId, Model model) {
        model.addAttribute("parentPageId", parentPageId);
        return "creationForm";
    }

    @GetMapping({"/{id}/edit"})
    String edit(@PathVariable(required = false) String id, Model model) {
        return renderer.renderEditForm(model, id);
    }
}
