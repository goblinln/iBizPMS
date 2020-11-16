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

import cn.ibizlab.pms.core.zentao.service.logic.IStoryStoryNFavoritesLogic;
import cn.ibizlab.pms.core.zentao.domain.Story;

/**
 * 关系型数据实体[StoryNFavorites] 对象
 */
@Slf4j
@Service
public class StoryStoryNFavoritesLogicImpl implements IStoryStoryNFavoritesLogic {

    @Autowired
    private KieContainer kieContainer;


    @Autowired
    private cn.ibizlab.pms.core.zentao.service.IStoryService iBzSysDefaultService;

    public cn.ibizlab.pms.core.zentao.service.IStoryService getIBzSysDefaultService() {
        return this.iBzSysDefaultService;
    }

    @Override
    public void execute(Story et) {

          KieSession kieSession = null;
        try{
           kieSession = kieContainer.newKieSession();
           kieSession.insert(et); 
           kieSession.setGlobal("storystorynfavoritesdefault", et);
           kieSession.setGlobal("iBzSysStoryDefaultService", iBzSysDefaultService);
           kieSession.setGlobal("curuser", cn.ibizlab.pms.util.security.AuthenticationUser.getAuthenticationUser());
           kieSession.startProcess("cn.ibizlab.pms.core.zentao.service.logic.storystorynfavorites");

        }catch(Exception e) {
            throw new RuntimeException("执行[需求取消收藏]处理逻辑发生异常"+e);
        }finally {
            if(kieSession!=null) {
                kieSession.destroy();
            }
        }
    }

}
