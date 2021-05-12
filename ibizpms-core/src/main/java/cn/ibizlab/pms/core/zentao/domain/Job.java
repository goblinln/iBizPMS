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
 * 实体[job]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_job", resultMap = "JobResultMap")
@ApiModel("job")
public class Job extends EntityMP implements Serializable {

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
     * editedDate
     */
    @TableField(value = "`EDITEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "editeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("editeddate")
    @ApiModelProperty("editedDate")
    private Timestamp editeddate;
    /**
     * product
     */
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("product")
    private Integer product;
    /**
     * lastStatus
     */
    @TableField(value = "`LASTSTATUS`")
    @JSONField(name = "laststatus")
    @JsonProperty("laststatus")
    @ApiModelProperty("lastStatus")
    private String laststatus;
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
     * createdBy
     */
    @TableField(value = "`CREATEDBY`")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    @ApiModelProperty("createdBy")
    private String createdby;
    /**
     * triggerType
     */
    @TableField(value = "`TRIGGERTYPE`")
    @JSONField(name = "triggertype")
    @JsonProperty("triggertype")
    @ApiModelProperty("triggerType")
    private String triggertype;
    /**
     * comment
     */
    @TableField(value = "`COMMENT`")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("comment")
    private String comment;
    /**
     * lastExec
     */
    @TableField(value = "`LASTEXEC`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastexec", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastexec")
    @ApiModelProperty("lastExec")
    private Timestamp lastexec;
    /**
     * jkHost
     */
    @TableField(value = "`JKHOST`")
    @JSONField(name = "jkhost")
    @JsonProperty("jkhost")
    @ApiModelProperty("jkHost")
    private Integer jkhost;
    /**
     * frame
     */
    @TableField(value = "`FRAME`")
    @JSONField(name = "frame")
    @JsonProperty("frame")
    @ApiModelProperty("frame")
    private String frame;
    /**
     * atDay
     */
    @TableField(value = "`ATDAY`")
    @JSONField(name = "atday")
    @JsonProperty("atday")
    @ApiModelProperty("atDay")
    private String atday;
    /**
     * jkJob
     */
    @TableField(value = "`JKJOB`")
    @JSONField(name = "jkjob")
    @JsonProperty("jkjob")
    @ApiModelProperty("jkJob")
    private String jkjob;
    /**
     * svnDir
     */
    @TableField(value = "`SVNDIR`")
    @JSONField(name = "svndir")
    @JsonProperty("svndir")
    @ApiModelProperty("svnDir")
    private String svndir;
    /**
     * repo
     */
    @TableField(value = "`REPO`")
    @JSONField(name = "repo")
    @JsonProperty("repo")
    @ApiModelProperty("repo")
    private Integer repo;
    /**
     * name
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("name")
    private String name;
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
     * lastTag
     */
    @TableField(value = "`LASTTAG`")
    @JSONField(name = "lasttag")
    @JsonProperty("lasttag")
    @ApiModelProperty("lastTag")
    private String lasttag;
    /**
     * atTime
     */
    @TableField(value = "`ATTIME`")
    @JSONField(name = "attime")
    @JsonProperty("attime")
    @ApiModelProperty("atTime")
    private String attime;
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
     * 设置 [product]
     */
    public void setProduct(Integer product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [lastStatus]
     */
    public void setLaststatus(String laststatus) {
        this.laststatus = laststatus;
        this.modify("laststatus", laststatus);
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
     * 设置 [createdBy]
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
        this.modify("createdby", createdby);
    }

    /**
     * 设置 [triggerType]
     */
    public void setTriggertype(String triggertype) {
        this.triggertype = triggertype;
        this.modify("triggertype", triggertype);
    }

    /**
     * 设置 [comment]
     */
    public void setComment(String comment) {
        this.comment = comment;
        this.modify("comment", comment);
    }

    /**
     * 设置 [lastExec]
     */
    public void setLastexec(Timestamp lastexec) {
        this.lastexec = lastexec;
        this.modify("lastexec", lastexec);
    }

    /**
     * 格式化日期 [lastExec]
     */
    public String formatLastexec() {
        if (this.lastexec == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastexec);
    }
    /**
     * 设置 [jkHost]
     */
    public void setJkhost(Integer jkhost) {
        this.jkhost = jkhost;
        this.modify("jkhost", jkhost);
    }

    /**
     * 设置 [frame]
     */
    public void setFrame(String frame) {
        this.frame = frame;
        this.modify("frame", frame);
    }

    /**
     * 设置 [atDay]
     */
    public void setAtday(String atday) {
        this.atday = atday;
        this.modify("atday", atday);
    }

    /**
     * 设置 [jkJob]
     */
    public void setJkjob(String jkjob) {
        this.jkjob = jkjob;
        this.modify("jkjob", jkjob);
    }

    /**
     * 设置 [svnDir]
     */
    public void setSvndir(String svndir) {
        this.svndir = svndir;
        this.modify("svndir", svndir);
    }

    /**
     * 设置 [repo]
     */
    public void setRepo(Integer repo) {
        this.repo = repo;
        this.modify("repo", repo);
    }

    /**
     * 设置 [name]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [lastTag]
     */
    public void setLasttag(String lasttag) {
        this.lasttag = lasttag;
        this.modify("lasttag", lasttag);
    }

    /**
     * 设置 [atTime]
     */
    public void setAttime(String attime) {
        this.attime = attime;
        this.modify("attime", attime);
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


