package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.IbzMyTerritory;
import cn.ibizlab.pms.core.ibiz.filter.IbzMyTerritorySearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzMyTerritoryService;
import cn.ibizlab.pms.core.uaa.filter.SysUserSearchContext;
import cn.ibizlab.pms.core.uaa.service.impl.SysUserServiceImpl;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.uaa.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[系统用户] 自定义服务对象
 */
@Slf4j
@Primary
@Service("SysUserExService")
public class SysUserExService extends SysUserServiceImpl {


    @Autowired
    IIbzMyTerritoryService iIbzMyTerritoryService;

    /**
     * [ChangePwd:修改密码] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public SysUser changePwd(SysUser et) {
        return super.changePwd(et);
    }

    @Override
    public SysUser get(String userid) {
        SysUser sysUser = new SysUser();
        AuthenticationUser authenticationUser = AuthenticationUser.getAuthenticationUser();
        CachedBeanCopier.copy(authenticationUser, sysUser);
        return sysUser;
    }

    @Override
    public Page<SysUser> searchMyWork(SysUserSearchContext context) {
        IbzMyTerritorySearchContext searchContext = new IbzMyTerritorySearchContext();
        searchContext.setN_account_eq(AuthenticationUser.getAuthenticationUser().getLoginname());
        List<IbzMyTerritory> list = iIbzMyTerritoryService.selectMyWork(searchContext);
        SysUser sysUser = get(AuthenticationUser.getAuthenticationUser().getUserid());
        List<SysUser> userList = new ArrayList<>();
        if(list.size() > 0) {
            CachedBeanCopier.copy(list.get(0), sysUser);
        }
        userList.add(sysUser);
        return new PageImpl<SysUser>(userList, context.getPageable(), 1);
    }

    @Override
    public Page<SysUser> searchPersonInfo(SysUserSearchContext context) {
        IbzMyTerritorySearchContext searchContext = new IbzMyTerritorySearchContext();
        List<IbzMyTerritory> list = iIbzMyTerritoryService.selectPersonInfo(searchContext);
        SysUser sysUser = get(AuthenticationUser.getAuthenticationUser().getUserid());
        List<SysUser> userList = new ArrayList<>();
        if(list.size() > 0) {
            CachedBeanCopier.copy(list.get(0), sysUser);
        }
        userList.add(sysUser);
        return new PageImpl<SysUser>(userList, context.getPageable(), 1);
    }
}

