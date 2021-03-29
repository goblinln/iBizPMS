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
 * 实体[任务统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_task", resultMap = "TaskStatsResultMap")
public class TaskStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务截至日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "taskdeadline", format = "yyyy-MM-dd")
    @JsonProperty("taskdeadline")
    private Timestamp taskdeadline;
    /**
     * 任务编号
     */
    @TableField(exist = false)
    @JSONField(name = "taskid")
    @JsonProperty("taskid")
    private Integer taskid;
    /**
     * 任务实际开始时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "taskrealstart", format = "yyyy-MM-dd")
    @JsonProperty("taskrealstart")
    private Timestamp taskrealstart;
    /**
     * 效率
     */
    @TableField(exist = false)
    @JSONField(name = "taskefficient")
    @JsonProperty("taskefficient")
    private String taskefficient;
    /**
     * 任务预计消耗
     */
    @TableField(exist = false)
    @JSONField(name = "taskestimate")
    @JsonProperty("taskestimate")
    private Integer taskestimate;
    /**
     * 结束
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    private Timestamp end;
    /**
     * 用户总消耗
     */
    @TableField(exist = false)
    @JSONField(name = "userconsumed")
    @JsonProperty("userconsumed")
    private Integer userconsumed;
    /**
     * 剩余总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    private Integer totalleft;
    /**
     * 项目总消耗
     */
    @TableField(exist = false)
    @JSONField(name = "projectconsumed")
    @JsonProperty("projectconsumed")
    private Integer projectconsumed;
    /**
     * 属性
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    private Timestamp begin;
    /**
     * 消耗总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    private Integer totalconsumed;
    /**
     * 预计总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    private Integer totalestimate;
    /**
     * 任务预计开始日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "taskeststarted", format = "yyyy-MM-dd")
    @JsonProperty("taskeststarted")
    private Timestamp taskeststarted;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 部门
     */
    @TableField(exist = false)
    @JSONField(name = "dept")
    @JsonProperty("dept")
    private String dept;
    /**
     * 名称
     */
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 项目名称
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    private String projectname;
    /**
     * 项目
     */
    @TableField(exist = false)
    @JSONField(name = "project")
    @JsonProperty("project")
    private String project;
    /**
     * 任务实际完成日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "taskfinisheddate", format = "yyyy-MM-dd")
    @JsonProperty("taskfinisheddate")
    private Timestamp taskfinisheddate;
    /**
     * 任务名称
     */
    @TableField(exist = false)
    @JSONField(name = "taskname")
    @JsonProperty("taskname")
    private String taskname;
    /**
     * 总任务数
     */
    @TableField(exist = false)
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    private Integer taskcnt;
    /**
     * 任务优先级
     */
    @TableField(exist = false)
    @JSONField(name = "taskpri")
    @JsonProperty("taskpri")
    private Integer taskpri;
    /**
     * 任务延期
     */
    @TableField(exist = false)
    @JSONField(name = "taskdelay")
    @JsonProperty("taskdelay")
    private String taskdelay;
    /**
     * 完成者
     */
    @TableField(exist = false)
    @JSONField(name = "finishedby")
    @JsonProperty("finishedby")
    private String finishedby;



    /**
     * 设置 [名称]
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


