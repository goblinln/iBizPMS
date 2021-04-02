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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import cn.ibizlab.pms.util.domain.EntityClient;

/**
 * ServiceApi [外部接口实体] 对象
 */
@Data
@ApiModel("外部接口实体")
public class PSSubSysSADE extends EntityClient implements Serializable {
    @Override
    public void modify(String field, Object val) {
        getExtensionparams().put("dirtyflagenable", true);
        super.modify(field, val);
    }

    /**
     * 同步模型模式
     */
    @DEField(defaultValue = "0")
    @JSONField(name = "syncmodelmode")
    @JsonProperty("syncmodelmode")
    @ApiModelProperty("同步模型模式")
    private String syncmodelmode;

    /**
     * 代码名称2
     */
    @JSONField(name = "codename2")
    @JsonProperty("codename2")
    @ApiModelProperty("代码名称2")
    private String codename2;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 外部接口实体标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "pssubsyssadeid")
    @JsonProperty("pssubsyssadeid")
    @ApiModelProperty("外部接口实体标识")
    private String pssubsyssadeid;

    /**
     * 用户标记
     */
    @JSONField(name = "usertag")
    @JsonProperty("usertag")
    @ApiModelProperty("用户标记")
    private String usertag;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 备注
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @ApiModelProperty("备注")
    private String memo;

    /**
     * 接口模式
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "majorflag")
    @JsonProperty("majorflag")
    @ApiModelProperty("接口模式")
    private Integer majorflag;

    /**
     * 实体标记
     */
    @JSONField(name = "detag")
    @JsonProperty("detag")
    @ApiModelProperty("实体标记")
    private String detag;

    /**
     * 用户标记4
     */
    @JSONField(name = "usertag4")
    @JsonProperty("usertag4")
    @ApiModelProperty("用户标记4")
    private String usertag4;

    /**
     * 用户标记2
     */
    @JSONField(name = "usertag2")
    @JsonProperty("usertag2")
    @ApiModelProperty("用户标记2")
    private String usertag2;

    /**
     * 用户分类
     */
    @JSONField(name = "usercat")
    @JsonProperty("usercat")
    @ApiModelProperty("用户分类")
    private String usercat;

    /**
     * 是否启用
     */
    @DEField(defaultValue = "1")
    @JSONField(name = "validflag")
    @JsonProperty("validflag")
    @ApiModelProperty("是否启用")
    private Integer validflag;

    /**
     * 外部接口实体名称
     */
    @JSONField(name = "pssubsyssadename")
    @JsonProperty("pssubsyssadename")
    @ApiModelProperty("外部接口实体名称")
    private String pssubsyssadename;

    /**
     * 用户标记3
     */
    @JSONField(name = "usertag3")
    @JsonProperty("usertag3")
    @ApiModelProperty("用户标记3")
    private String usertag3;

    /**
     * 实体标记2
     */
    @JSONField(name = "detag2")
    @JsonProperty("detag2")
    @ApiModelProperty("实体标记2")
    private String detag2;

    /**
     * 逻辑名称
     */
    @JSONField(name = "logicname")
    @JsonProperty("logicname")
    @ApiModelProperty("逻辑名称")
    private String logicname;

    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 代码名称
     */
    @JSONField(name = "codename")
    @JsonProperty("codename")
    @ApiModelProperty("代码名称")
    private String codename;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate" , format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 子系统接口
     */
    @JSONField(name = "pssubsysserviceapiname")
    @JsonProperty("pssubsysserviceapiname")
    @ApiModelProperty("子系统接口")
    private String pssubsysserviceapiname;

    /**
     * 子系统接口
     */
    @JSONField(name = "pssubsysserviceapiid")
    @JsonProperty("pssubsysserviceapiid")
    @ApiModelProperty("子系统接口")
    private String pssubsysserviceapiid;


    /**
     * 
     */
    @JSONField(name = "pssubsysserviceapi")
    @JsonProperty("pssubsysserviceapi")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysServiceAPI pssubsysserviceapi;




    /**
     * 设置 [同步模型模式]
     */
    public void setSyncmodelmode(String syncmodelmode) {
        this.syncmodelmode = syncmodelmode ;
        this.modify("syncmodelmode",syncmodelmode);
    }

    /**
     * 设置 [代码名称2]
     */
    public void setCodename2(String codename2) {
        this.codename2 = codename2 ;
        this.modify("codename2",codename2);
    }

    /**
     * 设置 [用户标记]
     */
    public void setUsertag(String usertag) {
        this.usertag = usertag ;
        this.modify("usertag",usertag);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo) {
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [接口模式]
     */
    public void setMajorflag(Integer majorflag) {
        this.majorflag = majorflag ;
        this.modify("majorflag",majorflag);
    }

    /**
     * 设置 [实体标记]
     */
    public void setDetag(String detag) {
        this.detag = detag ;
        this.modify("detag",detag);
    }

    /**
     * 设置 [用户标记4]
     */
    public void setUsertag4(String usertag4) {
        this.usertag4 = usertag4 ;
        this.modify("usertag4",usertag4);
    }

    /**
     * 设置 [用户标记2]
     */
    public void setUsertag2(String usertag2) {
        this.usertag2 = usertag2 ;
        this.modify("usertag2",usertag2);
    }

    /**
     * 设置 [用户分类]
     */
    public void setUsercat(String usercat) {
        this.usercat = usercat ;
        this.modify("usercat",usercat);
    }

    /**
     * 设置 [是否启用]
     */
    public void setValidflag(Integer validflag) {
        this.validflag = validflag ;
        this.modify("validflag",validflag);
    }

    /**
     * 设置 [外部接口实体名称]
     */
    public void setPssubsyssadename(String pssubsyssadename) {
        this.pssubsyssadename = pssubsyssadename ;
        this.modify("pssubsyssadename",pssubsyssadename);
    }

    /**
     * 设置 [用户标记3]
     */
    public void setUsertag3(String usertag3) {
        this.usertag3 = usertag3 ;
        this.modify("usertag3",usertag3);
    }

    /**
     * 设置 [实体标记2]
     */
    public void setDetag2(String detag2) {
        this.detag2 = detag2 ;
        this.modify("detag2",detag2);
    }

    /**
     * 设置 [逻辑名称]
     */
    public void setLogicname(String logicname) {
        this.logicname = logicname ;
        this.modify("logicname",logicname);
    }

    /**
     * 设置 [代码名称]
     */
    public void setCodename(String codename) {
        this.codename = codename ;
        this.modify("codename",codename);
    }

    /**
     * 设置 [子系统接口]
     */
    public void setPssubsysserviceapiid(String pssubsysserviceapiid) {
        this.pssubsysserviceapiid = pssubsysserviceapiid ;
        this.modify("pssubsysserviceapiid",pssubsysserviceapiid);
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
        this.reset("pssubsyssadeid");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


