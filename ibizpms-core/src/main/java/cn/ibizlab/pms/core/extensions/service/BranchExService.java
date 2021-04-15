package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.BranchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Branch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品的分支和平台信息] 自定义服务对象
 */
@Slf4j
@Primary
@Service("BranchExService")
public class BranchExService extends BranchServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Sort:排序] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Branch sort(Branch et) {
        return super.sort(et);
    }
}

