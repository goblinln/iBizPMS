package cn.ibizlab.pms.core.ibiz.domain;

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
 * 实体[系统更新功能]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_SYS_UPDATE_FEATURES", resultMap = "SysUpdateFeaturesResultMap")
@ApiModel("系统更新功能")
public class SysUpdateFeatures extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统更新功能名称
     */
    @DEField(name = "sys_update_featuresname")
    @TableField(value = "`sys_update_featuresname`")
    @JSONField(name = "sysupdatefeaturesname")
    @JsonProperty("sysupdatefeaturesname")
    @ApiModelProperty("系统更新功能名称")
    private String sysupdatefeaturesname;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`updatedate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`createman`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 更新类型
     */
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("更新类型")
    private String type;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createdate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 更新功能
     */
    @TableField(value = "`upfeatures`")
    @JSONField(name = "upfeatures")
    @JsonProperty("upfeatures")
    @ApiModelProperty("更新功能")
    private String upfeatures;
    /**
     * 系统更新功能标识
     */
    @DEField(name = "sys_update_featuresid", isKeyField = true)
    @TableId(value = "sys_update_featuresid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "sysupdatefeaturesid")
    @JsonProperty("sysupdatefeaturesid")
    @ApiModelProperty("系统更新功能标识")
    private String sysupdatefeaturesid;
    /**
     * 展示顺序
     */
    @TableField(value = "`displayorder`")
    @JSONField(name = "displayorder")
    @JsonProperty("displayorder")
    @ApiModelProperty("展示顺序")
    private Integer displayorder;
    /**
     * 功能描述
     */
    @TableField(value = "`featuresdesc`")
    @JSONField(name = "featuresdesc")
    @JsonProperty("featuresdesc")
    @ApiModelProperty("功能描述")
    private String featuresdesc;
    /**
     * 所属更新
     */
    @TableField(exist = false)
    @JSONField(name = "sysupdatelogname")
    @JsonProperty("sysupdatelogname")
    @ApiModelProperty("所属更新")
    private String sysupdatelogname;
    /**
     * 系统更新日志标识
     */
    @DEField(name = "sys_update_logid")
    @TableField(value = "`sys_update_logid`")
    @JSONField(name = "sysupdatelogid")
    @JsonProperty("sysupdatelogid")
    @ApiModelProperty("系统更新日志标识")
    private String sysupdatelogid;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog sysupdatelog;



    /**
     * 设置 [系统更新功能名称]
     */
    public void setSysupdatefeaturesname(String sysupdatefeaturesname) {
        this.sysupdatefeaturesname = sysupdatefeaturesname;
        this.modify("sys_update_featuresname", sysupdatefeaturesname);
    }

    /**
     * 设置 [更新类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [更新功能]
     */
    public void setUpfeatures(String upfeatures) {
        this.upfeatures = upfeatures;
        this.modify("upfeatures", upfeatures);
    }

    /**
     * 设置 [展示顺序]
     */
    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
        this.modify("displayorder", displayorder);
    }

    /**
     * 设置 [功能描述]
     */
    public void setFeaturesdesc(String featuresdesc) {
        this.featuresdesc = featuresdesc;
        this.modify("featuresdesc", featuresdesc);
    }

    /**
     * 设置 [系统更新日志标识]
     */
    public void setSysupdatelogid(String sysupdatelogid) {
        this.sysupdatelogid = sysupdatelogid;
        this.modify("sys_update_logid", sysupdatelogid);
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
        this.reset("sys_update_featuresid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


