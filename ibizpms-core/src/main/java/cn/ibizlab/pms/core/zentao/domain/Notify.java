package cn.ibizlab.pms.core.zentao.domain;

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
 * 实体[通知]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_notify", resultMap = "NotifyResultMap")
public class Notify extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * createdDate
     */
    @TableField(value = "`createddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    private Timestamp createddate;
    /**
     * ccList
     */
    @TableField(value = "`cclist`")
    @JSONField(name = "cclist")
    @JsonProperty("cclist")
    private String cclist;
    /**
     * failReason
     */
    @TableField(value = "`failreason`")
    @JSONField(name = "failreason")
    @JsonProperty("failreason")
    private String failreason;
    /**
     * action
     */
    @TableField(value = "`action`")
    @JSONField(name = "action")
    @JsonProperty("action")
    private Integer action;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * createdBy
     */
    @TableField(value = "`createdby`")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    private String createdby;
    /**
     * status
     */
    @DEField(defaultValue = "wait")
    @TableField(value = "`status`")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * subject
     */
    @TableField(value = "`subject`")
    @JSONField(name = "subject")
    @JsonProperty("subject")
    private String subject;
    /**
     * objectID
     */
    @TableField(value = "`objectid`")
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    private Integer objectid;
    /**
     * data
     */
    @TableField(value = "`data`")
    @JSONField(name = "data")
    @JsonProperty("data")
    private String data;
    /**
     * toList
     */
    @TableField(value = "`tolist`")
    @JSONField(name = "tolist")
    @JsonProperty("tolist")
    private String tolist;
    /**
     * sendTime
     */
    @TableField(value = "`sendtime`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "sendtime", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("sendtime")
    private Timestamp sendtime;
    /**
     * objectType
     */
    @TableField(value = "`objecttype`")
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    private String objecttype;



    /**
     * 设置 [createdDate]
     */
    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
        this.modify("createddate", createddate);
    }

    /**
     * 格式化日期 [createdDate]
     */
    public String formatCreateddate() {
        if (this.createddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(createddate);
    }
    /**
     * 设置 [ccList]
     */
    public void setCclist(String cclist) {
        this.cclist = cclist;
        this.modify("cclist", cclist);
    }

    /**
     * 设置 [failReason]
     */
    public void setFailreason(String failreason) {
        this.failreason = failreason;
        this.modify("failreason", failreason);
    }

    /**
     * 设置 [action]
     */
    public void setAction(Integer action) {
        this.action = action;
        this.modify("action", action);
    }

    /**
     * 设置 [createdBy]
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
        this.modify("createdby", createdby);
    }

    /**
     * 设置 [status]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [subject]
     */
    public void setSubject(String subject) {
        this.subject = subject;
        this.modify("subject", subject);
    }

    /**
     * 设置 [objectID]
     */
    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
        this.modify("objectid", objectid);
    }

    /**
     * 设置 [data]
     */
    public void setData(String data) {
        this.data = data;
        this.modify("data", data);
    }

    /**
     * 设置 [toList]
     */
    public void setTolist(String tolist) {
        this.tolist = tolist;
        this.modify("tolist", tolist);
    }

    /**
     * 设置 [sendTime]
     */
    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
        this.modify("sendtime", sendtime);
    }

    /**
     * 格式化日期 [sendTime]
     */
    public String formatSendtime() {
        if (this.sendtime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(sendtime);
    }
    /**
     * 设置 [objectType]
     */
    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
        this.modify("objecttype", objecttype);
    }


    @Override
    public Serializable getDefaultKey(boolean gen) {
        return IdWorker.getId();
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
        this.reset("id");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


