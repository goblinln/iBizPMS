package cn.ibizlab.pms.core.ibizsysmodel.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.ObjectUtils;
import org.springframework.util.DigestUtils;
import cn.ibizlab.pms.util.domain.EntityBase;
import cn.ibizlab.pms.util.annotation.DEField;
import cn.ibizlab.pms.util.enums.DEPredefinedFieldType;
import cn.ibizlab.pms.util.enums.DEFieldDefaultValueType;
import cn.ibizlab.pms.util.helper.DataObject;
import cn.ibizlab.pms.util.enums.DupCheck;
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;

import cn.ibizlab.pms.util.domain.EntityClient;

/**
 * ServiceApi [系统服务接口] 对象
 */
@Data
public class PSSysServiceAPI extends EntityClient implements Serializable {
    @Override
    public void modify(String field, Object val) {
        getExtensionparams().put("dirtyflagenable", true);
        super.modify(field, val);
    }

    /**
     * 认证客户端标识
     */
    @JSONField(name = "authclientid")
    @JsonProperty("authclientid")
    private String authclientid;

    /**
     * 接口模式
     */
    @JSONField(name = "apimode")
    @JsonProperty("apimode")
    private Integer apimode;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;

    /**
     * 平台预定义类型
     */
    @JSONField(name = "predefinedtype")
    @JsonProperty("predefinedtype")
    private String predefinedtype;

    /**
     * 系统服务接口标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "pssysserviceapiid")
    @JsonProperty("pssysserviceapiid")
    private String pssysserviceapiid;

    /**
     * 服务参数4
     */
    @JSONField(name = "serviceparam4")
    @JsonProperty("serviceparam4")
    private String serviceparam4;

    /**
     * 配置标记
     */
    @JSONField(name = "cfgtag")
    @JsonProperty("cfgtag")
    private String cfgtag;

    /**
     * 服务参数
     */
    @JSONField(name = "serviceparam")
    @JsonProperty("serviceparam")
    private String serviceparam;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;

    /**
     * 服务代码名称
     */
    @JSONField(name = "servicecodename")
    @JsonProperty("servicecodename")
    private String servicecodename;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;

    /**
     * 模型锁标志
     */
    @JSONField(name = "lockflag")
    @JsonProperty("lockflag")
    private Integer lockflag;

    /**
     * 默认查询请求方式
     */
    @DEField(defaultValue = "GET")
    @JSONField(name = "defselectreqmethod")
    @JsonProperty("defselectreqmethod")
    private String defselectreqmethod;

    /**
     * 服务参数2
     */
    @JSONField(name = "serviceparam2")
    @JsonProperty("serviceparam2")
    private String serviceparam2;

    /**
     * 代码名称
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    private String codename;

    /**
     * 认证参数4
     */
    @JSONField(name = "authparam4")
    @JsonProperty("authparam4")
    private String authparam4;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    private String usercat;

    /**
     * 认证模式
     */
    @JSONField(name = "authmode")
    @JsonProperty("authmode")
    private String authmode;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    private String usertag;

    /**
     * 接口类型
     */
    @JSONField(name = "apitype")
    @JsonProperty("apitype")
    private String apitype;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    private String pssystemname;

    /**
     * 认证参数2
     */
    @JSONField(name = "authparam2")
    @JsonProperty("authparam2")
    private String authparam2;

    /**
     * 配置模型存储标识
     */
    @JSONField(name = "cfgpsmodelstorageid")
    @JsonProperty("cfgpsmodelstorageid")
    private String cfgpsmodelstorageid;

    /**
     * 接口标记
     */
    @JSONField(name = "apitag")
    @JsonProperty("apitag")
    private String apitag;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    private String usertag4;

    /**
     * 服务参数3
     */
    @JSONField(name = "serviceparam3")
    @JsonProperty("serviceparam3")
    private String serviceparam3;

    /**
     * 默认结果集请求方式
     */
    @DEField(defaultValue = "GET")
    @JSONField(name = "defdedatasetreqmethod")
    @JsonProperty("defdedatasetreqmethod")
    private String defdedatasetreqmethod;

    /**
     * 唯一标识
     */
    @JSONField(name = "uniquetag")
    @JsonProperty("uniquetag")
    private String uniquetag;

    /**
     * 服务类型
     */
    @JSONField(name = "servicetype")
    @JsonProperty("servicetype")
    private String servicetype;

    /**
     * 系统服务接口名称
     */
    @JSONField(name = "pssysserviceapiname")
    @JsonProperty("pssysserviceapiname")
    private String pssysserviceapiname;

    /**
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    private String pssystemid;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    private String usertag3;

    /**
     * 是否启用
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "validflag")
    @JsonProperty("validflag")
    private Integer validflag;

    /**
     * 接口标记2
     */
    @JSONField(name = "apitag2")
    @JsonProperty("apitag2")
    private String apitag2;

    /**
     * 认证token路径
     */
    @JSONField(name = "authchecktokenuri")
    @JsonProperty("authchecktokenuri")
    private String authchecktokenuri;

    /**
     * 默认实体行为请求方式
     */
    @JSONField(name = "defdeactionreqmethod")
    @JsonProperty("defdeactionreqmethod")
    private String defdeactionreqmethod;

    /**
     * 认证参数
     */
    @JSONField(name = "authparam")
    @JsonProperty("authparam")
    private String authparam;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;

    /**
     * 认证参数3
     */
    @JSONField(name = "authparam3")
    @JsonProperty("authparam3")
    private String authparam3;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    private String usertag2;

    /**
     * 认证客户端密钥
     */
    @JSONField(name = "authclientsecret")
    @JsonProperty("authclientsecret")
    private String authclientsecret;

    /**
     * 版本
     */
    @JSONField(name = "ver")
    @JsonProperty("ver")
    private Integer ver;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    private String memo;

    /**
     * 系统模块
     */
    @JSONField(name = "psmodulename")
    @JsonProperty("psmodulename")
    private String psmodulename;

    /**
     * 系统模块
     */
    @JSONField(name = "psmoduleid")
    @JsonProperty("psmoduleid")
    private String psmoduleid;


    /**
     * 
     */
    @JSONField(name = "psmodule")
    @JsonProperty("psmodule")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule psmodule;




    /**
     * 设置 [认证客户端标识]
     */
    public void setAuthclientid(String authclientid) {
        this.authclientid = authclientid ;
        this.modify("authclientid", authclientid);
    }

    /**
     * 设置 [接口模式]
     */
    public void setApimode(Integer apimode) {
        this.apimode = apimode ;
        this.modify("apimode", apimode);
    }

    /**
     * 设置 [平台预定义类型]
     */
    public void setPredefinedtype(String predefinedtype) {
        this.predefinedtype = predefinedtype ;
        this.modify("predefinedtype", predefinedtype);
    }

    /**
     * 设置 [服务参数4]
     */
    public void setServiceparam4(String serviceparam4) {
        this.serviceparam4 = serviceparam4 ;
        this.modify("serviceparam4", serviceparam4);
    }

    /**
     * 设置 [配置标记]
     */
    public void setCfgtag(String cfgtag) {
        this.cfgtag = cfgtag ;
        this.modify("cfgtag", cfgtag);
    }

    /**
     * 设置 [服务参数]
     */
    public void setServiceparam(String serviceparam) {
        this.serviceparam = serviceparam ;
        this.modify("serviceparam", serviceparam);
    }

    /**
     * 设置 [服务代码名称]
     */
    public void setServicecodename(String servicecodename) {
        this.servicecodename = servicecodename ;
        this.modify("servicecodename", servicecodename);
    }

    /**
     * 设置 [模型锁标志]
     */
    public void setLockflag(Integer lockflag) {
        this.lockflag = lockflag ;
        this.modify("lockflag", lockflag);
    }

    /**
     * 设置 [默认查询请求方式]
     */
    public void setDefselectreqmethod(String defselectreqmethod) {
        this.defselectreqmethod = defselectreqmethod ;
        this.modify("defselectreqmethod", defselectreqmethod);
    }

    /**
     * 设置 [服务参数2]
     */
    public void setServiceparam2(String serviceparam2) {
        this.serviceparam2 = serviceparam2 ;
        this.modify("serviceparam2", serviceparam2);
    }

    /**
     * 设置 [代码名称]
     */
    public void setCodename(String codename) {
        this.codename = codename ;
        this.modify("codename", codename);
    }

    /**
     * 设置 [认证参数4]
     */
    public void setAuthparam4(String authparam4) {
        this.authparam4 = authparam4 ;
        this.modify("authparam4", authparam4);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat", usercat);
    }

    /**
     * 设置 [认证模式]
     */
    public void setAuthmode(String authmode) {
        this.authmode = authmode ;
        this.modify("authmode", authmode);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag", usertag);
    }

    /**
     * 设置 [接口类型]
     */
    public void setApitype(String apitype) {
        this.apitype = apitype ;
        this.modify("apitype", apitype);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname", pssystemname);
    }

    /**
     * 设置 [认证参数2]
     */
    public void setAuthparam2(String authparam2) {
        this.authparam2 = authparam2 ;
        this.modify("authparam2", authparam2);
    }

    /**
     * 设置 [配置模型存储标识]
     */
    public void setCfgpsmodelstorageid(String cfgpsmodelstorageid) {
        this.cfgpsmodelstorageid = cfgpsmodelstorageid ;
        this.modify("cfgpsmodelstorageid", cfgpsmodelstorageid);
    }

    /**
     * 设置 [接口标记]
     */
    public void setApitag(String apitag) {
        this.apitag = apitag ;
        this.modify("apitag", apitag);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4", usertag4);
    }

    /**
     * 设置 [服务参数3]
     */
    public void setServiceparam3(String serviceparam3) {
        this.serviceparam3 = serviceparam3 ;
        this.modify("serviceparam3", serviceparam3);
    }

    /**
     * 设置 [默认结果集请求方式]
     */
    public void setDefdedatasetreqmethod(String defdedatasetreqmethod) {
        this.defdedatasetreqmethod = defdedatasetreqmethod ;
        this.modify("defdedatasetreqmethod", defdedatasetreqmethod);
    }

    /**
     * 设置 [唯一标识]
     */
    public void setUniquetag(String uniquetag) {
        this.uniquetag = uniquetag ;
        this.modify("uniquetag", uniquetag);
    }

    /**
     * 设置 [服务类型]
     */
    public void setServicetype(String servicetype) {
        this.servicetype = servicetype ;
        this.modify("servicetype", servicetype);
    }

    /**
     * 设置 [系统服务接口名称]
     */
    public void setPssysserviceapiname(String pssysserviceapiname) {
        this.pssysserviceapiname = pssysserviceapiname ;
        this.modify("pssysserviceapiname", pssysserviceapiname);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid", pssystemid);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3", usertag3);
    }

    /**
     * 设置 [是否启用]
     */
    public void setValidflag(Integer validflag) {
        this.validflag = validflag ;
        this.modify("validflag", validflag);
    }

    /**
     * 设置 [接口标记2]
     */
    public void setApitag2(String apitag2) {
        this.apitag2 = apitag2 ;
        this.modify("apitag2", apitag2);
    }

    /**
     * 设置 [认证token路径]
     */
    public void setAuthchecktokenuri(String authchecktokenuri) {
        this.authchecktokenuri = authchecktokenuri ;
        this.modify("authchecktokenuri", authchecktokenuri);
    }

    /**
     * 设置 [默认实体行为请求方式]
     */
    public void setDefdeactionreqmethod(String defdeactionreqmethod) {
        this.defdeactionreqmethod = defdeactionreqmethod ;
        this.modify("defdeactionreqmethod", defdeactionreqmethod);
    }

    /**
     * 设置 [认证参数]
     */
    public void setAuthparam(String authparam) {
        this.authparam = authparam ;
        this.modify("authparam", authparam);
    }

    /**
     * 设置 [认证参数3]
     */
    public void setAuthparam3(String authparam3) {
        this.authparam3 = authparam3 ;
        this.modify("authparam3", authparam3);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2", usertag2);
    }

    /**
     * 设置 [认证客户端密钥]
     */
    public void setAuthclientsecret(String authclientsecret) {
        this.authclientsecret = authclientsecret ;
        this.modify("authclientsecret", authclientsecret);
    }

    /**
     * 设置 [版本]
     */
    public void setVer(Integer ver) {
        this.ver = ver ;
        this.modify("ver", ver);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo", memo);
    }

    /**
     * 设置 [系统模块]
     */
    public void setPsmoduleid(String psmoduleid) {
        this.psmoduleid = psmoduleid ;
        this.modify("psmoduleid", psmoduleid);
    }

    /**
     * 复制当前对象数据到目标对象(粘贴重置)
     * @param targetEntity 目标数据对象
     * @param bIncEmpty  是否包括空值
     * @param <T>
     * @return
     */
    @Override
    public <T> T copyTo(T targetEntity, boolean bIncEmpty) {
        this.reset("pssysserviceapiid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


