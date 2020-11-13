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
public class DynaDashboard extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态数据看板标识
     */
    @DEField(isKeyField = true)
    @TableId(value = "dynadashboardid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "dynadashboardid")
    @JsonProperty("dynadashboardid")
    private String dynadashboardid;
    /**
     * 动态数据看板名称
     */
    @TableField(value = "`dynadashboardname`")
    @JSONField(name = "dynadashboardname")
    @JsonProperty("dynadashboardname")
    private String dynadashboardname;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`createman`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`updatedate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createdate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;
    /**
     * 用户标识
     */
    @TableField(value = "`userid`")
    @JSONField(name = "userid")
    @JsonProperty("userid")
    private String userid;
    /**
     * 应用标识
     */
    @TableField(value = "`appid`")
    @JSONField(name = "appid")
    @JsonProperty("appid")
    private String appid;
    /**
     * 模型
     */
    @TableField(value = "`model`")
    @JSONField(name = "model")
    @JsonProperty("model")
    private String model;
    /**
     * 模型标识
     */
    @TableField(value = "`modelid`")
    @JSONField(name = "modelid")
    @JsonProperty("modelid")
    private String modelid;



    /**
     * 设置 [动态数据看板名称]
     */
    public void setDynadashboardname(String dynadashboardname) {
        this.dynadashboardname = dynadashboardname;
        this.modify("dynadashboardname", dynadashboardname);
    }

    /**
     * 设置 [用户标识]
     */
    public void setUserid(String userid) {
        this.userid = userid;
        this.modify("userid", userid);
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
     * 设置 [模型标识]
     */
    public void setModelid(String modelid) {
        this.modelid = modelid;
        this.modify("modelid", modelid);
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


