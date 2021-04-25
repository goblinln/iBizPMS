package cn.ibizlab.pms.core.ibizplugin.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProMessageSearchContext;


/**
 * 实体[IBIZProMessage] 服务对象接口
 */
public interface IIBIZProMessageService {

    boolean create(IBIZProMessage et);
    void createBatch(List<IBIZProMessage> list);
    boolean update(IBIZProMessage et);
    boolean sysUpdate(IBIZProMessage et);
    void updateBatch(List<IBIZProMessage> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IBIZProMessage get(String key);
    IBIZProMessage sysGet(String key);
    IBIZProMessage getDraft(IBIZProMessage et);
    boolean checkKey(IBIZProMessage et);
    IBIZProMessage markDone(IBIZProMessage et);
    boolean markDoneBatch(List<IBIZProMessage> etList);
    IBIZProMessage markRead(IBIZProMessage et);
    boolean markReadBatch(List<IBIZProMessage> etList);
    boolean save(IBIZProMessage et);
    void saveBatch(List<IBIZProMessage> list);
    IBIZProMessage send(IBIZProMessage et);
    boolean sendBatch(List<IBIZProMessage> etList);
    
    List<IBIZProMessage> selectQueryByDefault(IBIZProMessageSearchContext context);
    List<IBIZProMessage> selectQueryByUserAllMessages(IBIZProMessageSearchContext context);
    List<IBIZProMessage> selectQueryByUserUnreadMessages(IBIZProMessageSearchContext context);
    List<IBIZProMessage> selectQueryByView(IBIZProMessageSearchContext context);

    Page<IBIZProMessage> searchDefault(IBIZProMessageSearchContext context);
    Page<IBIZProMessage> searchUserAllMessages(IBIZProMessageSearchContext context);
    Page<IBIZProMessage> searchUserUnreadMessages(IBIZProMessageSearchContext context);
    IBIZProMessage dynamicCall(String key, String action, IBIZProMessage et);
}



