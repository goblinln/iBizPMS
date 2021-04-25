package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.zentao.domain.UserTpl;
import cn.ibizlab.pms.core.zentao.mapper.UserTplMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class UserTplHelper extends ZTBaseHelper<UserTplMapper, UserTpl> {

    @Override
    public boolean hasDeleted(){
        return false ;
    }

}
