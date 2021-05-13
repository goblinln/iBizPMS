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
 * 实体[文档库]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_doclib", resultMap = "DocLibResultMap")
@ApiModel("文档库")
public class DocLib extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文档类型
     */
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("文档类型")
    private String type;
    /**
     * 文件库类型
     */
    @TableField(exist = false)
    @JSONField(name = "doclibtype")
    @JsonProperty("doclibtype")
    @ApiModelProperty("文件库类型")
    private String doclibtype;
    /**
     * 是否收藏
     */
    @TableField(exist = false)
    @JSONField(name = "isfavourites")
    @JsonProperty("isfavourites")
    @ApiModelProperty("是否收藏")
    private String isfavourites;
    /**
     * 收藏者
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`COLLECTOR`")
    @JSONField(name = "collector")
    @JsonProperty("collector")
    @ApiModelProperty("收藏者")
    private String collector;
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
    @TableField(value = "`UPDATEMANNAME`")
    @JSONField(name = "updatemanname")
    @JsonProperty("updatemanname")
    @ApiModelProperty("由谁更新")
    private String updatemanname;
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
     * 权限
     */
    @DEField(defaultValue = "default")
    @TableField(value = "`ACL`")
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @ApiModelProperty("权限")
    private String acl;
    /**
     * Root
     */
    @TableField(exist = false)
    @JSONField(name = "root")
    @JsonProperty("root")
    @ApiModelProperty("Root")
    private String root;
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
     * 由谁更新
     */
    @TableField(value = "`UPDATEBY`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 分组
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`GROUPS`")
    @JSONField(name = "groups")
    @JsonProperty("groups")
    @ApiModelProperty("分组")
    private String groups;
    /**
     * 文档数量
     */
    @TableField(exist = false)
    @JSONField(name = "doccnt")
    @JsonProperty("doccnt")
    @ApiModelProperty("文档数量")
    private Integer doccnt;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`DEPTNAME`")
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @ApiModelProperty("归属部门名")
    private String deptname;
    /**
     * 用户
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`USERS`")
    @JSONField(name = "users")
    @JsonProperty("users")
    @ApiModelProperty("用户")
    private String users;
    /**
     * 是否是主库
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`MAIN`")
    @JSONField(name = "main")
    @JsonProperty("main")
    @ApiModelProperty("是否是主库")
    private String main;
    /**
     * 文档库名称
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("文档库名称")
    private String name;
    /**
     * 文件夹数
     */
    @TableField(exist = false)
    @JSONField(name = "modulecnt")
    @JsonProperty("modulecnt")
    @ApiModelProperty("文件夹数")
    private Integer modulecnt;
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
     * 创建时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "openeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    @ApiModelProperty("创建时间")
    private Timestamp openeddate;
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
     * 所属产品
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("所属产品")
    private String productname;
    /**
     * 所属项目
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @ApiModelProperty("所属项目")
    private String projectname;
    /**
     * 项目库
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PROJECT`")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目库")
    private Long project;
    /**
     * 产品库
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("产品库")
    private Long product;
    /**
     * 文档库编号
     */
    @TableField(value = "`DOCLIBSN`")
    @JSONField(name = "doclibsn")
    @JsonProperty("doclibsn")
    @ApiModelProperty("文档库编号")
    private Long doclibsn;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product ztProduct;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Project ztProject;



    /**
     * 设置 [文档类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [收藏者]
     */
    public void setCollector(String collector) {
        this.collector = collector;
        this.modify("collector", collector);
    }

    /**
     * 设置 [权限]
     */
    public void setAcl(String acl) {
        this.acl = acl;
        this.modify("acl", acl);
    }

    /**
     * 设置 [由谁更新]
     */
    public void setUpdateby(String updateby) {
        this.updateby = updateby;
        this.modify("updateby", updateby);
    }

    /**
     * 设置 [分组]
     */
    public void setGroups(String groups) {
        this.groups = groups;
        this.modify("groups", groups);
    }

    /**
     * 设置 [用户]
     */
    public void setUsers(String users) {
        this.users = users;
        this.modify("users", users);
    }

    /**
     * 设置 [是否是主库]
     */
    public void setMain(String main) {
        this.main = main;
        this.modify("main", main);
    }

    /**
     * 设置 [文档库名称]
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
     * 设置 [项目库]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
    }

    /**
     * 设置 [产品库]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [文档库编号]
     */
    public void setDoclibsn(Long doclibsn) {
        this.doclibsn = doclibsn;
        this.modify("doclibsn", doclibsn);
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


