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

import cn.ibizlab.pms.core.zentao.service.logic.IProductProductTopLogic;
import cn.ibizlab.pms.core.zentao.domain.Product;

/**
 * 关系型数据实体[ProductTop] 对象
 */
@Slf4j
@Service
public class ProductProductTopLogicImpl implements IProductProductTopLogic{

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private cn.ibizlab.pms.core.ibiz.service.IIbzTopService ibztopservice;

    public cn.ibizlab.pms.core.ibiz.service.IIbzTopService getIbztopService() {
        return this.ibztopservice;
    }


    @Autowired
    private cn.ibizlab.pms.core.zentao.service.IProductService iBzSysDefaultService;

    public cn.ibizlab.pms.core.zentao.service.IProductService getIBzSysDefaultService() {
        return this.iBzSysDefaultService;
    }

    @Override
    public void execute(Product et){

          KieSession kieSession = null;
        try{
           kieSession=kieContainer.newKieSession();
           cn.ibizlab.pms.core.ibiz.domain.IbzTop  productproducttopibztop =new cn.ibizlab.pms.core.ibiz.domain.IbzTop();
           kieSession.insert(productproducttopibztop); 
           kieSession.setGlobal("productproducttopibztop",productproducttopibztop);
           kieSession.insert(et); 
           kieSession.setGlobal("productproducttopdefault",et);
           kieSession.setGlobal("ibztopservice",ibztopservice);
           kieSession.setGlobal("iBzSysProductDefaultService",iBzSysDefaultService);
           kieSession.setGlobal("curuser", cn.ibizlab.pms.util.security.AuthenticationUser.getAuthenticationUser());
           kieSession.startProcess("cn.ibizlab.pms.core.zentao.service.logic.productproducttop");

        }catch(Exception e){
            throw new RuntimeException("执行[置顶]处理逻辑发生异常"+e);
        }finally {
            if(kieSession!=null) {
                kieSession.destroy();
            }
        }
    }

}
