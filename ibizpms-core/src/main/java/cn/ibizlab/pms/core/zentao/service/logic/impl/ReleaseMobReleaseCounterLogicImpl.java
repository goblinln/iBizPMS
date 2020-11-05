package cn.ibizlab.pms.core.zentao.service.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieContainer;

import cn.ibizlab.pms.core.zentao.service.logic.IReleaseMobReleaseCounterLogic;
import cn.ibizlab.pms.core.zentao.domain.Release;

/**
 * 关系型数据实体[MobReleaseCounter] 对象
 */
@Slf4j
@Service
public class ReleaseMobReleaseCounterLogicImpl implements IReleaseMobReleaseCounterLogic{

    @Autowired
    private KieContainer kieContainer;


    @Autowired
    private cn.ibizlab.pms.core.zentao.service.IReleaseService iBzSysDefaultService;

    public cn.ibizlab.pms.core.zentao.service.IReleaseService getIBzSysDefaultService() {
        return this.iBzSysDefaultService;
    }

    public void execute(Release et){

          KieSession kieSession = null;
        try{
           kieSession=kieContainer.newKieSession();
           kieSession.insert(et); 
           kieSession.setGlobal("releasemobreleasecounterdefault",et);
           kieSession.setGlobal("iBzSysReleaseDefaultService",iBzSysDefaultService);
           kieSession.setGlobal("curuser", cn.ibizlab.pms.util.security.AuthenticationUser.getAuthenticationUser());
           kieSession.startProcess("cn.ibizlab.pms.core.zentao.service.logic.releasemobreleasecounter");

        }catch(Exception e){
            throw new RuntimeException("执行[移动端产品发布计数器]处理逻辑发生异常"+e);
        }finally {
            if(kieSession!=null)
            kieSession.destroy();
        }
    }

}
