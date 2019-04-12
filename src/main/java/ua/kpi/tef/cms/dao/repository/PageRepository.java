package ua.kpi.tef.cms.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.tef.cms.dao.dto.PageDto;

import java.util.List;
import java.util.SortedSet;

@Repository
public interface PageRepository extends CrudRepository<PageDto, String> {
    List<PageDto> findAllByParentPage(PageDto parentPage);

}
