package cn.ibizlab.pms.core.ibizsysmodel.service.impl;

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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDEFieldSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSDEFieldService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSDEFieldFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[实体属性] 服务对象接口实现
 */
@Slf4j
@Service
public class PSDEFieldServiceImpl implements IPSDEFieldService {

//    @Autowired
    PSDEFieldFeignClient pSDEFieldFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSDEFieldFeignClient getPSDEFieldFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSDEFieldFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSDEFieldFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSDEField et) {
        PSDEField rt = pSDEFieldFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSDEField et) {
        PSDEField rt = getPSDEFieldFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSDEField> list){
        pSDEFieldFeignClient.createBatch(list) ;
    }

    public void createBatch(String devSlnSysId, List<PSDEField> list){
        getPSDEFieldFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSDEField et) {
        PSDEField rt = pSDEFieldFeignClient.update(et.getPsdefieldid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSDEField et) {
        PSDEField rt = getPSDEFieldFeignClient(devSlnSysId).update(et.getPsdefieldid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSDEField> list){
        pSDEFieldFeignClient.updateBatch(list) ;
    }

    public void updateBatch(String devSlnSysId, List<PSDEField> list){
        getPSDEFieldFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String psdefieldid) {
        boolean result=pSDEFieldFeignClient.remove(psdefieldid) ;
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String psdefieldid) {
        boolean result = getPSDEFieldFeignClient(devSlnSysId).remove(psdefieldid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSDEFieldFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSDEFieldFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSDEField get(String psdefieldid) {
		PSDEField et=pSDEFieldFeignClient.get(psdefieldid);
        if(et==null){
            et=new PSDEField();
            et.setPsdefieldid(psdefieldid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSDEField get(String devSlnSysId, String psdefieldid) {
        PSDEField et = getPSDEFieldFeignClient(devSlnSysId).get(psdefieldid);
        if (et == null) {
            et = new PSDEField();
            et.setPsdefieldid(psdefieldid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSDEFieldFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSDEField getDraft(PSDEField et) {
        et=pSDEFieldFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSDEField getDraft(String devSlnSysId, PSDEField et) {
        et = getPSDEFieldFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSDEField et) {
        return pSDEFieldFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSDEField et) {
        return getPSDEFieldFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSDEField et) {
        boolean result = true;
        Object rt = pSDEFieldFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSDEField.class);
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
    @Transactional
    public boolean save(String devSlnSysId, PSDEField et) {
        if (et.getPsdefieldid() == null) {
            et.setPsdefieldid((String)et.getDefaultKey(true));
        }
        if (!getPSDEFieldFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSDEField> list) {
        pSDEFieldFeignClient.saveBatch(list) ;
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSDEField> list) {
        getPSDEFieldFeignClient(devSlnSysId).saveBatch(list);
    }



	@Override
    public List<PSDEField> selectByPsdeid(String psdataentityid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psdeid_eq(psdataentityid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByPsdeid(String devSlnSysId, String psdataentityid) {
        PSDEFieldSearchContext context = new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psdeid_eq(psdataentityid);
        return getPSDEFieldFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public void removeByPsdeid(Collection<String> ids) {
    }


    @Override
    public void removeByPsdeid(String psdataentityid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByPsdeid(psdataentityid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByPsdeid(String devSlnSysId, String psdataentityid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDEField before:selectByPsdeid(devSlnSysId, psdataentityid)){
            delIds.add(before.getPsdefieldid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

	@Override
    public List<PSDEField> selectByDerpsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_derpsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByDerpsdefid(String devSlnSysId, String psdefieldid) {
        PSDEFieldSearchContext context = new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_derpsdefid_eq(psdefieldid);
        return getPSDEFieldFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByDerpsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByDerpsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByDerpsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByDerpsdefid(String devSlnSysId, String psdefieldid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDEField before:selectByDerpsdefid(devSlnSysId, psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

	@Override
    public List<PSDEField> selectByDupcheckpsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_dupchkpsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByDupcheckpsdefid(String devSlnSysId, String psdefieldid) {
        PSDEFieldSearchContext context = new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_dupchkpsdefid_eq(psdefieldid);
        return getPSDEFieldFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByDupcheckpsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByDupcheckpsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByDupcheckpsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByDupcheckpsdefid(String devSlnSysId, String psdefieldid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDEField before:selectByDupcheckpsdefid(devSlnSysId, psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

	@Override
    public List<PSDEField> selectByNo2dupchkpsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_no2dupchkpsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByNo2dupchkpsdefid(String devSlnSysId, String psdefieldid) {
        PSDEFieldSearchContext context = new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_no2dupchkpsdefid_eq(psdefieldid);
        return getPSDEFieldFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByNo2dupchkpsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByNo2dupchkpsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByNo2dupchkpsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByNo2dupchkpsdefid(String devSlnSysId, String psdefieldid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDEField before:selectByNo2dupchkpsdefid(devSlnSysId, psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

	@Override
    public List<PSDEField> selectByNo3dupchkpsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_no3dupchkpsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByNo3dupchkpsdefid(String devSlnSysId, String psdefieldid) {
        PSDEFieldSearchContext context = new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_no3dupchkpsdefid_eq(psdefieldid);
        return getPSDEFieldFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByNo3dupchkpsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByNo3dupchkpsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByNo3dupchkpsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByNo3dupchkpsdefid(String devSlnSysId, String psdefieldid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDEField before:selectByNo3dupchkpsdefid(devSlnSysId, psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

	@Override
    public List<PSDEField> selectByValuepsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_valuepsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByValuepsdefid(String devSlnSysId, String psdefieldid) {
        PSDEFieldSearchContext context = new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_valuepsdefid_eq(psdefieldid);
        return getPSDEFieldFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDEField> selectByValuepsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByValuepsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByValuepsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByValuepsdefid(String devSlnSysId, String psdefieldid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDEField before:selectByValuepsdefid(devSlnSysId, psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSDEField> searchDefault(PSDEFieldSearchContext context) {
        Page<PSDEField> pSDEFields=pSDEFieldFeignClient.searchDefault(context);
        return pSDEFields;
    }

    @Override
    public Page<PSDEField> searchDefault(String devSlnSysId, PSDEFieldSearchContext context) {
        Page<PSDEField> pSDEFields=getPSDEFieldFeignClient(devSlnSysId).searchDefault(context);
        return pSDEFields;
    }

    @Override
    @Transactional
    public PSDEField dynamicCall(String key, String action, PSDEField et) {
        return et;
    }
}


