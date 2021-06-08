package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[EmpLoyeeloadDTO]
 */
@Data
@ApiModel("员工负载表")
@JsonFilter(value = "dtofieldfilter")
public class EmpLoyeeloadDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("任务名")
    private String name;
    /**
     * 属性 [TASKCNT]
     *
     */
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    @ApiModelProperty("任务数")
    private Integer taskcnt;
    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("部门")
    private String dept;
    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("主键")
    private Long id;
    /**
     * 属性 [WORKDAY]
     *
     */
    @JSONField(name = "workday")
    @JsonProperty("workday")
    @ApiModelProperty("工作日天数")
    private Integer workday;
    /**
     * 属性 [TOTALTASKCNT]
     *
     */
    @JSONField(name = "totaltaskcnt")
    @JsonProperty("totaltaskcnt")
    @ApiModelProperty("总任务数")
    private Integer totaltaskcnt;
    /**
     * 属性 [ASSIGNEDTO]
     *
     */
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("指派给")
    private String assignedto;
    /**
     * 属性 [LEFT]
     *
     */
    @JSONField(name = "left")
    @JsonProperty("left")
    @ApiModelProperty("剩余工时")
    private Integer left;
    /**
     * 属性 [ASSIGN]
     *
     */
    @JSONField(name = "assign")
    @JsonProperty("assign")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("是否指派")
    private String assign;
    /**
     * 属性 [BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("属性")
    private Timestamp begin;
    /**
     * 属性 [TOTALLEFT]
     *
     */
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    @ApiModelProperty("总工时")
    private Integer totalleft;
    /**
     * 属性 [WORKLOAD]
     *
     */
    @JSONField(name = "workload")
    @JsonProperty("workload")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("工作负载")
    private String workload;
    /**
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束")
    private Timestamp end;
    /**
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("项目")
    private String projectname;
    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目编号")
    private Long project;

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [ASSIGNEDTO]
     */
    public void setAssignedto(String  assignedto){
        this.assignedto = assignedto ;
        this.modify("assignedto",assignedto);
    }

    /**
     * 设置 [ASSIGN]
     */
    public void setAssign(String  assign){
        this.assign = assign ;
        this.modify("assign",assign);
    }

    /**
     * 设置 [PROJECT]
     */
    public void setProject(Long  project){
        this.project = project ;
        this.modify("project",project);
    }


}


