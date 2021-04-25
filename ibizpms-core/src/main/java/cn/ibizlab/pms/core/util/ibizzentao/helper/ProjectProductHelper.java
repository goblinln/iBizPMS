package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.zentao.domain.ProjectProduct;
import cn.ibizlab.pms.core.zentao.mapper.ProjectProductMapper;
import org.springframework.stereotype.Component;

/**
 * @author chenxiang
 */
@Component
public class ProjectProductHelper extends ZTBaseHelper<ProjectProductMapper, ProjectProduct> {

    @Override
    public boolean hasId() {
        return false ;
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
