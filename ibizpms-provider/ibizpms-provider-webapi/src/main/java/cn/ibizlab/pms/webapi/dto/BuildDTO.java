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

/**
 * 服务DTO对象[BuildDTO]
 */
@Data
public class BuildDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[名称编号]不允许为空!")
    @Size(min = 0, max = 150, message = "内容长度必须小于等于[150]")
    private String name;

    /**
     * 属性 [BUILDER]
     *
     */
    @JSONField(name = "builder")
    @JsonProperty("builder")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    private String builder;

    /**
     * 属性 [DESC]
     *
     */
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    private String desc;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    private String deleted;

    /**
     * 属性 [SCMPATH]
     *
     */
    @JSONField(name = "scmpath")
    @JsonProperty("scmpath")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    private String scmpath;

    /**
     * 属性 [FILEPATH]
     *
     */
    @JSONField(name = "filepath")
    @JsonProperty("filepath")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    private String filepath;

    /**
     * 属性 [STORIES]
     *
     */
    @JSONField(name = "stories")
    @JsonProperty("stories")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    private String stories;

    /**
     * 属性 [BUGS]
     *
     */
    @JSONField(name = "bugs")
    @JsonProperty("bugs")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    private String bugs;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    private Timestamp date;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long product;

    /**
     * 属性 [BRANCH]
     *
     */
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long branch;

    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long project;

    /**
     * 属性 [PRODUCTNAME]
     *
     */
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    private String productname;

    /**
     * 属性 [IDS]
     *
     */
    @JSONField(name = "ids")
    @JsonProperty("ids")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String ids;

    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 1000, message = "内容长度必须小于等于[1000]")
    private String files;

    /**
     * 属性 [REBUILD]
     *
     */
    @JSONField(name = "rebuild")
    @JsonProperty("rebuild")
    private Integer rebuild;

    /**
     * 属性 [RELEASETYPE]
     *
     */
    @JSONField(name = "releasetype")
    @JsonProperty("releasetype")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    private String releasetype;

    /**
     * 属性 [FRONTAPPLICATION]
     *
     */
    @JSONField(name = "frontapplication")
    @JsonProperty("frontapplication")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    private String frontapplication;

    /**
     * 属性 [BACKGROUNDID]
     *
     */
    @JSONField(name = "backgroundid")
    @JsonProperty("backgroundid")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    private String backgroundid;

    /**
     * 属性 [SQLID]
     *
     */
    @JSONField(name = "sqlid")
    @JsonProperty("sqlid")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    private String sqlid;

    /**
     * 属性 [CREATEBUGCNT]
     *
     */
    @JSONField(name = "createbugcnt")
    @JsonProperty("createbugcnt")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    private String createbugcnt;


    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [BUILDER]
     */
    public void setBuilder(String  builder){
        this.builder = builder ;
        this.modify("builder",builder);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [SCMPATH]
     */
    public void setScmpath(String  scmpath){
        this.scmpath = scmpath ;
        this.modify("scmpath",scmpath);
    }

    /**
     * 设置 [FILEPATH]
     */
    public void setFilepath(String  filepath){
        this.filepath = filepath ;
        this.modify("filepath",filepath);
    }

    /**
     * 设置 [STORIES]
     */
    public void setStories(String  stories){
        this.stories = stories ;
        this.modify("stories",stories);
    }

    /**
     * 设置 [BUGS]
     */
    public void setBugs(String  bugs){
        this.bugs = bugs ;
        this.modify("bugs",bugs);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(Long  product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [BRANCH]
     */
    public void setBranch(Long  branch){
        this.branch = branch ;
        this.modify("branch",branch);
    }

    /**
     * 设置 [PROJECT]
     */
    public void setProject(Long  project){
        this.project = project ;
        this.modify("project",project);
    }

    /**
     * 设置 [CREATEBUGCNT]
     */
    public void setCreatebugcnt(String  createbugcnt){
        this.createbugcnt = createbugcnt ;
        this.modify("createbugcnt",createbugcnt);
    }


}


