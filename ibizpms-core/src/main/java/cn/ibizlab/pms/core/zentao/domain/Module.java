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
 * 实体[模块]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_module", resultMap = "ModuleResultMap")
@ApiModel("模块")
public class Module extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 所属根
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ROOT`")
    @JSONField(name = "root")
    @JsonProperty("root")
    @ApiModelProperty("所属根")
    private String root;
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
     * 类型
     */
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型")
    private String type;
    /**
     * 模块名称
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("模块名称")
    private String name;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ORDER`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;
    /**
     * 负责人
     */
    @DEField(defaultValue = "/")
    @TableField(value = "`OWNER`")
    @JSONField(name = "owner")
    @JsonProperty("owner")
    @ApiModelProperty("负责人")
    private String owner;
    /**
     * id
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 数据选择排序
     */
    @TableField(exist = false)
    @JSONField(name = "orderpk")
    @JsonProperty("orderpk")
    @ApiModelProperty("数据选择排序")
    private String orderpk;
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
     * 收藏者
     */
    @DEField(defaultValue = "/")
    @TableField(value = "`COLLECTOR`")
    @JSONField(name = "collector")
    @JsonProperty("collector")
    @ApiModelProperty("收藏者")
    private String collector;
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
     * 简称
     */
    @DEField(defaultValue = "/")
    @TableField(value = "`SHORT`")
    @JSONField(name = "ibizshort")
    @JsonProperty("ibizshort")
    @ApiModelProperty("简称")
    private String ibizshort;
    /**
     * 路径
     */
    @DEField(defaultValue = ",")
    @TableField(value = "`PATH`")
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("路径")
    private String path;
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
     * 组织标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORGID`")
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @ApiModelProperty("组织标识")
    private String orgid;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 上级模块
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @ApiModelProperty("上级模块")
    private String parentname;
    /**
     * 平台/分支
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`BRANCH`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @ApiModelProperty("平台/分支")
    private Long branch;
    /**
     * 上级模块
     */
    @TableField(value = "`PARENT`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("上级模块")
    private Long parent;
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
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`MDEPTNAME`")
    @JSONField(name = "mdeptname")
    @JsonProperty("mdeptname")
    @ApiModelProperty("归属部门名")
    private String mdeptname;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Branch ztBranchBranch;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Module ibizparent;



    /**
     * 设置 [所属根]
     */
    public void setRoot(String root) {
        this.root = root;
        this.modify("root", root);
    }

    /**
     * 设置 [级别]
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
        this.modify("grade", grade);
    }

    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [模块名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [负责人]
     */
    public void setOwner(String owner) {
        this.owner = owner;
        this.modify("owner", owner);
    }

    /**
     * 设置 [收藏者]
     */
    public void setCollector(String collector) {
        this.collector = collector;
        this.modify("collector", collector);
    }

    /**
     * 设置 [简称]
     */
    public void setIbizshort(String ibizshort) {
        this.ibizshort = ibizshort;
        this.modify("short", ibizshort);
    }

    /**
     * 设置 [路径]
     */
    public void setPath(String path) {
        this.path = path;
        this.modify("path", path);
    }

    /**
     * 设置 [平台/分支]
     */
    public void setBranch(Long branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [上级模块]
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


