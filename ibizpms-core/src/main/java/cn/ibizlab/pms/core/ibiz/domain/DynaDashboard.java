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
 * 实体[动态数据看板]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_DYNADASHBOARD", resultMap = "DynaDashboardResultMap")
@ApiModel("动态数据看板")
public class DynaDashboard extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户标识
     */
    @TableField(value = "`USERID`")
    @JSONField(name = "userid")
    @JsonProperty("userid")
    @ApiModelProperty("用户标识")
    private String userid;
    /**
     * 动态数据看板标识
     */
    @DEField(isKeyField = true)
    @TableId(value = "`DYNADASHBOARDID`", type = IdType.ASSIGN_UUID)
    @JSONField(name = "dynadashboardid")
    @JsonProperty("dynadashboardid")
    @ApiModelProperty("动态数据看板标识")
    private String dynadashboardid;
    /**
     * 动态数据看板名称
     */
    @TableField(value = "`DYNADASHBOARDNAME`")
    @JSONField(name = "dynadashboardname")
    @JsonProperty("dynadashboardname")
    @ApiModelProperty("动态数据看板名称")
    private String dynadashboardname;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN, dict = "SysOperator")
    @TableField(value = "`CREATEMAN`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN, dict = "SysOperator")
    @TableField(value = "`UPDATEMAN`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 模型标识
     */
    @TableField(value = "`MODELID`")
    @JSONField(name = "modelid")
    @JsonProperty("modelid")
    @ApiModelProperty("模型标识")
    private String modelid;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`UPDATEDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 应用标识
     */
    @TableField(value = "`APPID`")
    @JSONField(name = "appid")
    @JsonProperty("appid")
    @ApiModelProperty("应用标识")
    private String appid;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`CREATEDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 模型
     */
    @TableField(value = "`MODEL`")
    @JSONField(name = "model")
    @JsonProperty("model")
    @ApiModelProperty("模型")
    private String model;



    /**
     * 设置 [用户标识]
     */
    public void setUserid(String userid) {
        this.userid = userid;
        this.modify("userid", userid);
    }

    /**
     * 设置 [动态数据看板名称]
     */
    public void setDynadashboardname(String dynadashboardname) {
        this.dynadashboardname = dynadashboardname;
        this.modify("dynadashboardname", dynadashboardname);
    }

    /**
     * 设置 [模型标识]
     */
    public void setModelid(String modelid) {
        this.modelid = modelid;
        this.modify("modelid", modelid);
    }

    /**
     * 设置 [应用标识]
     */
    public void setAppid(String appid) {
        this.appid = appid;
        this.modify("appid", appid);
    }

    /**
     * 设置 [模型]
     */
    public void setModel(String model) {
        this.model = model;
        this.modify("model", model);
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
        this.reset("dynadashboardid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


