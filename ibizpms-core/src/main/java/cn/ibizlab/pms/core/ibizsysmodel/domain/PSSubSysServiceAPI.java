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
 * ServiceApi [外部服务接口] 对象
 */
@Data
@ApiModel("外部服务接口")
public class PSSubSysServiceAPI extends EntityClient implements Serializable {

    /**
     * 外部服务接口名称
     */
    @JSONField(name = "pssubsysserviceapiname")
    @JsonProperty("pssubsysserviceapiname")
    @ApiModelProperty("外部服务接口名称")
    private String pssubsysserviceapiname;

    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;

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
     * 系统
     */
    @JSONField(name = "pssystemid")
    @JsonProperty("pssystemid")
    @ApiModelProperty("系统")
    private String pssystemid;

    /**
     * 外部服务接口标识
     */
    @DEField(isKeyField = true)
    @JSONField(name = "pssubsysserviceapiid")
    @JsonProperty("pssubsysserviceapiid")
    @ApiModelProperty("外部服务接口标识")
    private String pssubsysserviceapiid;

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
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 系统
     */
    @JSONField(name = "pssystemname")
    @JsonProperty("pssystemname")
    @ApiModelProperty("系统")
    private String pssystemname;

    /**
     * 系统服务接口
     */
    @JSONField(name = "pssysserviceapiname")
    @JsonProperty("pssysserviceapiname")
    @ApiModelProperty("系统服务接口")
    private String pssysserviceapiname;

    /**
     * 系统模块
     */
    @JSONField(name = "psmodulename")
    @JsonProperty("psmodulename")
    @ApiModelProperty("系统模块")
    private String psmodulename;

    /**
     * 系统模块
     */
    @JSONField(name = "psmoduleid")
    @JsonProperty("psmoduleid")
    @ApiModelProperty("系统模块")
    private String psmoduleid;

    /**
     * 系统服务接口
     */
    @JSONField(name = "pssysserviceapiid")
    @JsonProperty("pssysserviceapiid")
    @ApiModelProperty("系统服务接口")
    private String pssysserviceapiid;


    /**
     * 
     */
    @JSONField(name = "psmodule")
    @JsonProperty("psmodule")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule psmodule;

    /**
     * 
     */
    @JSONField(name = "pssysserviceapi")
    @JsonProperty("pssysserviceapi")
    private cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysServiceAPI pssysserviceapi;




    /**
     * 设置 [外部服务接口名称]
     */
    public void setPssubsysserviceapiname(String pssubsysserviceapiname) {
        this.pssubsysserviceapiname = pssubsysserviceapiname ;
        this.modify("pssubsysserviceapiname",pssubsysserviceapiname);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemid(String pssystemid) {
        this.pssystemid = pssystemid ;
        this.modify("pssystemid",pssystemid);
    }

    /**
     * 设置 [系统]
     */
    public void setPssystemname(String pssystemname) {
        this.pssystemname = pssystemname ;
        this.modify("pssystemname",pssystemname);
    }

    /**
     * 设置 [系统模块]
     */
    public void setPsmoduleid(String psmoduleid) {
        this.psmoduleid = psmoduleid ;
        this.modify("psmoduleid",psmoduleid);
    }

    /**
     * 设置 [系统服务接口]
     */
    public void setPssysserviceapiid(String pssysserviceapiid) {
        this.pssysserviceapiid = pssysserviceapiid ;
        this.modify("pssysserviceapiid",pssysserviceapiid);
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
        this.reset("pssubsysserviceapiid");
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


