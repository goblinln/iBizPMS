package cn.ibizlab.pms.core.ibizpro.domain;

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
 * 实体[需求模块]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_module", resultMap = "IBZProStoryModuleResultMap")
@ApiModel("需求模块")
public class IBZProStoryModule extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 级别
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`GRADE`")
    @JSONField(name = "grade")
    @JsonProperty("grade")
    @ApiModelProperty("级别")
    private Integer grade;
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
     * owner
     */
    @DEField(defaultValue = "/")
    @TableField(value = "`OWNER`")
    @JSONField(name = "owner")
    @JsonProperty("owner")
    @ApiModelProperty("owner")
    private String owner;
    /**
     * 需求模块类型
     */
    @DEField(name = "ibiz_storytype")
    @TableField(value = "`IBIZ_STORYTYPE`")
    @JSONField(name = "ibiz_storytype")
    @JsonProperty("ibiz_storytype")
    @ApiModelProperty("需求模块类型")
    private String ibizStorytype;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 类型
     */
    @DEField(defaultValue = "story")
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型")
    private String type;
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
     * 名称
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("名称")
    private String name;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID)
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 路径
     */
    @DEField(defaultValue = "，")
    @TableField(value = "`PATH`")
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("路径")
    private String path;
    /**
     * IBIZ标识
     */
    @DEField(name = "ibiz_id")
    @TableField(value = "`IBIZ_ID`")
    @JSONField(name = "ibizid")
    @JsonProperty("ibizid")
    @ApiModelProperty("IBIZ标识")
    private String ibizid;
    /**
     * 产品
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("产品")
    private String productname;
    /**
     * 编号
     */
    @TableField(value = "`ROOT`")
    @JSONField(name = "root")
    @JsonProperty("root")
    @ApiModelProperty("编号")
    private Long root;
    /**
     * id
     */
    @TableField(value = "`PARENT`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("id")
    private Long parent;
    /**
     * 部门标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`MDEPTID`")
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    @ApiModelProperty("部门标识")
    private String mdeptid;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`MDEPTNAME`")
    @JSONField(name = "mdeptname")
    @JsonProperty("mdeptname")
    @ApiModelProperty("归属部门名")
    private String mdeptname;
    /**
     * 组织机构标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORGID`")
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @ApiModelProperty("组织机构标识")
    private String orgid;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`CREATEBY`")
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "`ORGNAME`")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`UPDATEBY`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 产品
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibizpro.domain.IBZProProduct ibzproproduct;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule parentmodule;



    /**
     * 设置 [级别]
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
        this.modify("grade", grade);
    }

    /**
     * 设置 [collector]
     */
    public void setCollector(String collector) {
        this.collector = collector;
        this.modify("collector", collector);
    }

    /**
     * 设置 [owner]
     */
    public void setOwner(String owner) {
        this.owner = owner;
        this.modify("owner", owner);
    }

    /**
     * 设置 [需求模块类型]
     */
    public void setIbizStorytype(String ibizStorytype) {
        this.ibizStorytype = ibizStorytype;
        this.modify("ibiz_storytype", ibizStorytype);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [简称]
     */
    public void setIbizshort(String ibizshort) {
        this.ibizshort = ibizshort;
        this.modify("short", ibizshort);
    }

    /**
     * 设置 [名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [路径]
     */
    public void setPath(String path) {
        this.path = path;
        this.modify("path", path);
    }

    /**
     * 设置 [IBIZ标识]
     */
    public void setIbizid(String ibizid) {
        this.ibizid = ibizid;
        this.modify("ibiz_id", ibizid);
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


