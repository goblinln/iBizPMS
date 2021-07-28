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
 * 实体[文档]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_doc", resultMap = "DocResultMap")
@ApiModel("文档")
public class Doc extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组
     */
    @TableField(value = "groups")
    @JSONField(name = "groups")
    @JsonProperty("groups")
    @ApiModelProperty("分组")
    private String groups;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "deptname")
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @ApiModelProperty("归属部门名")
    private String deptname;
    /**
     * 文档正文
     */
    @TableField(exist = false)
    @JSONField(name = "content")
    @JsonProperty("content")
    @ApiModelProperty("文档正文")
    private String content;
    /**
     * 文档查询类型
     */
    @TableField(exist = false)
    @JSONField(name = "docqtype")
    @JsonProperty("docqtype")
    @ApiModelProperty("文档查询类型")
    private String docqtype;
    /**
     * 更新时间
     */
    @TableField(value = "editeddate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "editeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("editeddate")
    @ApiModelProperty("更新时间")
    private Timestamp editeddate;
    /**
     * views
     */
    @TableField(value = "views")
    @JSONField(name = "views")
    @JsonProperty("views")
    @ApiModelProperty("views")
    private Integer views;
    /**
     * 版本号
     */
    @DEField(defaultValue = "1")
    @TableField(value = "version")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("版本号")
    private Integer version;
    /**
     * 由谁更新
     */
    @TableField(value = "editedby")
    @JSONField(name = "editedby")
    @JsonProperty("editedby")
    @ApiModelProperty("由谁更新")
    private String editedby;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "dept")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 文档编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("文档编号")
    private Long id;
    /**
     * 文档标题
     */
    @TableField(value = "title")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("文档标题")
    private String title;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "deleted")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 最近更新数量
     */
    @TableField(exist = false)
    @JSONField(name = "recentupdatecnt")
    @JsonProperty("recentupdatecnt")
    @ApiModelProperty("最近更新数量")
    private Integer recentupdatecnt;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "org")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 文档编号
     */
    @TableField(value = "docsn")
    @JSONField(name = "docsn")
    @JsonProperty("docsn")
    @ApiModelProperty("文档编号")
    private Long docsn;
    /**
     * 文档类型
     */
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("文档类型")
    private String type;
    /**
     * 所有文档数量
     */
    @TableField(exist = false)
    @JSONField(name = "alldoccnt")
    @JsonProperty("alldoccnt")
    @ApiModelProperty("所有文档数量")
    private Integer alldoccnt;
    /**
     * 添加时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "addeddate", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "addeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("addeddate")
    @ApiModelProperty("添加时间")
    private Timestamp addeddate;
    /**
     * 权限
     */
    @DEField(defaultValue = "open")
    @TableField(value = "acl")
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @ApiModelProperty("权限")
    private String acl;
    /**
     * 我的文档数量
     */
    @TableField(exist = false)
    @JSONField(name = "mydoccnt")
    @JsonProperty("mydoccnt")
    @ApiModelProperty("我的文档数量")
    private Integer mydoccnt;
    /**
     * 文档链接
     */
    @TableField(exist = false)
    @JSONField(name = "url")
    @JsonProperty("url")
    @ApiModelProperty("文档链接")
    private String url;
    /**
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "orgname")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;
    /**
     * 文档数
     */
    @TableField(exist = false)
    @JSONField(name = "doccnt")
    @JsonProperty("doccnt")
    @ApiModelProperty("文档数")
    private Integer doccnt;
    /**
     * 我的收藏数量
     */
    @TableField(exist = false)
    @JSONField(name = "myfavouritecnt")
    @JsonProperty("myfavouritecnt")
    @ApiModelProperty("我的收藏数量")
    private Integer myfavouritecnt;
    /**
     * 用户
     */
    @TableField(value = "users")
    @JSONField(name = "users")
    @JsonProperty("users")
    @ApiModelProperty("用户")
    private String users;
    /**
     * 最近添加数量
     */
    @TableField(exist = false)
    @JSONField(name = "recentaddcnt")
    @JsonProperty("recentaddcnt")
    @ApiModelProperty("最近添加数量")
    private Integer recentaddcnt;
    /**
     * 关键字
     */
    @TableField(value = "keywords")
    @JSONField(name = "keywords")
    @JsonProperty("keywords")
    @ApiModelProperty("关键字")
    private String keywords;
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
    @TableField(value = "collector")
    @JSONField(name = "collector")
    @JsonProperty("collector")
    @ApiModelProperty("收藏者")
    private String collector;
    /**
     * 由谁添加
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "addedby")
    @JSONField(name = "addedby")
    @JsonProperty("addedby")
    @ApiModelProperty("由谁添加")
    private String addedby;
    /**
     * 今日更新数量
     */
    @TableField(exist = false)
    @JSONField(name = "todayupdatecnt")
    @JsonProperty("todayupdatecnt")
    @ApiModelProperty("今日更新数量")
    private Integer todayupdatecnt;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "updateby")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
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
     * 所属文档库
     */
    @TableField(exist = false)
    @JSONField(name = "libname")
    @JsonProperty("libname")
    @ApiModelProperty("所属文档库")
    private String libname;
    /**
     * 模块分类
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @ApiModelProperty("模块分类")
    private String modulename;
    /**
     * 所属文档库
     */
    @TableField(value = "lib")
    @JSONField(name = "lib")
    @JsonProperty("lib")
    @ApiModelProperty("所属文档库")
    private Long lib;
    /**
     * 所属项目
     */
    @DEField(defaultValue = "0")
    @TableField(value = "project")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("所属项目")
    private Long project;
    /**
     * 所属产品
     */
    @DEField(defaultValue = "0")
    @TableField(value = "product")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("所属产品")
    private Long product;
    /**
     * 所属分类
     */
    @DEField(defaultValue = "0")
    @TableField(value = "module")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("所属分类")
    private Long module;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.DocLib ztDoclib;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibiz.domain.DocLibModule ztModule;

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
     * 文档内容
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.zentao.domain.DocContent> docconents;


    /**
     * 设置 [分组]
     */
    public void setGroups(String groups) {
        this.groups = groups;
        this.modify("groups", groups);
    }

    /**
     * 设置 [更新时间]
     */
    public void setEditeddate(Timestamp editeddate) {
        this.editeddate = editeddate;
        this.modify("editeddate", editeddate);
    }

    /**
     * 格式化日期 [更新时间]
     */
    public String formatEditeddate() {
        if (this.editeddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(editeddate);
    }
    /**
     * 设置 [views]
     */
    public void setViews(Integer views) {
        this.views = views;
        this.modify("views", views);
    }

    /**
     * 设置 [版本号]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [由谁更新]
     */
    public void setEditedby(String editedby) {
        this.editedby = editedby;
        this.modify("editedby", editedby);
    }

    /**
     * 设置 [文档标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [文档编号]
     */
    public void setDocsn(Long docsn) {
        this.docsn = docsn;
        this.modify("docsn", docsn);
    }

    /**
     * 设置 [文档类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [权限]
     */
    public void setAcl(String acl) {
        this.acl = acl;
        this.modify("acl", acl);
    }

    /**
     * 设置 [用户]
     */
    public void setUsers(String users) {
        this.users = users;
        this.modify("users", users);
    }

    /**
     * 设置 [关键字]
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
        this.modify("keywords", keywords);
    }

    /**
     * 设置 [收藏者]
     */
    public void setCollector(String collector) {
        this.collector = collector;
        this.modify("collector", collector);
    }

    /**
     * 设置 [所属文档库]
     */
    public void setLib(Long lib) {
        this.lib = lib;
        this.modify("lib", lib);
    }

    /**
     * 设置 [所属项目]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
    }

    /**
     * 设置 [所属产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [所属分类]
     */
    public void setModule(Long module) {
        this.module = module;
        this.modify("module", module);
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


