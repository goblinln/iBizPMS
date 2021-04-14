package cn.ibizlab.pms.core.ibizpro.domain;

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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[系统配置表]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZPRO_CONFIG", resultMap = "IbzproConfigResultMap")
@ApiModel("系统配置表")
public class IbzproConfig extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "updateman")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 系统配置表名称
     */
    @DEField(name = "ibzpro_configname")
    @TableField(value = "ibzpro_configname")
    @JSONField(name = "ibzproconfigname")
    @JsonProperty("ibzproconfigname")
    @ApiModelProperty("系统配置表名称")
    private String ibzproconfigname;
    /**
     * 描述
     */
    @TableField(value = "memo")
    @JSONField(name = "memo")
    @JsonProperty("memo")
    @ApiModelProperty("描述")
    private String memo;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "updatedate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "createdate", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 系统配置表标识
     */
    @DEField(name = "ibzpro_configid", isKeyField = true)
    @TableId(value = "ibzpro_configid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "ibzproconfigid")
    @JsonProperty("ibzproconfigid")
    @ApiModelProperty("系统配置表标识")
    private String ibzproconfigid;
    /**
     * 范围
     */
    @TableField(value = "scope")
    @JSONField(name = "scope")
    @JsonProperty("scope")
    @ApiModelProperty("范围")
    private String scope;
    /**
     * 类型
     */
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型")
    private String type;
    /**
     * 管理现状
     */
    @TableField(value = "managementstatus")
    @JSONField(name = "managementstatus")
    @JsonProperty("managementstatus")
    @ApiModelProperty("管理现状")
    private String managementstatus;
    /**
     * 是否启用
     */
    @TableField(value = "vaild")
    @JSONField(name = "vaild")
    @JsonProperty("vaild")
    @ApiModelProperty("是否启用")
    private String vaild;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "createman", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;



    /**
     * 设置 [系统配置表名称]
     */
    public void setIbzproconfigname(String ibzproconfigname) {
        this.ibzproconfigname = ibzproconfigname;
        this.modify("ibzpro_configname", ibzproconfigname);
    }

    /**
     * 设置 [描述]
     */
    public void setMemo(String memo) {
        this.memo = memo;
        this.modify("memo", memo);
    }

    /**
     * 设置 [范围]
     */
    public void setScope(String scope) {
        this.scope = scope;
        this.modify("scope", scope);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [管理现状]
     */
    public void setManagementstatus(String managementstatus) {
        this.managementstatus = managementstatus;
        this.modify("managementstatus", managementstatus);
    }

    /**
     * 设置 [是否启用]
     */
    public void setVaild(String vaild) {
        this.vaild = vaild;
        this.modify("vaild", vaild);
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
        this.reset("ibzpro_configid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


