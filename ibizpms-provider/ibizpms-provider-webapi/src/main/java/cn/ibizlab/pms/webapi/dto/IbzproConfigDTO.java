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
 * 服务DTO对象[IbzproConfigDTO]
 */
@Data
@ApiModel("系统配置表")
@JsonFilter(value = "dtofieldfilter")
public class IbzproConfigDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

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
     * 属性 [IBZPRO_CONFIGNAME]
     *
     */
    @JSONField(name = "ibzproconfigname")
    @JsonProperty("ibzproconfigname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("系统配置表名称")
    private String ibzproconfigname;
    /**
     * 属性 [MEMO]
     *
     */
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("描述")
    private String memo;
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
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 属性 [IBZPRO_CONFIGID]
     *
     */
    @JSONField(name = "ibzproconfigid")
    @JsonProperty("ibzproconfigid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("系统配置表标识")
    private String ibzproconfigid;
    /**
     * 属性 [SCOPE]
     *
     */
    @JSONField(name = "scope")
    @JsonProperty("scope")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("范围")
    private String scope;
    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("类型")
    private String type;
    /**
     * 属性 [MANAGEMENTSTATUS]
     *
     */
    @JSONField(name = "managementstatus")
    @JsonProperty("managementstatus")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("管理现状")
    private String managementstatus;
    /**
     * 属性 [VAILD]
     *
     */
    @JSONField(name = "vaild")
    @JsonProperty("vaild")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("是否启用")
    private String vaild;
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
     * 设置 [IBZPRO_CONFIGNAME]
     */
    public void setIbzproconfigname(String  ibzproconfigname){
        this.ibzproconfigname = ibzproconfigname ;
        this.modify("ibzpro_configname",ibzproconfigname);
    }

    /**
     * 设置 [MEMO]
     */
    public void setMemo(String  memo){
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [SCOPE]
     */
    public void setScope(String  scope){
        this.scope = scope ;
        this.modify("scope",scope);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [MANAGEMENTSTATUS]
     */
    public void setManagementstatus(String  managementstatus){
        this.managementstatus = managementstatus ;
        this.modify("managementstatus",managementstatus);
    }

    /**
     * 设置 [VAILD]
     */
    public void setVaild(String  vaild){
        this.vaild = vaild ;
        this.modify("vaild",vaild);
    }


}


