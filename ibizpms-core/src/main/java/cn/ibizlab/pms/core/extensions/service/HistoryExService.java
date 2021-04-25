package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.HistoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.History;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[操作历史] 自定义服务对象
 */
@Slf4j
@Primary
@Service("HistoryExService")
public class HistoryExService extends HistoryServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [LogHistory:创建历史记录] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public History logHistory(History et) {
        return super.logHistory(et);
    }
}

