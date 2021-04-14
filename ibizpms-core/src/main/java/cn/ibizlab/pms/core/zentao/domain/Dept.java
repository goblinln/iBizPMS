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
 * 实体[部门]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_dept", resultMap = "DeptResultMap")
@ApiModel("部门")
public class Dept extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 负责人
     */
    @TableField(value = "manager")
    @JSONField(name = "manager")
    @JsonProperty("manager")
    @ApiModelProperty("负责人")
    private String manager;
    /**
     * 无子部门
     */
    @TableField(exist = false)
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    @ApiModelProperty("无子部门")
    private String isleaf;
    /**
     * grade
     */
    @DEField(defaultValue = "0")
    @TableField(value = "grade")
    @JSONField(name = "grade")
    @JsonProperty("grade")
    @ApiModelProperty("grade")
    private Integer grade;
    /**
     * function
     */
    @TableField(value = "function")
    @JSONField(name = "function")
    @JsonProperty("function")
    @ApiModelProperty("function")
    private String function;
    /**
     * order
     */
    @DEField(defaultValue = "0")
    @TableField(value = "order")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("order")
    private Integer order;
    /**
     * path
     */
    @TableField(value = "path")
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("path")
    private String path;
    /**
     * position
     */
    @TableField(value = "position")
    @JSONField(name = "position")
    @JsonProperty("position")
    @ApiModelProperty("position")
    private String position;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 部门名称
     */
    @TableField(value = "name")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("部门名称")
    private String name;
    /**
     * 上级部门
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @ApiModelProperty("上级部门")
    private String parentname;
    /**
     * parent
     */
    @TableField(value = "parent")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("parent")
    private Long parent;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Dept ibizparent;



    /**
     * 设置 [负责人]
     */
    public void setManager(String manager) {
        this.manager = manager;
        this.modify("manager", manager);
    }

    /**
     * 设置 [grade]
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
        this.modify("grade", grade);
    }

    /**
     * 设置 [function]
     */
    public void setFunction(String function) {
        this.function = function;
        this.modify("function", function);
    }

    /**
     * 设置 [order]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [path]
     */
    public void setPath(String path) {
        this.path = path;
        this.modify("path", path);
    }

    /**
     * 设置 [position]
     */
    public void setPosition(String position) {
        this.position = position;
        this.modify("position", position);
    }

    /**
     * 设置 [部门名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [parent]
     */
    public void setParent(Long parent) {
        this.parent = parent;
        this.modify("parent", parent);
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


