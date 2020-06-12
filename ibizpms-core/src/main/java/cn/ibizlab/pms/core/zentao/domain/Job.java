package cn.ibizlab.pms.core.zentao.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
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
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;

/**
 * 实体[job]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_job",resultMap = "JobResultMap")
public class Job extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 逻辑删除标志
     */
    @DEField(defaultValue = "0" , preType = DEPredefinedFieldType.LOGICVALID, logicval = "0" , logicdelval="1")
    @TableLogic(value= "0",delval="1")
    @TableField(value = "deleted")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * editedDate
     */
    @TableField(value = "editeddate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "editeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("editeddate")
    private Timestamp editeddate;
    /**
     * product
     */
    @TableField(value = "product")
    @JSONField(name = "product")
    @JsonProperty("product")
    private Integer product;
    /**
     * lastStatus
     */
    @TableField(value = "laststatus")
    @JSONField(name = "laststatus")
    @JsonProperty("laststatus")
    private String laststatus;
    /**
     * createdDate
     */
    @TableField(value = "createddate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    private Timestamp createddate;
    /**
     * createdBy
     */
    @TableField(value = "createdby")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    private String createdby;
    /**
     * triggerType
     */
    @TableField(value = "triggertype")
    @JSONField(name = "triggertype")
    @JsonProperty("triggertype")
    private String triggertype;
    /**
     * comment
     */
    @TableField(value = "comment")
    @JSONField(name = "comment")
    @JsonProperty("comment")
    private String comment;
    /**
     * lastExec
     */
    @TableField(value = "lastexec")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "lastexec" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastexec")
    private Timestamp lastexec;
    /**
     * jkHost
     */
    @TableField(value = "jkhost")
    @JSONField(name = "jkhost")
    @JsonProperty("jkhost")
    private Integer jkhost;
    /**
     * frame
     */
    @TableField(value = "frame")
    @JSONField(name = "frame")
    @JsonProperty("frame")
    private String frame;
    /**
     * atDay
     */
    @TableField(value = "atday")
    @JSONField(name = "atday")
    @JsonProperty("atday")
    private String atday;
    /**
     * jkJob
     */
    @TableField(value = "jkjob")
    @JSONField(name = "jkjob")
    @JsonProperty("jkjob")
    private String jkjob;
    /**
     * svnDir
     */
    @TableField(value = "svndir")
    @JSONField(name = "svndir")
    @JsonProperty("svndir")
    private String svndir;
    /**
     * repo
     */
    @TableField(value = "repo")
    @JSONField(name = "repo")
    @JsonProperty("repo")
    private Integer repo;
    /**
     * name
     */
    @TableField(value = "name")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * id
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * lastTag
     */
    @TableField(value = "lasttag")
    @JSONField(name = "lasttag")
    @JsonProperty("lasttag")
    private String lasttag;
    /**
     * atTime
     */
    @TableField(value = "attime")
    @JSONField(name = "attime")
    @JsonProperty("attime")
    private String attime;
    /**
     * editedBy
     */
    @TableField(value = "editedby")
    @JSONField(name = "editedby")
    @JsonProperty("editedby")
    private String editedby;



    /**
     * 设置 [editedDate]
     */
    public void setEditeddate(Timestamp editeddate){
        this.editeddate = editeddate ;
        this.modify("editeddate",editeddate);
    }
    /**
     * 设置 [product]
     */
    public void setProduct(Integer product){
        this.product = product ;
        this.modify("product",product);
    }
    /**
     * 设置 [lastStatus]
     */
    public void setLaststatus(String laststatus){
        this.laststatus = laststatus ;
        this.modify("laststatus",laststatus);
    }
    /**
     * 设置 [createdDate]
     */
    public void setCreateddate(Timestamp createddate){
        this.createddate = createddate ;
        this.modify("createddate",createddate);
    }
    /**
     * 设置 [createdBy]
     */
    public void setCreatedby(String createdby){
        this.createdby = createdby ;
        this.modify("createdby",createdby);
    }
    /**
     * 设置 [triggerType]
     */
    public void setTriggertype(String triggertype){
        this.triggertype = triggertype ;
        this.modify("triggertype",triggertype);
    }
    /**
     * 设置 [comment]
     */
    public void setComment(String comment){
        this.comment = comment ;
        this.modify("comment",comment);
    }
    /**
     * 设置 [lastExec]
     */
    public void setLastexec(Timestamp lastexec){
        this.lastexec = lastexec ;
        this.modify("lastexec",lastexec);
    }
    /**
     * 设置 [jkHost]
     */
    public void setJkhost(Integer jkhost){
        this.jkhost = jkhost ;
        this.modify("jkhost",jkhost);
    }
    /**
     * 设置 [frame]
     */
    public void setFrame(String frame){
        this.frame = frame ;
        this.modify("frame",frame);
    }
    /**
     * 设置 [atDay]
     */
    public void setAtday(String atday){
        this.atday = atday ;
        this.modify("atday",atday);
    }
    /**
     * 设置 [jkJob]
     */
    public void setJkjob(String jkjob){
        this.jkjob = jkjob ;
        this.modify("jkjob",jkjob);
    }
    /**
     * 设置 [svnDir]
     */
    public void setSvndir(String svndir){
        this.svndir = svndir ;
        this.modify("svndir",svndir);
    }
    /**
     * 设置 [repo]
     */
    public void setRepo(Integer repo){
        this.repo = repo ;
        this.modify("repo",repo);
    }
    /**
     * 设置 [name]
     */
    public void setName(String name){
        this.name = name ;
        this.modify("name",name);
    }
    /**
     * 设置 [lastTag]
     */
    public void setLasttag(String lasttag){
        this.lasttag = lasttag ;
        this.modify("lasttag",lasttag);
    }
    /**
     * 设置 [atTime]
     */
    public void setAttime(String attime){
        this.attime = attime ;
        this.modify("attime",attime);
    }
    /**
     * 设置 [editedBy]
     */
    public void setEditedby(String editedby){
        this.editedby = editedby ;
        this.modify("editedby",editedby);
    }

}


