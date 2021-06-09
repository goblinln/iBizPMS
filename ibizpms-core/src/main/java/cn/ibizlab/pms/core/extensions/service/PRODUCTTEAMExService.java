package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;
import cn.ibizlab.pms.core.ibiz.service.impl.PRODUCTTEAMServiceImpl;
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.util.dict.StaticDict;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品团队] 自定义服务对象
 */
@Slf4j
@Primary
@Service("PRODUCTTEAMExService")
public class PRODUCTTEAMExService extends PRODUCTTEAMServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    ISysEmployeeService iSysEmployeeService;

    /**
     * [ProductTeamGuoLv:PmsEe团队管理过滤] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public PRODUCTTEAM productTeamGuoLv(PRODUCTTEAM et) {
        return super.productTeamGuoLv(et);
    }

    @Override
    public Page<PRODUCTTEAM> searchProductTeamInfo(PRODUCTTEAMSearchContext context) {
        Map<String, Object> params = context.getParams();
        List<PRODUCTTEAM> productteams = new ArrayList<>();
        if(params.get("teams") != null) {
            productteams.addAll(super.selectRowEditDefaultProductTeam(context));
        }else {
            productteams.addAll(super.selectProductTeamInfo(context));
        }
        if(params.get("dept") != null) {
            List<SysEmployee> list = iSysEmployeeService.selectByMdeptid(params.get("dept").toString());
            Long root = params.get("root") != null ? Long.parseLong(params.get("root").toString()) : 0L;
            for(SysEmployee sysEmployee : list) {
                PRODUCTTEAM productteam = new PRODUCTTEAM();
                productteam.setRoot(root);
                productteam.setDays(5);
                productteam.setHours(7D);
                productteam.setLimited(StaticDict.YesNo.ITEM_0.getValue());
                productteam.setAccount(sysEmployee.getLoginname());
                productteams.add(productteam);
            }
        }
        return new PageImpl<PRODUCTTEAM>(productteams, context.getPageable(), productteams.size());
    }
}

