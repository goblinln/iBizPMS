package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.report.service.impl.IbzReportlyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[汇报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzReportlyExService")
public class IbzReportlyExService extends IbzReportlyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [HaveRead:已读] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzReportly haveRead(IbzReportly et) {
        return super.haveRead(et);
    }
    /**
     * [Submit:提交] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzReportly submit(IbzReportly et) {
        return super.submit(et);
    }
}

