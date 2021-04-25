package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * 服务DTO对象[DocDTO]
 */
@Data
@ApiModel("文档")
public class DocDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [GROUPS]
     *
     */
    @JSONField(name = "groups")
    @JsonProperty("groups")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("分组")
    private String groups;

    /**
     * 属性 [CONTENT]
     *
     */
    @JSONField(name = "content")
    @JsonProperty("content")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("文档正文")
    private String content;

    /**
     * 属性 [DOCQTYPE]
     *
     */
    @JSONField(name = "docqtype")
    @JsonProperty("docqtype")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("文档查询类型")
    private String docqtype;

    /**
     * 属性 [EDITEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "editeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("editeddate")
    @ApiModelProperty("更新时间")
    private Timestamp editeddate;

    /**
     * 属性 [VIEWS]
     *
     */
    @JSONField(name = "views")
    @JsonProperty("views")
    @ApiModelProperty("views")
    private Integer views;

    /**
     * 属性 [VERSION]
     *
     */
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("版本号")
    private Integer version;

    /**
     * 属性 [EDITEDBY]
     *
     */
    @JSONField(name = "editedby")
    @JsonProperty("editedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁更新")
    private String editedby;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("文档编号")
    private Long id;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @NotBlank(message = "[文档标题]不允许为空!")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("文档标题")
    private String title;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已删除")
    private String deleted;

    /**
     * 属性 [RECENTUPDATECNT]
     *
     */
    @JSONField(name = "recentupdatecnt")
    @JsonProperty("recentupdatecnt")
    @ApiModelProperty("最近更新数量")
    private Integer recentupdatecnt;

    /**
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("文档类型")
    private String type;

    /**
     * 属性 [ALLDOCCNT]
     *
     */
    @JSONField(name = "alldoccnt")
    @JsonProperty("alldoccnt")
    @ApiModelProperty("所有文档数量")
    private Integer alldoccnt;

    /**
     * 属性 [ADDEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "addeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("addeddate")
    @ApiModelProperty("添加时间")
    private Timestamp addeddate;

    /**
     * 属性 [ACL]
     *
     */
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @Size(min = 0, max = 10, message = "内容长度必须小于等于[10]")
    @ApiModelProperty("权限")
    private String acl;

    /**
     * 属性 [MYDOCCNT]
     *
     */
    @JSONField(name = "mydoccnt")
    @JsonProperty("mydoccnt")
    @ApiModelProperty("我的文档数量")
    private Integer mydoccnt;

    /**
     * 属性 [URL]
     *
     */
    @JSONField(name = "url")
    @JsonProperty("url")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("文档链接")
    private String url;

    /**
     * 属性 [DOCCNT]
     *
     */
    @JSONField(name = "doccnt")
    @JsonProperty("doccnt")
    @ApiModelProperty("文档数")
    private Integer doccnt;

    /**
     * 属性 [MYFAVOURITECNT]
     *
     */
    @JSONField(name = "myfavouritecnt")
    @JsonProperty("myfavouritecnt")
    @ApiModelProperty("我的收藏数量")
    private Integer myfavouritecnt;

    /**
     * 属性 [USERS]
     *
     */
    @JSONField(name = "users")
    @JsonProperty("users")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("用户")
    private String users;

    /**
     * 属性 [RECENTADDCNT]
     *
     */
    @JSONField(name = "recentaddcnt")
    @JsonProperty("recentaddcnt")
    @ApiModelProperty("最近添加数量")
    private Integer recentaddcnt;

    /**
     * 属性 [KEYWORDS]
     *
     */
    @JSONField(name = "keywords")
    @JsonProperty("keywords")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("关键字")
    private String keywords;

    /**
     * 属性 [ISFAVOURITES]
     *
     */
    @JSONField(name = "isfavourites")
    @JsonProperty("isfavourites")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("是否收藏")
    private String isfavourites;

    /**
     * 属性 [COLLECTOR]
     *
     */
    @JSONField(name = "collector")
    @JsonProperty("collector")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("收藏者")
    private String collector;

    /**
     * 属性 [ADDEDBY]
     *
     */
    @JSONField(name = "addedby")
    @JsonProperty("addedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁添加")
    private String addedby;

    /**
     * 属性 [TODAYUPDATECNT]
     *
     */
    @JSONField(name = "todayupdatecnt")
    @JsonProperty("todayupdatecnt")
    @ApiModelProperty("今日更新数量")
    private Integer todayupdatecnt;

    /**
     * 属性 [UPDATEBY]
     *
     */
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 1000, message = "内容长度必须小于等于[1000]")
    @ApiModelProperty("附件")
    private String files;

    /**
     * 属性 [PRODUCTNAME]
     *
     */
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("所属产品")
    private String productname;

    /**
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("所属项目")
    private String projectname;

    /**
     * 属性 [LIBNAME]
     *
     */
    @JSONField(name = "libname")
    @JsonProperty("libname")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("所属文档库")
    private String libname;

    /**
     * 属性 [MODULENAME]
     *
     */
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("模块分类")
    private String modulename;

    /**
     * 属性 [LIB]
     *
     */
    @JSONField(name = "lib")
    @JsonProperty("lib")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属文档库")
    private Long lib;

    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属项目")
    private Long project;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属产品")
    private Long product;

    /**
     * 属性 [MODULE]
     *
     */
    @JSONField(name = "module")
    @JsonProperty("module")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属分类")
    private Long module;

    /**
     * 属性 [DEPTNAME]
     *
     */
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String deptname;

    /**
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;


    /**
     * 设置 [GROUPS]
     */
    public void setGroups(String  groups){
        this.groups = groups ;
        this.modify("groups",groups);
    }

    /**
     * 设置 [EDITEDDATE]
     */
    public void setEditeddate(Timestamp  editeddate){
        this.editeddate = editeddate ;
        this.modify("editeddate",editeddate);
    }

    /**
     * 设置 [VIEWS]
     */
    public void setViews(Integer  views){
        this.views = views ;
        this.modify("views",views);
    }

    /**
     * 设置 [VERSION]
     */
    public void setVersion(Integer  version){
        this.version = version ;
        this.modify("version",version);
    }

    /**
     * 设置 [EDITEDBY]
     */
    public void setEditedby(String  editedby){
        this.editedby = editedby ;
        this.modify("editedby",editedby);
    }

    /**
     * 设置 [TITLE]
     */
    public void setTitle(String  title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [ACL]
     */
    public void setAcl(String  acl){
        this.acl = acl ;
        this.modify("acl",acl);
    }

    /**
     * 设置 [USERS]
     */
    public void setUsers(String  users){
        this.users = users ;
        this.modify("users",users);
    }

    /**
     * 设置 [KEYWORDS]
     */
    public void setKeywords(String  keywords){
        this.keywords = keywords ;
        this.modify("keywords",keywords);
    }

    /**
     * 设置 [COLLECTOR]
     */
    public void setCollector(String  collector){
        this.collector = collector ;
        this.modify("collector",collector);
    }

    /**
     * 设置 [LIB]
     */
    public void setLib(Long  lib){
        this.lib = lib ;
        this.modify("lib",lib);
    }

    /**
     * 设置 [PROJECT]
     */
    public void setProject(Long  project){
        this.project = project ;
        this.modify("project",project);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(Long  product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [MODULE]
     */
    public void setModule(Long  module){
        this.module = module ;
        this.modify("module",module);
    }


}


