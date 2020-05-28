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


import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;


/**
 * 实体[compile]
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "zt_compile",resultMap = "CompileResultMap")
public class Compile extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * queue
     */
    @TableField(value = "queue")
    @JSONField(name = "queue")
    @JsonProperty("queue")
    private Integer queue;
    /**
     * createdDate
     */
    @TableField(value = "createddate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    private Timestamp createddate;
    /**
     * atTime
     */
    @TableField(value = "attime")
    @JSONField(name = "attime")
    @JsonProperty("attime")
    private String attime;
    /**
     * updateDate
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "updatedate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;
    /**
     * id
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.UUID)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * tag
     */
    @TableField(value = "tag")
    @JSONField(name = "tag")
    @JsonProperty("tag")
    private String tag;
    /**
     * testtask
     */
    @TableField(value = "testtask")
    @JSONField(name = "testtask")
    @JsonProperty("testtask")
    private Integer testtask;
    /**
     * job
     */
    @TableField(value = "job")
    @JSONField(name = "job")
    @JsonProperty("job")
    private Integer job;
    /**
     * name
     */
    @TableField(value = "name")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * logs
     */
    @TableField(value = "logs")
    @JSONField(name = "logs")
    @JsonProperty("logs")
    private String logs;
    /**
     * status
     */
    @TableField(value = "status")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
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
     * createdBy
     */
    @TableField(value = "createdby")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    private String createdby;



    /**
     * 设置 [queue]
     */
    public void setQueue(Integer queue){
        this.queue = queue ;
        this.modify("queue",queue);
    }
    /**
     * 设置 [createdDate]
     */
    public void setCreateddate(Timestamp createddate){
        this.createddate = createddate ;
        this.modify("createddate",createddate);
    }
    /**
     * 设置 [atTime]
     */
    public void setAttime(String attime){
        this.attime = attime ;
        this.modify("attime",attime);
    }
    /**
     * 设置 [tag]
     */
    public void setTag(String tag){
        this.tag = tag ;
        this.modify("tag",tag);
    }
    /**
     * 设置 [testtask]
     */
    public void setTesttask(Integer testtask){
        this.testtask = testtask ;
        this.modify("testtask",testtask);
    }
    /**
     * 设置 [job]
     */
    public void setJob(Integer job){
        this.job = job ;
        this.modify("job",job);
    }
    /**
     * 设置 [name]
     */
    public void setName(String name){
        this.name = name ;
        this.modify("name",name);
    }
    /**
     * 设置 [logs]
     */
    public void setLogs(String logs){
        this.logs = logs ;
        this.modify("logs",logs);
    }
    /**
     * 设置 [status]
     */
    public void setStatus(String status){
        this.status = status ;
        this.modify("status",status);
    }
    /**
     * 设置 [createdBy]
     */
    public void setCreatedby(String createdby){
        this.createdby = createdby ;
        this.modify("createdby",createdby);
    }

}


