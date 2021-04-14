package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.DocLibServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.DocLib;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[文档库] 自定义服务对象
 */
@Slf4j
@Primary
@Service("DocLibExService")
public class DocLibExService extends DocLibServiceImpl {

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
    public DocLib collect(DocLib et) {
        return super.collect(et);
    }
    /**
     * [UnCollect:取消收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public DocLib unCollect(DocLib et) {
        return super.unCollect(et);
    }
}

