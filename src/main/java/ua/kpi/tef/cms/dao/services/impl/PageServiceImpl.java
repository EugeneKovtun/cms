package ua.kpi.tef.cms.dao.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.kpi.tef.cms.dao.dto.PageDto;
import ua.kpi.tef.cms.dao.repository.PageRepository;
import ua.kpi.tef.cms.dao.services.PageService;
import ua.kpi.tef.cms.exception.PageNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component
public class PageServiceImpl implements PageService {
    private final PageRepository repository;

    @Autowired
    public PageServiceImpl(PageRepository repository) {
        this.repository = repository;
    }

    @Override
    public PageDto getPageById(String id) {
        return repository.findById(id).orElseThrow(
                PageNotFoundException::new);
    }

    @Override
    public List<PageDto> getChildElements(PageDto parentPage) {
        List<PageDto> childElements = repository.findAllByParentPage(parentPage);
        childElements.sort(parentPage);
        return childElements;
    }

    @Override
    public void deletePageById(String id) {
        repository.deleteAll(getChildTree(getPageById(id)));
        repository.deleteById(id);
    }

    @Override
    public PageDto savePage(PageDto page) {
        if (page.getAliasOf() != null) {
            PageDto aliasOf = page.getAliasOf();
            if ("".equals(page.getIntro())) {
                page.setIntro(aliasOf.getIntro());
            }
            if ("".equals(page.getIntroUa())) {
                page.setIntroUa(aliasOf.getIntroUa());
            }
            if ("".equals(page.getLinkToImage())) {
                page.setLinkToImage(aliasOf.getLinkToImage());
            }
            if ("".equals(page.getLinkToImageUa())) {
                page.setLinkToImageUa(aliasOf.getLinkToImageUa());
            }
        }
        return repository.save(page);
    }

    private List<PageDto> getChildTree(PageDto pageDto) {
        List<PageDto> result = new ArrayList<>();
        List<PageDto> temp = getChildElements(pageDto);
        result.addAll(temp);
        for (PageDto dto : temp) {
            result.addAll(getChildTree(dto));
        }
        return result;
    }


}
