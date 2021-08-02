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
 * 实体[ImClient]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_im_client", resultMap = "ImClientResultMap")
@ApiModel("ImClient")
public class ImClient extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * strategy
     */
    @TableField(value = "`STRATEGY`")
    @JSONField(name = "strategy")
    @JsonProperty("strategy")
    @ApiModelProperty("strategy")
    private String strategy;
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
     * changeLog
     */
    @TableField(value = "`CHANGELOG`")
    @JSONField(name = "changelog")
    @JsonProperty("changelog")
    @ApiModelProperty("changeLog")
    private String changelog;
    /**
     * version
     */
    @TableField(value = "`VERSION`")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("version")
    private String version;
    /**
     * downloads
     */
    @TableField(value = "`DOWNLOADS`")
    @JSONField(name = "downloads")
    @JsonProperty("downloads")
    @ApiModelProperty("downloads")
    private String downloads;
    /**
     * status
     */
    @DEField(dict = "Im_client__status")
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("status")
    private String status;
    /**
     * desc
     */
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("desc")
    private String desc;
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
     * createdBy
     */
    @TableField(value = "`CREATEDBY`")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    @ApiModelProperty("createdBy")
    private String createdby;
    /**
     * editedBy
     */
    @TableField(value = "`EDITEDBY`")
    @JSONField(name = "editedby")
    @JsonProperty("editedby")
    @ApiModelProperty("editedBy")
    private String editedby;



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
     * 设置 [strategy]
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy;
        this.modify("strategy", strategy);
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
     * 设置 [changeLog]
     */
    public void setChangelog(String changelog) {
        this.changelog = changelog;
        this.modify("changelog", changelog);
    }

    /**
     * 设置 [version]
     */
    public void setVersion(String version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [downloads]
     */
    public void setDownloads(String downloads) {
        this.downloads = downloads;
        this.modify("downloads", downloads);
    }

    /**
     * 设置 [status]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [desc]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [createdBy]
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
        this.modify("createdby", createdby);
    }

    /**
     * 设置 [editedBy]
     */
    public void setEditedby(String editedby) {
        this.editedby = editedby;
        this.modify("editedby", editedby);
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


