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
 * 实体[网页钩子]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_webhook", resultMap = "WebHookResultMap")
@ApiModel("网页钩子")
public class WebHook extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * secret
     */
    @TableField(value = "`SECRET`")
    @JSONField(name = "secret")
    @JsonProperty("secret")
    @ApiModelProperty("secret")
    private String secret;
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
     * actions
     */
    @TableField(value = "`ACTIONS`")
    @JSONField(name = "actions")
    @JsonProperty("actions")
    @ApiModelProperty("actions")
    private String actions;
    /**
     * desc
     */
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("desc")
    private String desc;
    /**
     * contentType
     */
    @DEField(defaultValue = "application/json")
    @TableField(value = "`CONTENTTYPE`")
    @JSONField(name = "contenttype")
    @JsonProperty("contenttype")
    @ApiModelProperty("contentType")
    private String contenttype;
    /**
     * type
     */
    @DEField(defaultValue = "default")
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("type")
    private String type;
    /**
     * id
     */
    @DEField(defaultValue = "0", isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * editedBy
     */
    @TableField(value = "`EDITEDBY`")
    @JSONField(name = "editedby")
    @JsonProperty("editedby")
    @ApiModelProperty("editedBy")
    private String editedby;
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
     * name
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("name")
    private String name;
    /**
     * url
     */
    @TableField(value = "`URL`")
    @JSONField(name = "url")
    @JsonProperty("url")
    @ApiModelProperty("url")
    private String url;
    /**
     * domain
     */
    @TableField(value = "`DOMAIN`")
    @JSONField(name = "domain")
    @JsonProperty("domain")
    @ApiModelProperty("domain")
    private String domain;
    /**
     * createdBy
     */
    @TableField(value = "`CREATEDBY`")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    @ApiModelProperty("createdBy")
    private String createdby;
    /**
     * params
     */
    @TableField(value = "`PARAMS`")
    @JSONField(name = "params")
    @JsonProperty("params")
    @ApiModelProperty("params")
    private String params;
    /**
     * sendType
     */
    @DEField(dict = "Webhook__sendType")
    @TableField(value = "`SENDTYPE`")
    @JSONField(name = "sendtype")
    @JsonProperty("sendtype")
    @ApiModelProperty("sendType")
    private String sendtype;
    /**
     * products
     */
    @TableField(value = "`PRODUCTS`")
    @JSONField(name = "products")
    @JsonProperty("products")
    @ApiModelProperty("products")
    private String products;
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
     * projects
     */
    @TableField(value = "`PROJECTS`")
    @JSONField(name = "projects")
    @JsonProperty("projects")
    @ApiModelProperty("projects")
    private String projects;



    /**
     * 设置 [secret]
     */
    public void setSecret(String secret) {
        this.secret = secret;
        this.modify("secret", secret);
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
     * 设置 [actions]
     */
    public void setActions(String actions) {
        this.actions = actions;
        this.modify("actions", actions);
    }

    /**
     * 设置 [desc]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [contentType]
     */
    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
        this.modify("contenttype", contenttype);
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
     * 设置 [name]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [url]
     */
    public void setUrl(String url) {
        this.url = url;
        this.modify("url", url);
    }

    /**
     * 设置 [domain]
     */
    public void setDomain(String domain) {
        this.domain = domain;
        this.modify("domain", domain);
    }

    /**
     * 设置 [createdBy]
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
        this.modify("createdby", createdby);
    }

    /**
     * 设置 [params]
     */
    public void setParams(String params) {
        this.params = params;
        this.modify("params", params);
    }

    /**
     * 设置 [sendType]
     */
    public void setSendtype(String sendtype) {
        this.sendtype = sendtype;
        this.modify("sendtype", sendtype);
    }

    /**
     * 设置 [products]
     */
    public void setProducts(String products) {
        this.products = products;
        this.modify("products", products);
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
     * 设置 [projects]
     */
    public void setProjects(String projects) {
        this.projects = projects;
        this.modify("projects", projects);
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


