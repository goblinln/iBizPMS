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
 * 服务DTO对象[TestReportDTO]
 */
@Data
@ApiModel("测试报告")
@JsonFilter(value = "dtofieldfilter")
public class TestReportDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [PRODUCTCNT]
     *
     */
    @JSONField(name = "productcnt")
    @JsonProperty("productcnt")
    @ApiModelProperty("项目报告产品数")
    private Integer productcnt;
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
     * 属性 [OVERVIEWS]
     *
     */
    @JSONField(name = "overviews")
    @JsonProperty("overviews")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("概况")
    private String overviews;
    /**
     * 属性 [OBJECTID]
     *
     */
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    @ApiModelProperty("所属对象")
    private Integer objectid;
    /**
     * 属性 [CASES]
     *
     */
    @JSONField(name = "cases")
    @JsonProperty("cases")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("用例")
    private String cases;
    /**
     * 属性 [MEMBERS]
     *
     */
    @JSONField(name = "members")
    @JsonProperty("members")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("参与人员")
    private String members;
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
     * 属性 [BUGS]
     *
     */
    @JSONField(name = "bugs")
    @JsonProperty("bugs")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("测试的Bug")
    private String bugs;
    /**
     * 属性 [CREATEDBY]
     *
     */
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁创建")
    private String createdby;
    /**
     * 属性 [STORIES]
     *
     */
    @JSONField(name = "stories")
    @JsonProperty("stories")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("测试的需求")
    private String stories;
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
     * 属性 [TESTREPORTSN]
     *
     */
    @JSONField(name = "testreportsn")
    @JsonProperty("testreportsn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("测试报告编号")
    private Long testreportsn;
    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * 属性 [TASKS]
     *
     */
    @JSONField(name = "tasks")
    @JsonProperty("tasks")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("测试单")
    private String tasks;
    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @NotBlank(message = "[标题]不允许为空!")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("标题")
    private String title;
    /**
     * 属性 [BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    @NotNull(message = "[开始时间]不允许为空!")
    @ApiModelProperty("开始时间")
    private Timestamp begin;
    /**
     * 属性 [OBJECTTYPE]
     *
     */
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("对象类型")
    private String objecttype;
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
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @NotNull(message = "[结束时间]不允许为空!")
    @ApiModelProperty("结束时间")
    private Timestamp end;
    /**
     * 属性 [BUILDS]
     *
     */
    @JSONField(name = "builds")
    @JsonProperty("builds")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("版本信息")
    private String builds;
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
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 属性 [CREATEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    @ApiModelProperty("创建时间")
    private Timestamp createddate;
    /**
     * 属性 [REPORT]
     *
     */
    @JSONField(name = "report")
    @JsonProperty("report")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("总结")
    private String report;
    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 属性 [OWNER]
     *
     */
    @JSONField(name = "owner")
    @JsonProperty("owner")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("负责人")
    private String owner;
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
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("所属项目")
    private String projectname;
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
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属产品")
    private Long product;
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
     * 设置 [OBJECTID]
     */
    public void setObjectid(Integer  objectid){
        this.objectid = objectid ;
        this.modify("objectid",objectid);
    }

    /**
     * 设置 [CASES]
     */
    public void setCases(String  cases){
        this.cases = cases ;
        this.modify("cases",cases);
    }

    /**
     * 设置 [MEMBERS]
     */
    public void setMembers(String  members){
        this.members = members ;
        this.modify("members",members);
    }

    /**
     * 设置 [BUGS]
     */
    public void setBugs(String  bugs){
        this.bugs = bugs ;
        this.modify("bugs",bugs);
    }

    /**
     * 设置 [STORIES]
     */
    public void setStories(String  stories){
        this.stories = stories ;
        this.modify("stories",stories);
    }

    /**
     * 设置 [TESTREPORTSN]
     */
    public void setTestreportsn(Long  testreportsn){
        this.testreportsn = testreportsn ;
        this.modify("testreportsn",testreportsn);
    }

    /**
     * 设置 [TASKS]
     */
    public void setTasks(String  tasks){
        this.tasks = tasks ;
        this.modify("tasks",tasks);
    }

    /**
     * 设置 [TITLE]
     */
    public void setTitle(String  title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [BEGIN]
     */
    public void setBegin(Timestamp  begin){
        this.begin = begin ;
        this.modify("begin",begin);
    }

    /**
     * 设置 [OBJECTTYPE]
     */
    public void setObjecttype(String  objecttype){
        this.objecttype = objecttype ;
        this.modify("objecttype",objecttype);
    }

    /**
     * 设置 [END]
     */
    public void setEnd(Timestamp  end){
        this.end = end ;
        this.modify("end",end);
    }

    /**
     * 设置 [BUILDS]
     */
    public void setBuilds(String  builds){
        this.builds = builds ;
        this.modify("builds",builds);
    }

    /**
     * 设置 [REPORT]
     */
    public void setReport(String  report){
        this.report = report ;
        this.modify("report",report);
    }

    /**
     * 设置 [OWNER]
     */
    public void setOwner(String  owner){
        this.owner = owner ;
        this.modify("owner",owner);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(Long  product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [PROJECT]
     */
    public void setProject(Long  project){
        this.project = project ;
        this.modify("project",project);
    }


}


