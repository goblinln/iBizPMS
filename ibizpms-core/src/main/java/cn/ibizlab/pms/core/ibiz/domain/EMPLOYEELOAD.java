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
 * 实体[员工负载表]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "ZT_TASK",resultMap = "EMPLOYEELOADResultMap")
public class EMPLOYEELOAD extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 任务名
     */
    @TableField(value = "`name`")
    @JSONField(name = "name")
    @JsonProperty("name")
    private String name;
    /**
     * 任务数
     */
    @TableField(exist = false)
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    private Integer taskcnt;
    /**
     * 项目编号
     */
    @TableField(value = "`project`")
    @JSONField(name = "project")
    @JsonProperty("project")
    private Long project;
    /**
     * 项目
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    private String projectname;
    /**
     * 总任务数
     */
    @TableField(exist = false)
    @JSONField(name = "totaltaskcnt")
    @JsonProperty("totaltaskcnt")
    private Integer totaltaskcnt;
    /**
     * 总工时
     */
    @TableField(exist = false)
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    private Integer totalleft;
    /**
     * 剩余工时
     */
    @TableField(exist = false)
    @JSONField(name = "left")
    @JsonProperty("left")
    private Integer left;
    /**
     * 工作负载
     */
    @TableField(exist = false)
    @JSONField(name = "workload")
    @JsonProperty("workload")
    private String workload;
    /**
     * 指派给
     */
    @TableField(value = "`assignedto`")
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    private String assignedto;
    /**
     * 部门
     */
    @TableField(exist = false)
    @JSONField(name = "dept")
    @JsonProperty("dept")
    private String dept;
    /**
     * 属性
     */
    @TableField(exist = false)
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    private Timestamp begin;
    /**
     * 结束
     */
    @TableField(exist = false)
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    private Timestamp end;
    /**
     * 工作日天数
     */
    @TableField(exist = false)
    @JSONField(name = "workday")
    @JsonProperty("workday")
    private Integer workday;
    /**
     * 是否指派
     */
    @TableField(value = "`assign`")
    @JSONField(name = "assign")
    @JsonProperty("assign")
    private String assign;

    /**
     * 项目
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Project ztproject;



    /**
     * 设置 [任务名]
     */
    public void setName(String name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [项目编号]
     */
    public void setProject(Long project){
        this.project = project ;
        this.modify("project",project);
    }

    /**
     * 设置 [指派给]
     */
    public void setAssignedto(String assignedto){
        this.assignedto = assignedto ;
        this.modify("assignedto",assignedto);
    }

    /**
     * 设置 [是否指派]
     */
    public void setAssign(String assign){
        this.assign = assign ;
        this.modify("assign",assign);
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
        return super.copyTo(targetEntity,bIncEmpty);
    }
}


