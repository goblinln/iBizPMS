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
 * 实体[ImChat]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_im_chat", resultMap = "ImChatResultMap")
public class ImChat extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * admins
     */
    @TableField(value = "`admins`")
    @JSONField(name = "admins")
    @JsonProperty("admins")
    private String admins;
    /**
     * createdBy
     */
    @TableField(value = "`createdby`")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    private String createdby;
    /**
     * editedDate
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`editeddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "editeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("editeddate")
    private Timestamp editeddate;
    /**
     * dismissDate
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`dismissdate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "dismissdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("dismissdate")
    private Timestamp dismissdate;
    /**
     * createdDate
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`createddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    private Timestamp createddate;
    /**
     * committers
     */
    @TableField(value = "`committers`")
    @JSONField(name = "committers")
    @JsonProperty("committers")
    private String committers;
    /**
     * public
     */
    @DEField(name = "public")
    @TableField(value = "`public`")
    @JSONField(name = "ibizpublic")
    @JsonProperty("ibizpublic")
    private String ibizpublic;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * name
     */
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * type
     */
    @DEField(defaultValue = "group")
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * editedBy
     */
    @TableField(value = "`editedby`")
    @JSONField(name = "editedby")
    @JsonProperty("editedby")
    private String editedby;
    /**
     * subject
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`subject`")
    @JSONField(name = "subject")
    @JsonProperty("subject")
    private Integer subject;
    /**
     * lastActiveTime
     */
    @DEField(defaultValue = "0000-00-00 00:00:00")
    @TableField(value = "`lastactivetime`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastactivetime", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastactivetime")
    private Timestamp lastactivetime;
    /**
     * gid
     */
    @TableField(value = "`gid`")
    @JSONField(name = "gid")
    @JsonProperty("gid")
    private String gid;



    /**
     * 设置 [admins]
     */
    public void setAdmins(String admins) {
        this.admins = admins;
        this.modify("admins", admins);
    }

    /**
     * 设置 [createdBy]
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
        this.modify("createdby", createdby);
    }

    /**
     * 设置 [editedDate]
     */
    public void setEditeddate(Timestamp editeddate) {
        this.editeddate = editeddate;
        this.modify("editeddate", editeddate);
    }

    /**
     * 格式化日期 [editedDate]
     */
    public String formatEditeddate() {
        if (this.editeddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(editeddate);
    }
    /**
     * 设置 [dismissDate]
     */
    public void setDismissdate(Timestamp dismissdate) {
        this.dismissdate = dismissdate;
        this.modify("dismissdate", dismissdate);
    }

    /**
     * 格式化日期 [dismissDate]
     */
    public String formatDismissdate() {
        if (this.dismissdate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dismissdate);
    }
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
     * 设置 [committers]
     */
    public void setCommitters(String committers) {
        this.committers = committers;
        this.modify("committers", committers);
    }

    /**
     * 设置 [public]
     */
    public void setIbizpublic(String ibizpublic) {
        this.ibizpublic = ibizpublic;
        this.modify("public", ibizpublic);
    }

    /**
     * 设置 [name]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [type]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [editedBy]
     */
    public void setEditedby(String editedby) {
        this.editedby = editedby;
        this.modify("editedby", editedby);
    }

    /**
     * 设置 [subject]
     */
    public void setSubject(Integer subject) {
        this.subject = subject;
        this.modify("subject", subject);
    }

    /**
     * 设置 [lastActiveTime]
     */
    public void setLastactivetime(Timestamp lastactivetime) {
        this.lastactivetime = lastactivetime;
        this.modify("lastactivetime", lastactivetime);
    }

    /**
     * 格式化日期 [lastActiveTime]
     */
    public String formatLastactivetime() {
        if (this.lastactivetime == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastactivetime);
    }
    /**
     * 设置 [gid]
     */
    public void setGid(String gid) {
        this.gid = gid;
        this.modify("gid", gid);
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


