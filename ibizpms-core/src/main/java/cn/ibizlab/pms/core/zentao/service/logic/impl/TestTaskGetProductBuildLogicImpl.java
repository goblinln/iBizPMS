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

import cn.ibizlab.pms.core.zentao.service.logic.ITestTaskGetProductBuildLogic;
import cn.ibizlab.pms.core.zentao.domain.TestTask;

/**
 * 关系型数据实体[GetProductBuild] 对象
 */
@Slf4j
@Service
public class TestTaskGetProductBuildLogicImpl implements ITestTaskGetProductBuildLogic {

    @Autowired
    private KieContainer kieContainer;


    @Autowired
    private cn.ibizlab.pms.core.zentao.service.ITestTaskService iBzSysDefaultService;

    public cn.ibizlab.pms.core.zentao.service.ITestTaskService getIBzSysDefaultService() {
        return this.iBzSysDefaultService;
    }

    @Override
    public void execute(TestTask et) {

          KieSession kieSession = null;
        try{
           kieSession = kieContainer.newKieSession();
           kieSession.insert(et); 
           kieSession.setGlobal("testtaskgetproductbuilddefault", et);
           kieSession.setGlobal("iBzSysTesttaskDefaultService", iBzSysDefaultService);
           kieSession.setGlobal("curuser", cn.ibizlab.pms.util.security.AuthenticationUser.getAuthenticationUser());
           kieSession.startProcess("cn.ibizlab.pms.core.zentao.service.logic.testtaskgetproductbuild");

        }catch(Exception e) {
            throw new RuntimeException("执行[获取产品及版本]处理逻辑发生异常"+e);
        }finally {
            if(kieSession!=null) {
                kieSession.destroy();
            }
        }
    }

}
