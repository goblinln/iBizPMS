package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.DocServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Doc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[文档] 自定义服务对象
 */
@Slf4j
@Primary
@Service("DocExService")
public class DocExService extends DocServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ByVersionUpdateContext:根据版本更新正文信息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc byVersionUpdateContext(Doc et) {
        return super.byVersionUpdateContext(et);
    }
    /**
     * [Collect:收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc collect(Doc et) {
        return super.collect(et);
    }
    /**
     * [GetDocStatus:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc getDocStatus(Doc et) {
        return super.getDocStatus(et);
    }
    /**
     * [OnlyCollectDoc:仅收藏文档] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc onlyCollectDoc(Doc et) {
        return super.onlyCollectDoc(et);
    }
    /**
     * [OnlyUnCollectDoc:仅取消收藏文档] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc onlyUnCollectDoc(Doc et) {
        return super.onlyUnCollectDoc(et);
    }
    /**
     * [UnCollect:取消收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc unCollect(Doc et) {
        return super.unCollect(et);
    }
}

