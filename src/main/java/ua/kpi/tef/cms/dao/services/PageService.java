package ua.kpi.tef.cms.dao.services;

import org.springframework.stereotype.Service;
import ua.kpi.tef.cms.dao.dto.PageDto;
import ua.kpi.tef.cms.entities.Page;

import java.util.List;
import java.util.Set;

@Service
public interface PageService {
    PageDto getPageById(String id);

    List<PageDto> getChildElements(PageDto parentPage);


    void deletePageById(String id);


    PageDto savePage(PageDto page);
}
