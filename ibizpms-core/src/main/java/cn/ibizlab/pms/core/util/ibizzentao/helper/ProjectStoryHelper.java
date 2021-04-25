package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.zentao.domain.ProjectStory;
import cn.ibizlab.pms.core.zentao.mapper.ProjectStoryMapper;
import org.springframework.stereotype.Component;

/**
 * @author chenxiang
 */
@Component
public class ProjectStoryHelper extends ZTBaseHelper<ProjectStoryMapper, ProjectStory> {
    @Override
    public boolean hasId() {
        return false;
    }

    @Override
    public boolean hasDeleted() {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return super.delete(key);
    }
}
