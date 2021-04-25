package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.zentao.domain.SuiteCase;
import cn.ibizlab.pms.core.zentao.mapper.SuiteCaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class SuiteCaseHelper extends ZTBaseHelper<SuiteCaseMapper, SuiteCase> {

    @Override
    public boolean hasDeleted() {
        return false;
    }

    @Override
    public boolean hasId() {
        return false;
    }

}
