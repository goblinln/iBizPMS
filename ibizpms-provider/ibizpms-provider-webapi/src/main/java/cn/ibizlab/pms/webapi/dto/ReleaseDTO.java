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
 * 服务DTO对象[ReleaseDTO]
 */
@Data
@ApiModel("发布")
public class ReleaseDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [STORIES]
     *
     */
    @JSONField(name = "stories")
    @JsonProperty("stories")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("完成的需求")
    private String stories;

    /**
     * 属性 [MARKER]
     *
     */
    @JSONField(name = "marker")
    @JsonProperty("marker")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("里程碑")
    private String marker;

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
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 属性 [SQLID]
     *
     */
    @JSONField(name = "sqlid")
    @JsonProperty("sqlid")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("运行数据库")
    private String sqlid;

    /**
     * 属性 [LEFTBUGS]
     *
     */
    @JSONField(name = "leftbugs")
    @JsonProperty("leftbugs")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("遗留的Bug")
    private String leftbugs;

    /**
     * 属性 [BUGS]
     *
     */
    @JSONField(name = "bugs")
    @JsonProperty("bugs")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("解决的Bug")
    private String bugs;

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
     * 属性 [NOTICEUSERS]
     *
     */
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;

    /**
     * 属性 [REBUILD]
     *
     */
    @JSONField(name = "rebuild")
    @JsonProperty("rebuild")
    @ApiModelProperty("重新构建")
    private Integer rebuild;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[发布名称]不允许为空!")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("发布名称")
    private String name;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("发布日期")
    private Timestamp date;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("状态")
    private String status;

    /**
     * 属性 [RELEASETYPE]
     *
     */
    @JSONField(name = "releasetype")
    @JsonProperty("releasetype")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("运行模式")
    private String releasetype;

    /**
     * 属性 [SUBSTATUS]
     *
     */
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("子状态")
    private String substatus;

    /**
     * 属性 [BACKGROUNDID]
     *
     */
    @JSONField(name = "backgroundid")
    @JsonProperty("backgroundid")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("后台体系")
    private String backgroundid;

    /**
     * 属性 [DESC]
     *
     */
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("描述")
    private String desc;

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
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;

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
     * 属性 [UPDATEBY]
     *
     */
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 属性 [FRONTAPPLICATION]
     *
     */
    @JSONField(name = "frontapplication")
    @JsonProperty("frontapplication")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("系统应用")
    private String frontapplication;

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
     * 属性 [CREATEBY]
     *
     */
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁创建")
    private String createby;

    /**
     * 属性 [PRODUCTNAME]
     *
     */
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @Size(min = 0, max = 8, message = "内容长度必须小于等于[8]")
    @ApiModelProperty("产品名称")
    private String productname;

    /**
     * 属性 [BUILDER]
     *
     */
    @JSONField(name = "builder")
    @JsonProperty("builder")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("构建者")
    private String builder;

    /**
     * 属性 [BUILDNAME]
     *
     */
    @JSONField(name = "buildname")
    @JsonProperty("buildname")
    @Size(min = 0, max = 150, message = "内容长度必须小于等于[150]")
    @ApiModelProperty("版本")
    private String buildname;

    /**
     * 属性 [BUILDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "builddate" , format="yyyy-MM-dd")
    @JsonProperty("builddate")
    @ApiModelProperty("打包日期")
    private Timestamp builddate;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("产品")
    private Long product;

    /**
     * 属性 [BUILD]
     *
     */
    @JSONField(name = "build")
    @JsonProperty("build")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("版本")
    private Long build;

    /**
     * 属性 [BRANCH]
     *
     */
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("平台/分支")
    private Long branch;

    /**
     * 属性 [RELEASESN]
     *
     */
    @JSONField(name = "releasesn")
    @JsonProperty("releasesn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("发布编号")
    private Long releasesn;


    /**
     * 设置 [STORIES]
     */
    public void setStories(String  stories){
        this.stories = stories ;
        this.modify("stories",stories);
    }

    /**
     * 设置 [MARKER]
     */
    public void setMarker(String  marker){
        this.marker = marker ;
        this.modify("marker",marker);
    }

    /**
     * 设置 [LEFTBUGS]
     */
    public void setLeftbugs(String  leftbugs){
        this.leftbugs = leftbugs ;
        this.modify("leftbugs",leftbugs);
    }

    /**
     * 设置 [BUGS]
     */
    public void setBugs(String  bugs){
        this.bugs = bugs ;
        this.modify("bugs",bugs);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [SUBSTATUS]
     */
    public void setSubstatus(String  substatus){
        this.substatus = substatus ;
        this.modify("substatus",substatus);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [PRODUCT]
     */
    public void setProduct(Long  product){
        this.product = product ;
        this.modify("product",product);
    }

    /**
     * 设置 [BUILD]
     */
    public void setBuild(Long  build){
        this.build = build ;
        this.modify("build",build);
    }

    /**
     * 设置 [BRANCH]
     */
    public void setBranch(Long  branch){
        this.branch = branch ;
        this.modify("branch",branch);
    }

    /**
     * 设置 [RELEASESN]
     */
    public void setReleasesn(Long  releasesn){
        this.releasesn = releasesn ;
        this.modify("releasesn",releasesn);
    }


}


