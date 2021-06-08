package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.filter.ProductModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.impl.ProductModuleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.ProductModule;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[需求模块] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProductModuleExService")
public class ProductModuleExService extends ProductModuleServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [SyncFromIBIZ:同步Ibz平台模块] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductModule syncFromIBIZ(ProductModule et) {
        return super.syncFromIBIZ(et);
    }

    @Override
    public Page<ProductModule> searchDefault(ProductModuleSearchContext context) {
        Map<String, Object> params = context.getParams();
        if(params.get("action") != null && "update".equals(params.get("action"))) {
            super.searchParentModule(context);
        }
        return super.searchDefault(context);
    }
}

