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
 * 服务DTO对象[BugStatsDTO]
 */
@Data
@ApiModel("Bug统计")
@JsonFilter(value = "dtofieldfilter")
public class BugStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [BUGACTIVE]
     *
     */
    @JSONField(name = "bugactive")
    @JsonProperty("bugactive")
    @ApiModelProperty("激活Bug")
    private Integer bugactive;

    /**
     * 属性 [BUGWILLNOTFIX]
     *
     */
    @JSONField(name = "bugwillnotfix")
    @JsonProperty("bugwillnotfix")
    @ApiModelProperty("不予解决")
    private Integer bugwillnotfix;

    /**
     * 属性 [RESOLVEDBY]
     *
     */
    @JSONField(name = "resolvedby")
    @JsonProperty("resolvedby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁解决")
    private String resolvedby;

    /**
     * 属性 [BUGRESOLUTION]
     *
     */
    @JSONField(name = "bugresolution")
    @JsonProperty("bugresolution")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("Bug解决方案")
    private String bugresolution;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("部门")
    private String dept;

    /**
     * 属性 [BUGBYDESIGN]
     *
     */
    @JSONField(name = "bugbydesign")
    @JsonProperty("bugbydesign")
    @ApiModelProperty("设计如此")
    private Integer bugbydesign;

    /**
     * 属性 [BUGOPENEDBY]
     *
     */
    @JSONField(name = "bugopenedby")
    @JsonProperty("bugopenedby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("bug创建人")
    private String bugopenedby;

    /**
     * 属性 [BUGSTATUS]
     *
     */
    @JSONField(name = "bugstatus")
    @JsonProperty("bugstatus")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("Bug状态")
    private String bugstatus;

    /**
     * 属性 [BUGCLOSED]
     *
     */
    @JSONField(name = "bugclosed")
    @JsonProperty("bugclosed")
    @ApiModelProperty("已关闭Bug")
    private Integer bugclosed;

    /**
     * 属性 [ASSIGNEDTO]
     *
     */
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("指派给")
    private String assignedto;

    /**
     * 属性 [BUGEXTERNAL]
     *
     */
    @JSONField(name = "bugexternal")
    @JsonProperty("bugexternal")
    @ApiModelProperty("外部原因")
    private Integer bugexternal;

    /**
     * 属性 [BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始")
    private Timestamp begin;

    /**
     * 属性 [BUGRESOLVEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "bugresolveddate" , format="yyyy-MM-dd")
    @JsonProperty("bugresolveddate")
    @ApiModelProperty("bug解决日期")
    private Timestamp bugresolveddate;

    /**
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束")
    private Timestamp end;

    /**
     * 属性 [BUGID]
     *
     */
    @JSONField(name = "bugid")
    @JsonProperty("bugid")
    @ApiModelProperty("Bug编号")
    private Integer bugid;

    /**
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目名称")
    private String projectname;

    /**
     * 属性 [BUGFIXED]
     *
     */
    @JSONField(name = "bugfixed")
    @JsonProperty("bugfixed")
    @ApiModelProperty("已解决")
    private Integer bugfixed;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("标识")
    private Long id;

    /**
     * 属性 [BUGPOSTPONED]
     *
     */
    @JSONField(name = "bugpostponed")
    @JsonProperty("bugpostponed")
    @ApiModelProperty("延期处理")
    private Integer bugpostponed;

    /**
     * 属性 [BUGTITLE]
     *
     */
    @JSONField(name = "bugtitle")
    @JsonProperty("bugtitle")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("Bug标题")
    private String bugtitle;

    /**
     * 属性 [BUGCNT]
     *
     */
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("Bug")
    private Integer bugcnt;

    /**
     * 属性 [BUGOPENEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "bugopeneddate" , format="yyyy-MM-dd")
    @JsonProperty("bugopeneddate")
    @ApiModelProperty("Bug创建日期")
    private Timestamp bugopeneddate;

    /**
     * 属性 [BUGWJJ]
     *
     */
    @JSONField(name = "bugwjj")
    @JsonProperty("bugwjj")
    @ApiModelProperty("未解决")
    private Integer bugwjj;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("名称")
    private String title;

    /**
     * 属性 [BUGPRI]
     *
     */
    @JSONField(name = "bugpri")
    @JsonProperty("bugpri")
    @ApiModelProperty("Bug优先级")
    private Integer bugpri;

    /**
     * 属性 [BUGNOTREPRO]
     *
     */
    @JSONField(name = "bugnotrepro")
    @JsonProperty("bugnotrepro")
    @ApiModelProperty("无法重现")
    private Integer bugnotrepro;

    /**
     * 属性 [PROJECTNAME1]
     *
     */
    @JSONField(name = "projectname1")
    @JsonProperty("projectname1")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目名称")
    private String projectname1;

    /**
     * 属性 [BUGRESOLVED]
     *
     */
    @JSONField(name = "bugresolved")
    @JsonProperty("bugresolved")
    @ApiModelProperty("已解决Bug")
    private Integer bugresolved;

    /**
     * 属性 [BUGDUPLICATE]
     *
     */
    @JSONField(name = "bugduplicate")
    @JsonProperty("bugduplicate")
    @ApiModelProperty("重复Bug")
    private Integer bugduplicate;

    /**
     * 属性 [OPENEDBY]
     *
     */
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("由谁创建")
    private String openedby;

    /**
     * 属性 [BUGEFFICIENT]
     *
     */
    @JSONField(name = "bugefficient")
    @JsonProperty("bugefficient")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("有效率")
    private String bugefficient;

    /**
     * 属性 [BUGTOSTORY]
     *
     */
    @JSONField(name = "bugtostory")
    @JsonProperty("bugtostory")
    @ApiModelProperty("转为需求")
    private Integer bugtostory;

    /**
     * 属性 [BUGSEVERITY]
     *
     */
    @JSONField(name = "bugseverity")
    @JsonProperty("bugseverity")
    @ApiModelProperty("Bug严重程度")
    private Integer bugseverity;

    /**
     * 属性 [BUGTOTAL]
     *
     */
    @JSONField(name = "bugtotal")
    @JsonProperty("bugtotal")
    @ApiModelProperty("总计")
    private Integer bugtotal;

    /**
     * 属性 [PRODUCTNAME]
     *
     */
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("产品名称")
    private String productname;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long product;

    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目编号")
    private Long project;


    /**
     * 设置 [RESOLVEDBY]
     */
    public void setResolvedby(String  resolvedby){
        this.resolvedby = resolvedby ;
        this.modify("resolvedby",resolvedby);
    }

    /**
     * 设置 [ASSIGNEDTO]
     */
    public void setAssignedto(String  assignedto){
        this.assignedto = assignedto ;
        this.modify("assignedto",assignedto);
    }

    /**
     * 设置 [PROJECTNAME]
     */
    public void setProjectname(String  projectname){
        this.projectname = projectname ;
        this.modify("projectname",projectname);
    }

    /**
     * 设置 [TITLE]
     */
    public void setTitle(String  title){
        this.title = title ;
        this.modify("title",title);
    }

    /**
     * 设置 [OPENEDBY]
     */
    public void setOpenedby(String  openedby){
        this.openedby = openedby ;
        this.modify("openedby",openedby);
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


