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
 * 服务DTO对象[DeptDTO]
 */
@Data
@ApiModel("部门")
@JsonFilter(value = "dtofieldfilter")
public class DeptDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [MANAGER]
     *
     */
    @JSONField(name = "manager")
    @JsonProperty("manager")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("负责人")
    private String manager;
    /**
     * 属性 [ISLEAF]
     *
     */
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("无子部门")
    private String isleaf;
    /**
     * 属性 [GRADE]
     *
     */
    @JSONField(name = "grade")
    @JsonProperty("grade")
    @ApiModelProperty("grade")
    private Integer grade;
    /**
     * 属性 [FUNCTION]
     *
     */
    @JSONField(name = "function")
    @JsonProperty("function")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("function")
    private String function;
    /**
     * 属性 [ORDER]
     *
     */
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("order")
    private Integer order;
    /**
     * 属性 [PATH]
     *
     */
    @JSONField(name = "path")
    @JsonProperty("path")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("path")
    private String path;
    /**
     * 属性 [POSITION]
     *
     */
    @JSONField(name = "position")
    @JsonProperty("position")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("position")
    private String position;
    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;
    /**
     * 属性 [DEPTSN]
     *
     */
    @JSONField(name = "deptsn")
    @JsonProperty("deptsn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("部门编号")
    private Long deptsn;
    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[部门名称]不允许为空!")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("部门名称")
    private String name;
    /**
     * 属性 [PARENTNAME]
     *
     */
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("上级部门")
    private String parentname;
    /**
     * 属性 [PARENT]
     *
     */
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("parent")
    private Long parent;

    /**
     * 设置 [MANAGER]
     */
    public void setManager(String  manager){
        this.manager = manager ;
        this.modify("manager",manager);
    }

    /**
     * 设置 [GRADE]
     */
    public void setGrade(Integer  grade){
        this.grade = grade ;
        this.modify("grade",grade);
    }

    /**
     * 设置 [FUNCTION]
     */
    public void setFunction(String  function){
        this.function = function ;
        this.modify("function",function);
    }

    /**
     * 设置 [ORDER]
     */
    public void setOrder(Integer  order){
        this.order = order ;
        this.modify("order",order);
    }

    /**
     * 设置 [PATH]
     */
    public void setPath(String  path){
        this.path = path ;
        this.modify("path",path);
    }

    /**
     * 设置 [POSITION]
     */
    public void setPosition(String  position){
        this.position = position ;
        this.modify("position",position);
    }

    /**
     * 设置 [DEPTSN]
     */
    public void setDeptsn(Long  deptsn){
        this.deptsn = deptsn ;
        this.modify("deptsn",deptsn);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [PARENT]
     */
    public void setParent(Long  parent){
        this.parent = parent ;
        this.modify("parent",parent);
    }


}


