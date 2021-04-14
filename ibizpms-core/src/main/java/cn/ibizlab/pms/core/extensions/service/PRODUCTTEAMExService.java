package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.service.impl.PRODUCTTEAMServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品团队] 自定义服务对象
 */
@Slf4j
@Primary
@Service("PRODUCTTEAMExService")
public class PRODUCTTEAMExService extends PRODUCTTEAMServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ProductTeamGuoLv:PmsEe团队管理过滤] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public PRODUCTTEAM productTeamGuoLv(PRODUCTTEAM et) {
        return super.productTeamGuoLv(et);
    }
}

