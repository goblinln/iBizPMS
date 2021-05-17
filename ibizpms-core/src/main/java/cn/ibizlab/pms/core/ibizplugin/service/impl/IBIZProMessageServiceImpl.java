package cn.ibizlab.pms.core.ibizplugin.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.math.BigInteger;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProMessageSearchContext;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProMessageService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizplugin.client.IBIZProMessageFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
/**
 * 实体[消息] 服务对象接口实现
 */
@Slf4j
@Service
public class IBIZProMessageServiceImpl implements IIBIZProMessageService {

    @Autowired
    IBIZProMessageFeignClient iBIZProMessageFeignClient;


    @Override
    public boolean create(IBIZProMessage et) {
        IBIZProMessage rt = iBIZProMessageFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<IBIZProMessage> list){
        iBIZProMessageFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(IBIZProMessage et) {
        IBIZProMessage rt = iBIZProMessageFeignClient.update(et.getIbizpromessageid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<IBIZProMessage> list){
        iBIZProMessageFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(IBIZProMessage et) {
                                IBIZProMessage rt = iBIZProMessageFeignClient.update(et.getIbizpromessageid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String ibizpromessageid) {
        boolean result=iBIZProMessageFeignClient.remove(ibizpromessageid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        iBIZProMessageFeignClient.removeBatch(idList);
    }

    @Override
    public IBIZProMessage get(String ibizpromessageid) {
		IBIZProMessage et=iBIZProMessageFeignClient.get(ibizpromessageid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), ibizpromessageid);
        }
        else{
        }
        return  et;
    }

    @Override
    public IBIZProMessage sysGet(String ibizpromessageid) {
         return null;
    }

    @Override
    public IBIZProMessage getDraft(IBIZProMessage et) {
        et=iBIZProMessageFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(IBIZProMessage et) {
        return iBIZProMessageFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public IBIZProMessage markDone(IBIZProMessage et) {
        et = iBIZProMessageFeignClient.markDone(et.getIbizpromessageid(), et);
        return et;
    }
    @Override
    @Transactional
    public boolean markDoneBatch(List<IBIZProMessage> etList) {
        for(IBIZProMessage et : etList) {
            markDone(et);
        }
        return true;
    }

    @Override
    @Transactional
    public IBIZProMessage markRead(IBIZProMessage et) {
        et = iBIZProMessageFeignClient.markRead(et.getIbizpromessageid(), et);
        return et;
    }
    @Override
    @Transactional
    public boolean markReadBatch(List<IBIZProMessage> etList) {
        for(IBIZProMessage et : etList) {
            markRead(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(IBIZProMessage et) {
        boolean result = true;
        Object rt = iBIZProMessageFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), IBIZProMessage.class);
                if (rt != null) {
                    CachedBeanCopier.copy(rt, et);
                }
            } else if (rt instanceof Boolean) {
                result = (boolean) rt;
            }
        } catch (Exception e) {
        }
            return result;
    }

    @Override
    public void saveBatch(List<IBIZProMessage> list) {
        iBIZProMessageFeignClient.saveBatch(list) ;
    }

    @Override
    @Transactional
    public IBIZProMessage send(IBIZProMessage et) {
        et = iBIZProMessageFeignClient.send(et.getIbizpromessageid(), et);
        return et;
    }
    @Override
    @Transactional
    public boolean sendBatch(List<IBIZProMessage> etList) {
        for(IBIZProMessage et : etList) {
            send(et);
        }
        return true;
    }





    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBIZProMessage> searchDefault(IBIZProMessageSearchContext context) {
        Page<IBIZProMessage> iBIZProMessages=iBIZProMessageFeignClient.searchDefault(context);
        return iBIZProMessages;
    }

    /**
     * 查询集合 用户全部消息
     */
    @Override
    public Page<IBIZProMessage> searchUserAllMessages(IBIZProMessageSearchContext context) {
        Page<IBIZProMessage> iBIZProMessages=iBIZProMessageFeignClient.searchUserAllMessages(context);
        return iBIZProMessages;
    }

    /**
     * 查询集合 用户未读信息
     */
    @Override
    public Page<IBIZProMessage> searchUserUnreadMessages(IBIZProMessageSearchContext context) {
        Page<IBIZProMessage> iBIZProMessages=iBIZProMessageFeignClient.searchUserUnreadMessages(context);
        return iBIZProMessages;
    }

    @Override
    @Transactional
    public IBIZProMessage dynamicCall(String key, String action, IBIZProMessage et) {
        return et;
    }
}



