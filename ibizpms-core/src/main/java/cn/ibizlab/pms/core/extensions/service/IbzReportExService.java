package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.service.impl.IbzReportServiceImpl;
import cn.ibizlab.pms.util.domain.EntityMP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Primary
@Service("IbzReportExService")
public class IbzReportExService extends IbzReportServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public IbzReport sysGet(Long key) {
        if (key == 0){
            IbzReport result = new IbzReport();
            result.setIbzdailyid(0L);
            result.setType("daily");
            return result;
        }else {
            return super.sysGet(key);
        }
    }


    @Override
    public IbzReport get(Long key) {
        if (key == 0){
            IbzReport result = new IbzReport();
            result.setIbzdailyid(0L);
            result.setType("daily");
            return result;
        }else {
            return super.sysGet(key);
        }
    }
}
