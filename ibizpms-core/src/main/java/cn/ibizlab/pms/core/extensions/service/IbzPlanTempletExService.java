package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IbzPlanTempletServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[计划模板] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzPlanTempletExService")
public class IbzPlanTempletExService extends IbzPlanTempletServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [GetPlan:获取计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzPlanTemplet getPlan(IbzPlanTemplet et) {
        return super.getPlan(et);
    }
}

