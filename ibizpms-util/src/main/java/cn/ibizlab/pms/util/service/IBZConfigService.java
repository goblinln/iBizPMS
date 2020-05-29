package cn.ibizlab.pms.util.service;

import cn.ibizlab.pms.util.domain.IBZConfig;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.DataObject;
import cn.ibizlab.pms.util.mapper.IBZConfigMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class IBZConfigService extends ServiceImpl<IBZConfigMapper, IBZConfig> implements IService<IBZConfig> {


    @Value("${ibiz.systemid:pms}")
	private String systemId;

    @Cacheable( value="ibzou_configs",key = "'cfgid:'+#p0+'||'+#p1+'||'+#p2")
    public JSONObject getConfig(String cfgType,String targetType,String userId)
    {
        if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(cfgType)||StringUtils.isEmpty(targetType))
            throw new BadRequestAlertException("获取配置失败，参数缺失","IBZConfig",cfgType);
        IBZConfig config=this.getOne(Wrappers.query(IBZConfig.builder().systemId(systemId).cfgType(cfgType).targetType(targetType).userId(userId).build()),false);
        if(config==null)
            return new JSONObject();
        else
            return JSON.parseObject(config.getCfg());
    }

    @CacheEvict( value="ibzou_configs",key = "'cfgid:'+#p0+'||'+#p1+'||'+#p2")
    public boolean saveConfig(String cfgType,String targetType,String userId,JSONObject config)
    {
        if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(cfgType)||StringUtils.isEmpty(targetType))
            throw new BadRequestAlertException("保存配置失败，参数缺失","IBZConfig",cfgType);
        String cfg="{}";
        if(config!=null)
            cfg=JSONObject.toJSONString(config);
        return this.saveOrUpdate(IBZConfig.builder().systemId(systemId).cfgType(cfgType).targetType(targetType).userId(userId).cfg(cfg).updateDate(DataObject.getNow()).build());
    }

    @CacheEvict( value="ibzou_configs",key = "'cfgid:'+#p0+'||'+#p1+'||'+#p2")
    public void resetConfig(String cfgType,String targetType,String userId)
    {
        if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(cfgType)||StringUtils.isEmpty(targetType))
            throw new BadRequestAlertException("重置配置失败，参数缺失","IBZConfig",cfgType);
        this.remove(Wrappers.query(IBZConfig.builder().systemId(systemId).cfgType(cfgType).targetType(targetType).userId(userId).build()));
    }

}