package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[SysUpdateFeaturesDTO]
 */
@Data
@ApiModel("系统更新功能")
@JsonFilter(value = "dtofieldfilter")
public class SysUpdateFeaturesDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [SYS_UPDATE_FEATURESNAME]
     *
     */
    @JSONField(name = "sysupdatefeaturesname")
    @JsonProperty("sysupdatefeaturesname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("系统更新功能名称")
    private String sysupdatefeaturesname;

    /**
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 属性 [UPDATEMAN]
     *
     */
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新类型")
    private String type;

    /**
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 属性 [UPFEATURES]
     *
     */
    @JSONField(name = "upfeatures")
    @JsonProperty("upfeatures")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("更新功能")
    private String upfeatures;

    /**
     * 属性 [SYS_UPDATE_FEATURESID]
     *
     */
    @JSONField(name = "sysupdatefeaturesid")
    @JsonProperty("sysupdatefeaturesid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统更新功能标识")
    private String sysupdatefeaturesid;

    /**
     * 属性 [DISPLAYORDER]
     *
     */
    @JSONField(name = "displayorder")
    @JsonProperty("displayorder")
    @ApiModelProperty("展示顺序")
    private Integer displayorder;

    /**
     * 属性 [FEATURESDESC]
     *
     */
    @JSONField(name = "featuresdesc")
    @JsonProperty("featuresdesc")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("功能描述")
    private String featuresdesc;

    /**
     * 属性 [SYS_UPDATE_LOGNAME]
     *
     */
    @JSONField(name = "sysupdatelogname")
    @JsonProperty("sysupdatelogname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("所属更新")
    private String sysupdatelogname;

    /**
     * 属性 [SYS_UPDATE_LOGID]
     *
     */
    @JSONField(name = "sysupdatelogid")
    @JsonProperty("sysupdatelogid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统更新日志标识")
    private String sysupdatelogid;


    /**
     * 设置 [SYS_UPDATE_FEATURESNAME]
     */
    public void setSysupdatefeaturesname(String  sysupdatefeaturesname){
        this.sysupdatefeaturesname = sysupdatefeaturesname ;
        this.modify("sys_update_featuresname",sysupdatefeaturesname);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [UPFEATURES]
     */
    public void setUpfeatures(String  upfeatures){
        this.upfeatures = upfeatures ;
        this.modify("upfeatures",upfeatures);
    }

    /**
     * 设置 [DISPLAYORDER]
     */
    public void setDisplayorder(Integer  displayorder){
        this.displayorder = displayorder ;
        this.modify("displayorder",displayorder);
    }

    /**
     * 设置 [FEATURESDESC]
     */
    public void setFeaturesdesc(String  featuresdesc){
        this.featuresdesc = featuresdesc ;
        this.modify("featuresdesc",featuresdesc);
    }

    /**
     * 设置 [SYS_UPDATE_LOGID]
     */
    public void setSysupdatelogid(String  sysupdatelogid){
        this.sysupdatelogid = sysupdatelogid ;
        this.modify("sys_update_logid",sysupdatelogid);
    }


}


