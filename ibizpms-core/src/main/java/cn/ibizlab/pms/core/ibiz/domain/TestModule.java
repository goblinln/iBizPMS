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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[测试模块]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_module", resultMap = "TestModuleResultMap")
@ApiModel("测试模块")
public class TestModule extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型（story）
     */
    @DEField(defaultValue = "story")
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型（story）")
    private String type;
    /**
     * path
     */
    @DEField(defaultValue = ",")
    @TableField(value = "`PATH`")
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("path")
    private String path;
    /**
     * owner
     */
    @DEField(defaultValue = "/")
    @TableField(value = "`OWNER`")
    @JSONField(name = "owner")
    @JsonProperty("owner")
    @ApiModelProperty("owner")
    private String owner;
    /**
     * 排序值
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ORDER`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序值")
    private Integer order;
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
     * branch
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`BRANCH`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @ApiModelProperty("branch")
    private Integer branch;
    /**
     * collector
     */
    @DEField(defaultValue = "/")
    @TableField(value = "`COLLECTOR`")
    @JSONField(name = "collector")
    @JsonProperty("collector")
    @ApiModelProperty("collector")
    private String collector;
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
     * 叶子模块
     */
    @TableField(exist = false)
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    @ApiModelProperty("叶子模块")
    private String isleaf;
    /**
     * 名称
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("名称")
    private String name;
    /**
     * 简称
     */
    @DEField(defaultValue = "/")
    @TableField(value = "`SHORT`")
    @JSONField(name = "ibizshort")
    @JsonProperty("ibizshort")
    @ApiModelProperty("简称")
    private String ibizshort;
    /**
     * grade
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`GRADE`")
    @JSONField(name = "grade")
    @JsonProperty("grade")
    @ApiModelProperty("grade")
    private Integer grade;
    /**
     * 上级模块
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @ApiModelProperty("上级模块")
    private String parentname;
    /**
     * 测试
     */
    @TableField(exist = false)
    @JSONField(name = "rootname")
    @JsonProperty("rootname")
    @ApiModelProperty("测试")
    private String rootname;
    /**
     * 编号
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ROOT`")
    @JSONField(name = "root")
    @JsonProperty("root")
    @ApiModelProperty("编号")
    private Long root;
    /**
     * id
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PARENT`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("id")
    private Long parent;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibiz.domain.TestModule parentmodule;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product roottest;



    /**
     * 设置 [类型（story）]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [path]
     */
    public void setPath(String path) {
        this.path = path;
        this.modify("path", path);
    }

    /**
     * 设置 [owner]
     */
    public void setOwner(String owner) {
        this.owner = owner;
        this.modify("owner", owner);
    }

    /**
     * 设置 [排序值]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [branch]
     */
    public void setBranch(Integer branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [collector]
     */
    public void setCollector(String collector) {
        this.collector = collector;
        this.modify("collector", collector);
    }

    /**
     * 设置 [名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [简称]
     */
    public void setIbizshort(String ibizshort) {
        this.ibizshort = ibizshort;
        this.modify("short", ibizshort);
    }

    /**
     * 设置 [grade]
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
        this.modify("grade", grade);
    }

    /**
     * 设置 [编号]
     */
    public void setRoot(Long root) {
        this.root = root;
        this.modify("root", root);
    }

    /**
     * 设置 [id]
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


