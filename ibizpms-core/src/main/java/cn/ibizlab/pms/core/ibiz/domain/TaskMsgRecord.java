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
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[待办消息记录]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_TASKMSGRECORD",resultMap = "TaskMsgRecordResultMap")
public class TaskMsgRecord extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 待办消息记录标识
     */
    @DEField(isKeyField=true)
    @TableId(value= "taskmsgrecordid",type=IdType.ASSIGN_UUID)
    @JSONField(name = "taskmsgrecordid")
    @JsonProperty("taskmsgrecordid")
    private String taskmsgrecordid;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "createman" , fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;
    /**
     * 待办消息记录名称
     */
    @TableField(value = "taskmsgrecordname")
    @JSONField(name = "taskmsgrecordname")
    @JsonProperty("taskmsgrecordname")
    private String taskmsgrecordname;
    /**
     * 逻辑有效标志
     */
    @DEField(preType = DEPredefinedFieldType.LOGICVALID, logicval = "1" , logicdelval="0")
    @TableLogic(value= "1",delval="0")
    @TableField(value = "enable")
    @JSONField(name = "enable")
    @JsonProperty("enable")
    private Integer enable;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "updatedate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "updateman")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "createdate" , fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;
    /**
     * 待办人标识
     */
    @TableField(value = "taskuserid")
    @JSONField(name = "taskuserid")
    @JsonProperty("taskuserid")
    private String taskuserid;
    /**
     * 第三方应用待办标识
     */
    @TableField(value = "apptaskid")
    @JSONField(name = "apptaskid")
    @JsonProperty("apptaskid")
    private String apptaskid;
    /**
     * 待办类型
     */
    @TableField(value = "tasktype")
    @JSONField(name = "tasktype")
    @JsonProperty("tasktype")
    private String tasktype;
    /**
     * 备注
     */
    @TableField(value = "memo")
    @JSONField(name = "memo")
    @JsonProperty("memo")
    private String memo;
    /**
     * 标题
     */
    @TableField(value = "title")
    @JSONField(name = "title")
    @JsonProperty("title")
    private String title;
    /**
     * 数据标识
     */
    @TableField(value = "dataid")
    @JSONField(name = "dataid")
    @JsonProperty("dataid")
    private String dataid;



    /**
     * 设置 [待办消息记录名称]
     */
    public void setTaskmsgrecordname(String taskmsgrecordname){
        this.taskmsgrecordname = taskmsgrecordname ;
        this.modify("taskmsgrecordname",taskmsgrecordname);
    }

    /**
     * 设置 [待办人标识]
     */
    public void setTaskuserid(String taskuserid){
        this.taskuserid = taskuserid ;
        this.modify("taskuserid",taskuserid);
    }

    /**
     * 设置 [第三方应用待办标识]
     */
    public void setApptaskid(String apptaskid){
        this.apptaskid = apptaskid ;
        this.modify("apptaskid",apptaskid);
    }

    /**
     * 设置 [待办类型]
     */
    public void setTasktype(String tasktype){
        this.tasktype = tasktype ;
        this.modify("tasktype",tasktype);
    }

    /**
     * 设置 [备注]
     */
    public void setMemo(String memo){
        this.memo = memo ;
        this.modify("memo",memo);
    }

    /**
     * 设置 [标题]
     */
    public void setTitle(String title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [数据标识]
     */
    public void setDataid(String dataid){
        this.dataid = dataid ;
        this.modify("dataid",dataid);
    }


}


