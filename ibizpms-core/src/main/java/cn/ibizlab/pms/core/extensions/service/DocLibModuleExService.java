package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.service.impl.DocLibModuleServiceImpl;
import cn.ibizlab.pms.util.security.AuthenticationUser;
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
    @Transactional(rollbackFor = Exception.class)
    public DocLibModule collect(DocLibModule et) {
        DocLibModule docLibModule = this.get(et.getId());
        String collector = docLibModule.getCollector();
        if ("".equals(collector) || "/".equals(collector)) {
            collector += ",";
        }
        collector += AuthenticationUser.getAuthenticationUser().getUsername() + ",";
        et.setCollector(collector);
        this.updateById(et);
        return super.collect(et);
    }

    @Override
    public DocLibModule sysGet(Long key) {
        if (key == 0){
            return null;
        }
        DocLibModule docLibModule = super.sysGet(key);
        if(docLibModule.getCollector() != null && docLibModule.getCollector().contains(AuthenticationUser.getAuthenticationUser().getUsername())) {
            docLibModule.setIsfavourites("1");
        }else {
            docLibModule.setIsfavourites("0");
        }
        return docLibModule;
    }

    /**
     * [UnCollect:取消收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DocLibModule unCollect(DocLibModule et) {
        DocLibModule docLibModule = this.get(et.getId());
        String collector = docLibModule.getCollector();
        collector = collector.replaceFirst(AuthenticationUser.getAuthenticationUser().getUsername() + ",", "");
        if (",".equals(collector)) {
            collector = "";
        }
        et.setCollector(collector);
        this.updateById(et);
        return super.unCollect(et);
    }
}

