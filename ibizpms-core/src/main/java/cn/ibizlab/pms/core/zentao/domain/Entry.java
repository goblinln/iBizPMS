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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[entry]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_entry", resultMap = "EntryResultMap")
@ApiModel("entry")
public class Entry extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 逻辑删除标志
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("逻辑删除标志")
    private String deleted;
    /**
     * code
     */
    @TableField(value = "`CODE`")
    @JSONField(name = "code")
    @JsonProperty("code")
    @ApiModelProperty("code")
    private String code;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * editedDate
     */
    @TableField(value = "`EDITEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "editeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("editeddate")
    @ApiModelProperty("editedDate")
    private Timestamp editeddate;
    /**
     * createdDate
     */
    @TableField(value = "`CREATEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    @ApiModelProperty("createdDate")
    private Timestamp createddate;
    /**
     * freePasswd
     */
    @DEField(dict = "Action__read")
    @TableField(value = "`FREEPASSWD`")
    @JSONField(name = "freepasswd")
    @JsonProperty("freepasswd")
    @ApiModelProperty("freePasswd")
    private String freepasswd;
    /**
     * createdBy
     */
    @TableField(value = "`CREATEDBY`")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    @ApiModelProperty("createdBy")
    private String createdby;
    /**
     * account
     */
    @TableField(value = "`ACCOUNT`")
    @JSONField(name = "account")
    @JsonProperty("account")
    @ApiModelProperty("account")
    private String account;
    /**
     * calledTime
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`CALLEDTIME`")
    @JSONField(name = "calledtime")
    @JsonProperty("calledtime")
    @ApiModelProperty("calledTime")
    private Integer calledtime;
    /**
     * key
     */
    @TableField(value = "`KEY`")
    @JSONField(name = "key")
    @JsonProperty("key")
    @ApiModelProperty("key")
    private String key;
    /**
     * editedBy
     */
    @TableField(value = "`EDITEDBY`")
    @JSONField(name = "editedby")
    @JsonProperty("editedby")
    @ApiModelProperty("editedBy")
    private String editedby;
    /**
     * ip
     */
    @TableField(value = "`IP`")
    @JSONField(name = "ip")
    @JsonProperty("ip")
    @ApiModelProperty("ip")
    private String ip;
    /**
     * desc
     */
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("desc")
    private String desc;
    /**
     * name
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("name")
    private String name;



    /**
     * 设置 [code]
     */
    public void setCode(String code) {
        this.code = code;
        this.modify("code", code);
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
     * 设置 [freePasswd]
     */
    public void setFreepasswd(String freepasswd) {
        this.freepasswd = freepasswd;
        this.modify("freepasswd", freepasswd);
    }

    /**
     * 设置 [createdBy]
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
        this.modify("createdby", createdby);
    }

    /**
     * 设置 [account]
     */
    public void setAccount(String account) {
        this.account = account;
        this.modify("account", account);
    }

    /**
     * 设置 [calledTime]
     */
    public void setCalledtime(Integer calledtime) {
        this.calledtime = calledtime;
        this.modify("calledtime", calledtime);
    }

    /**
     * 设置 [key]
     */
    public void setKey(String key) {
        this.key = key;
        this.modify("key", key);
    }

    /**
     * 设置 [editedBy]
     */
    public void setEditedby(String editedby) {
        this.editedby = editedby;
        this.modify("editedby", editedby);
    }

    /**
     * 设置 [ip]
     */
    public void setIp(String ip) {
        this.ip = ip;
        this.modify("ip", ip);
    }

    /**
     * 设置 [desc]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [name]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
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


