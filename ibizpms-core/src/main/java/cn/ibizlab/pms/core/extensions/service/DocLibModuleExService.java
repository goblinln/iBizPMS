package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.service.impl.DocLibModuleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[文档库分类] 自定义服务对象
 */
@Slf4j
@Primary
@Service("DocLibModuleExService")
public class DocLibModuleExService extends DocLibModuleServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Collect:收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public DocLibModule collect(DocLibModule et) {
        return super.collect(et);
    }
    /**
     * [UnCollect:取消收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public DocLibModule unCollect(DocLibModule et) {
        return super.unCollect(et);
    }
}

