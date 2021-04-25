package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.zentao.domain.TestRun;
import cn.ibizlab.pms.core.zentao.mapper.TestRunMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class TestRunHelper extends ZTBaseHelper<TestRunMapper, TestRun> {

    @Override
    public boolean hasDeleted() {
        return false ;
    }
}
